package tech.kicky.mavericks.sample

import com.airbnb.mvrx.MavericksState

data class MainState(val count: Int = 0) : MavericksState