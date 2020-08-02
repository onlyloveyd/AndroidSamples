package cn.onlyloveyd.androidpractice.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import cn.onlyloveyd.androidpractice.adapter.BindingViewHolder
import cn.onlyloveyd.androidpractice.data.Article
import cn.onlyloveyd.androidpractice.databinding.ItemArticleBinding

/**
 * 文章Page 适配器
 *
 * @author yidong
 * @date 2020/7/23
 */
class ArticleAdapter : PagingDataAdapter<Article, BindingViewHolder>(Article.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding as ItemArticleBinding
        binding.data = getItem(position)
    }
}