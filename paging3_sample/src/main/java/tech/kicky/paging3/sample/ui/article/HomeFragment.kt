package tech.kicky.paging3.sample.ui.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.kicky.paging3.sample.R
import tech.kicky.paging3.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy {
        ArticleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.getPagingData().collect {
                adapter.submitData(it)
            }

        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                binding.refresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter =
            adapter.withLoadStateFooter(
                FooterAdapter { adapter.retry() })
        binding.refresh.setOnRefreshListener {
            adapter.refresh()
        }
    }
}