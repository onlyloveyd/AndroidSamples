package tech.kicky.result

import android.content.Context
import android.view.ViewGroup
import tech.kicky.common.adapter.BindingViewHolder
import tech.kicky.common.adapter.ViewBindingAdapter
import tech.kicky.result.databinding.ItemTitleBinding

/**
 * 菜单标题
 * author: yidong
 * 2021-11-26
 */
class TitleAdapter(context: Context) :
    ViewBindingAdapter<MenuOpt>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ItemTitleBinding
        binding.title.text = getItem(position).title
        holder.itemView.setOnClickListener {
            getItem(position).event.invoke()
        }
    }
}