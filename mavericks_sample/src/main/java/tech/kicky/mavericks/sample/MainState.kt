package tech.kicky.mavericks.sample

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import tech.kicky.mavericks.sample.data.HotKey
import tech.kicky.mavericks.sample.data.Response

data class MainState(
    val hotKeys: List<HotKey> = emptyList(),
    val request: Async<Response<List<HotKey>>> = Uninitialized
) : MavericksState