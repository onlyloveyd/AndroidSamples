package cn.onlyloveyd.androidpractice.vp2practice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Vp2 Viewmodel
 *
 * @author yidong
 * @date 2020/6/5
 */
class Vp2ViewModel : ViewModel() {
    val sample = MutableLiveData(0)

    fun addOne() {
        sample.postValue(sample.value?.plus(1))
    }
}