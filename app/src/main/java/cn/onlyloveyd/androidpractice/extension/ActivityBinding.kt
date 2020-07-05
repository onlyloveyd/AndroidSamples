package cn.onlyloveyd.androidpractice.extension

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KProperty

/**
 * Binding Activity委托类
 *
 * @author yidong
 * @date 2020/6/9
 */
open class ActivityBinding<in TClass : Activity, out TBinding : ViewDataBinding>(@LayoutRes val layoutId: Int) {
    private var value: TBinding? = null

    operator fun getValue(thisRef: TClass, property: KProperty<*>): TBinding {
        value = value ?: DataBindingUtil.setContentView(thisRef, layoutId)
        return value!!
    }
}