package tech.kicky.mavericks.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import tech.kicky.mavericks.sample.data.HotKey
import tech.kicky.mavericks.sample.databinding.ItemHotKeyBinding

/**
 * 热词适配器
 * author: yidong
 * 2022-01-01
 */
class BindViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

class HotKeyAdapter : ListAdapter<HotKey, BindViewHolder>(HashItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder {
        val binding = ItemHotKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindViewHolder, position: Int) {
        val binding = holder.binding as ItemHotKeyBinding
        binding.title.text = getItem(position).name
    }
}


class HashItemCallback : DiffUtil.ItemCallback<HotKey>() {
    override fun areItemsTheSame(oldItem: HotKey, newItem: HotKey) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HotKey, newItem: HotKey) =
        oldItem.hashCode() == newItem.hashCode()
}