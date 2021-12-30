package tech.kicky.mavericks.sample

import com.airbnb.mvrx.MavericksViewModel

class MainViewModel(initState: MainState) : MavericksViewModel<MainState>(initState) {

    fun incCount() {
        setState {
            copy(count = count + 1)
        }
    }
}