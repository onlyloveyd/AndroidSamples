package cn.onlyloveyd.androidpractice.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import cn.onlyloveyd.androidpractice.data.Article
import cn.onlyloveyd.androidpractice.retrofitcoroutine.WanAndroidApi
import cn.onlyloveyd.androidpractice.room.AppDatabase
import retrofit2.HttpException
import java.io.IOException

/**
 * Article
 *
 * @author yidong
 * @date 2020/8/12
 */

private const val WAN_ANDROID_STARTING_PAGE_INDEX = 1

@ExperimentalPagingApi
class ArticleRemoteMediator(
    private val service: WanAndroidApi,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, Article>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> WAN_ANDROID_STARTING_PAGE_INDEX
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    if (state.lastItemOrNull() == null) {
                        return MediatorResult.Success(true)
                    } else {
                        state.lastItemOrNull()!!.pageIndex.plus(1)
                    }
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = service.getHomeArticles(
                loadKey
            )

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.articleDao().clearArticles()
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                response.data.data.forEach {
                    it.pageIndex = loadKey
                }
                appDatabase.articleDao().insertAll(response.data.data)
            }

            MediatorResult.Success(
                endOfPaginationReached = (response.data.curPage == response.data.pageCount)
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}