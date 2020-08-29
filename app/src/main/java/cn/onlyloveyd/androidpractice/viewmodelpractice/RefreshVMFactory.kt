package cn.onlyloveyd.androidpractice.viewmodelpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 带参数的ViewModel 工厂
 * author: yidong
 * 2020/8/29
 */
class RefreshVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RefreshViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RefreshViewModel("I am a param") as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}