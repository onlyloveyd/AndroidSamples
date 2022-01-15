package tech.kicky.paging3.sample.ui.article

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import tech.kicky.common.adapter.BindingViewHolder
import tech.kicky.paging3.sample.data.UiModel
import tech.kicky.paging3.sample.databinding.ArticleItemBinding
import tech.kicky.paging3.sample.databinding.SeparatorItemBinding

class ArticleAdapter : PagingDataAdapter<UiModel, BindingViewHolder>(COMPARATOR) {

    companion object {
        private const val ARTICLE = 1
        private const val SEPARATOR = 2

        private val COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.ArticleItem && newItem is UiModel.ArticleItem &&
                        oldItem.article.id == newItem.article.id) || (oldItem is UiModel.SeparatorItem &&
                        newItem is UiModel.SeparatorItem && oldItem.date == newItem.date)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.ArticleItem -> ARTICLE
            is UiModel.SeparatorItem -> SEPARATOR
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = if (viewType == ARTICLE) ArticleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ) else {
            SeparatorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        }
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = getItem(position)
        if (item is UiModel.ArticleItem) {
            val binding = holder.binding as ArticleItemBinding
            binding.title.text = Html.fromHtml(item.article.title)
            binding.chapter.text = item.article.chapterName
        } else if (item is UiModel.SeparatorItem) {
            val binding = holder.binding as SeparatorItemBinding
            binding.date.text = item.date
        }
    }

}
