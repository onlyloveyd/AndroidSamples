package cn.onlyloveyd.androidpractice.recyclerviewpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.onlyloveyd.androidpractice.databinding.ItemArticleBinding

/**
 * 通用型适配器
 *
 * @author yidong
 * @date 2020/6/6
 */
class ArticleAdapter(val onClick: (Article) -> Unit) :
    ListAdapter<Article, BindingViewHolder>(Article.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ItemArticleBinding
        val article = getItem(position)
        binding.data = article
    }
}