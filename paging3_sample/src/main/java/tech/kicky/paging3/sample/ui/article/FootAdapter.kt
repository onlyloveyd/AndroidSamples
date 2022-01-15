package tech.kicky.paging3.sample.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import tech.kicky.common.adapter.BindingViewHolder
import tech.kicky.paging3.sample.databinding.FooterItemBinding

/**
 * 底部状态
 * author: yidong
 * 2022-01-10
 */
class FooterAdapter(private val retry: () -> Unit) : LoadStateAdapter<BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        val binding = FooterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {
        val binding = holder.binding as FooterItemBinding

        binding.loading.isVisible = loadState is LoadState.Loading
        binding.message.text = when (loadState) {
            is LoadState.Loading -> "正在加载"
            is LoadState.Error -> "点击重试"
            is LoadState.NotLoading -> "已全部加载"
        }
        binding.message.setOnClickListener {
            if (loadState is LoadState.Error) {
                retry.invoke()
            }
        }
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return super.displayLoadStateAsItem(loadState)
                || (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
    }
}
