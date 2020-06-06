package cn.onlyloveyd.androidpractice.recyclerviewpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.onlyloveyd.androidpractice.databinding.ItemBannerBinding
import cn.onlyloveyd.androidpractice.viewmodelpractice.Banner
import cn.onlyloveyd.androidpractice.viewmodelpractice.BannerAdapter

/**
 * 通用型适配器
 *
 * @author yidong
 * @date 2020/6/6
 */
class CommonAdapter<T>(val callback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BindingViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ItemBannerBinding
        val banner = getItem(position)
    }
}