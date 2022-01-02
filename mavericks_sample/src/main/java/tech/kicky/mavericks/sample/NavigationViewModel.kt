package tech.kicky.mavericks.sample

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.PersistState

data class NavState(@PersistState val count: Int = 0) : MavericksState

class NavViewModel(initState: NavState) : MavericksViewModel<NavState>(initState) {

    fun incCount() {
        setState {
            copy(count = count.plus(1))
        }
    }
}