package cn.onlyloveyd.androidpractice.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import cn.onlyloveyd.androidpractice.adapter.BindingViewHolder
import cn.onlyloveyd.androidpractice.databinding.ItemFooterBinding

/**
 * Description
 *
 * @author yidong
 * @date 2020/7/23
 */
class PostsLoadStateAdapter(
    private val adapter: ArticleAdapter
) : LoadStateAdapter<BindingViewHolder>() {
    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BindingViewHolder {

        val binding = ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }
}