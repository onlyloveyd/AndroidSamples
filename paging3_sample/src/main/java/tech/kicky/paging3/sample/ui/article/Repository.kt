package tech.kicky.paging3.sample.ui.article

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.kicky.paging3.sample.data.Article
import tech.kicky.paging3.sample.network.WanAndroidService

object Repository {

    private const val PAGE_SIZE = 50

    private val gitHubService = WanAndroidService.create()

    fun getAuthorArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { AuthorPagingSource(gitHubService) }
        ).flow
    }

    fun getHomeArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { HomePagingSource(gitHubService) }
        ).flow
    }
}
