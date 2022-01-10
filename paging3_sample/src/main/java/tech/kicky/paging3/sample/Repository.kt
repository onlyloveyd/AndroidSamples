package tech.kicky.paging3.sample

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.kicky.paging3.sample.data.Repo
import tech.kicky.paging3.sample.network.GitHubService

object Repository {

    private const val PAGE_SIZE = 50

    private val gitHubService = GitHubService.create()

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(gitHubService) }
        ).flow
    }

}
