package cn.onlyloveyd.androidpractice.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.LoadStates
import cn.onlyloveyd.androidpractice.adapter.BindingViewHolder
import cn.onlyloveyd.androidpractice.databinding.ItemFooterBinding

/**
 * 文章加载状态适配器
 *
 * @author yidong
 * @date 2020/7/23
 */
class ArticleLoadStateAdapter(
    private val adapter: ArticleAdapter
) : LoadStateAdapter<BindingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BindingViewHolder {
        val binding = ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {
        val binding = holder.binding as ItemFooterBinding

        when (loadState) {
            is LoadState.Error -> {
                binding.loading.visibility = View.GONE
                binding.loadingMsg.visibility = View.VISIBLE
                binding.loadingMsg.text = "Load Failed, Tap Retry"
                binding.loadingMsg.setOnClickListener {
                    adapter.retry()
                }
            }
            is LoadState.Loading -> {
                binding.loading.visibility = View.VISIBLE
                binding.loadingMsg.visibility = View.VISIBLE
                binding.loadingMsg.text = "Loading"
            }
            is LoadState.NotLoading -> {
                binding.loading.visibility = View.GONE
                binding.loadingMsg.visibility = View.GONE
            }
        }
    }
}