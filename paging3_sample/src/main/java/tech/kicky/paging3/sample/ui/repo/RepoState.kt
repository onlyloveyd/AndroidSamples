package tech.kicky.paging3.sample.ui.repo

import androidx.paging.PagingData
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import tech.kicky.paging3.sample.data.Repo
import tech.kicky.paging3.sample.data.RepoResponse

data class RepoState(
    val repos: PagingData<Repo> = PagingData.empty(),
    val request: Async<RepoResponse> = Uninitialized
) : MavericksState