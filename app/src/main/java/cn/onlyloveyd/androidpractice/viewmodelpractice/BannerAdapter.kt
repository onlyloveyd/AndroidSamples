package cn.onlyloveyd.androidpractice.viewmodelpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.onlyloveyd.androidpractice.databinding.ItemBannerBinding
import cn.onlyloveyd.androidpractice.recyclerviewpractice.BindingViewHolder

/**
 * Binding 适配器
 *
 * @author yidong
 * @date 2020/6/4
 */
class BannerAdapter(val onClick: (Banner) -> Unit) :
    ListAdapter<Banner, BindingViewHolder>(Banner.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ItemBannerBinding
        val banner = getItem(position)
        binding.data = banner
        holder.itemView.setOnClickListener {
            onClick(banner)
        }
    }

}
