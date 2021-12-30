package tech.kicky.dialogfragmentsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SampleViewModel : ViewModel() {

    private val _count = MutableSharedFlow<Int>(replay = 1)
    val count: SharedFlow<Int> = _count
    private var current: Int = 0

    fun incCount() {
        current = current.plus(1)
        viewModelScope.launch {
            _count.emit(current)
        }
    }
}