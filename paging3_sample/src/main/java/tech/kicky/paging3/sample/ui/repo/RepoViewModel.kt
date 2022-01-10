package tech.kicky.paging3.sample.ui.repo

import com.airbnb.mvrx.MavericksViewModel
import tech.kicky.paging3.sample.Repository

class RepoViewModel(initState: RepoState) : MavericksViewModel<RepoState>(initState) {

    init {
        getPagingData()
    }

    fun getPagingData() {
//        return Repository.getPagingData().cachedIn(viewModelScope)
        suspend {
            Repository.getPagingData()
        }.execute {
            copy(repos, request)
        }
    }


}
