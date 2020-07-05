package cn.onlyloveyd.androidpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.onlyloveyd.androidpractice.data.Article
import cn.onlyloveyd.androidpractice.databinding.ItemArticleBinding

/**
 * 文章列表适配器
 *
 * @author yidong
 * @date 2020/7/5
 */
class ArticleAdapter(callback: DiffUtil.ItemCallback<Article>, val click: (Article) -> Unit) :
    ListAdapter<Article, BindingViewHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ItemArticleBinding
        binding.data = getItem(position)
        holder.itemView.setOnClickListener {
            click(getItem(position))
        }
    }
}