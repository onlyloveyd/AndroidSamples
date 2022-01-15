package tech.kicky.paging3.sample.ui.article

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.kicky.paging3.sample.data.Article
import tech.kicky.paging3.sample.network.WanAndroidService

class AuthorPagingSource(
    private val wanAndroidService: WanAndroidService
) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1 // set page 1 as default
            val articleResponse = wanAndroidService.authorArticle(page, "程序亦非猿")
            val articles = articleResponse.data.datas
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (articles.isNotEmpty()) page + 1 else null
            LoadResult.Page(articles, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
}

class HomePagingSource(
    private val wanAndroidService: WanAndroidService
) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1 // set page 1 as default
            val articleResponse = wanAndroidService.homeArticle(page)
            val articles = articleResponse.data.datas
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (articles.isNotEmpty()) page + 1 else null
            LoadResult.Page(articles, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
}
