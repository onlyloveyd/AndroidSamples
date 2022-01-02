package tech.kicky.mavericks.sample.articles

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tech.kicky.mavericks.sample.BindViewHolder
import tech.kicky.mavericks.sample.data.Article
import tech.kicky.mavericks.sample.databinding.ItemArticleBinding


class ArticleAdapter : ListAdapter<Article, BindViewHolder>(ArticleItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindViewHolder, position: Int) {
        val binding = holder.binding as ItemArticleBinding
        binding.chapter.text = getItem(position).chapterName
        binding.title.text = Html.fromHtml(getItem(position).title)
    }
}

class ArticleItemCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Article, newItem: Article) =
        oldItem.hashCode() == newItem.hashCode()

}