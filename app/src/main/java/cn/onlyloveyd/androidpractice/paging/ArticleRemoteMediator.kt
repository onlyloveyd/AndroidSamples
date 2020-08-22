package cn.onlyloveyd.androidpractice.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import cn.onlyloveyd.androidpractice.data.Article
import cn.onlyloveyd.androidpractice.retrofitcoroutine.WanAndroidApi
import cn.onlyloveyd.androidpractice.room.AppDatabase
import cn.onlyloveyd.androidpractice.room.RemoteKeys
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

/**
 * Article
 *
 * @author yidong
 * @date 2020/8/12
 */

private const val WAN_ANDROID_STARTING_PAGE_INDEX = 1

@ExperimentalPagingApi
class ArticleRemoteMediator(
    private val query: String,
    private val service: WanAndroidApi,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, Article>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: WAN_ANDROID_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                    ?: // The LoadType is PREPEND so some data was loaded before,
                    // so we should have been able to get remote keys
                    // If the remoteKeys are null, then we're an invalid state and we have a bug
                    throw InvalidObjectException("Remote key and the prevKey should not be null")
                // If the previous key is null, then we can't request more data
                val prevKey = remoteKeys.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                remoteKeys.prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextKey
            }
        }

        try {
            val apiResponse = service.getHomeArticles(page)

            val articles = apiResponse.data.data
            val endOfPaginationReached = articles.isEmpty()
            appDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeysDao().clearRemoteKeys()
                    appDatabase.articleDao().clearArticles()
                }
                val prevKey = if (page == WAN_ANDROID_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = articles.map {
                    RemoteKeys(articleId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.remoteKeysDao().insertAll(keys)
                appDatabase.articleDao().insertAll(articles)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Article>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { article ->
                // Get the remote keys of the last item retrieved
                appDatabase.remoteKeysDao().remoteKeysArticleId(article.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Article>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { article ->
                // Get the remote keys of the first items retrieved
                appDatabase.remoteKeysDao().remoteKeysArticleId(article.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { articleId ->
                appDatabase.remoteKeysDao().remoteKeysArticleId(articleId)
            }
        }
    }
}