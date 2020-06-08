package cn.onlyloveyd.androidpractice.recyclerviewpractice

import androidx.recyclerview.widget.DiffUtil

/**
 * 文章
 *
 * @author yidong
 * @date 2020/6/6
 */
data class Article(val id: Int, val title: String) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }
}