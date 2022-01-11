package tech.kicky.paging3.sample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.kicky.paging3.sample.data.Article
import tech.kicky.paging3.sample.network.WanAndroidService

class ArticlePagingSource(
    private val wanAndroidService: WanAndroidService
) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1 // set page 1 as default
            val pageSize = params.loadSize
            val repoResponse = wanAndroidService.searchRepos(page)
            val repoItems = repoResponse.data.datas
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null

}
