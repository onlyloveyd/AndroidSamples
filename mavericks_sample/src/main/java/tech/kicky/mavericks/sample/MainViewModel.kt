package tech.kicky.mavericks.sample

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.Dispatchers
import tech.kicky.mavericks.sample.network.Retrofitance

class MainViewModel(initState: MainState) : MavericksViewModel<MainState>(initState) {
    init {
        getHotKeys()
    }

    fun getHotKeys() = withState {
        if (it.request is Loading) return@withState
        suspend {
            Retrofitance.wanAndroidAPI.hotKey()
        }.execute(Dispatchers.IO, retainValue = MainState::request) { state ->
            copy(request = state, hotKeys = state()?.data ?: emptyList())
        }
    }
}