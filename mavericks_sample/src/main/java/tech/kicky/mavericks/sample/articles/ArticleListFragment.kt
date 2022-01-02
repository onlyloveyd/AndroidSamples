package tech.kicky.mavericks.sample.articles

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.navigation.navGraphViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.snackbar.Snackbar
import tech.kicky.mavericks.sample.NavState
import tech.kicky.mavericks.sample.NavViewModel
import tech.kicky.mavericks.sample.R
import tech.kicky.mavericks.sample.databinding.FragmentArticleListBinding

class ArticleListFragment : Fragment(R.layout.fragment_article_list), MavericksView {

    private val viewModel: ArticleListViewModel by fragmentViewModel()
    private val binding: FragmentArticleListBinding by viewBinding()
    private val navViewModel: NavViewModel by navGraphViewModel(R.id.mavericks_nav)

    private val adapter by lazy {
        ArticleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onEach {
            binding.refresh.isRefreshing = it.isRefresh
            binding.tvMessage.apply {
                visibility =
                    if (it.isLoadMoreCompleted || it.isLoadMore) View.VISIBLE else View.GONE
                text = if (it.isLoadMoreCompleted) "已全部加载" else "加载中"
            }
        }
        viewModel.onAsync(
            ArticleListState::request,
            deliveryMode = uniqueOnly(),
            onFail = {
                Toast.makeText(context, "articles request failed.", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refresh.setOnRefreshListener {
            viewModel.refresh()
        }
        binding.list.adapter = adapter
        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                recyclerView.layoutManager?.let {
                    val visibleItemCount = it.childCount
                    val totalItemCount = it.itemCount
                    val lastVisibleItemPosition = when (it) {
                        is LinearLayoutManager -> it.findLastVisibleItemPosition()
                        is GridLayoutManager -> it.findLastVisibleItemPosition()
                        is StaggeredGridLayoutManager -> findLastVisibleItemPosition(
                            it.findLastVisibleItemPositions(
                                null
                            )
                        )
                        else -> return
                    }

                    if ((visibleItemCount + lastVisibleItemPosition + 3) >= totalItemCount) {
                        viewModel.loadMore()
                    }
                }
            }
        })

        navViewModel.onEach(NavState::count) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                Snackbar.make(
                    binding.root,
                    "$it times",
                    Snackbar.LENGTH_INDEFINITE
                )
                    .apply {
                        setAction("DISMISS") {
                            this.dismiss()
                        }
                        show()
                    }
            }
        }
    }

    private fun findLastVisibleItemPosition(lastVisibleItems: IntArray): Int {
        return lastVisibleItems.maxOfOrNull { it } ?: lastVisibleItems[0]
    }


    override fun invalidate() {
        withState(viewModel) {
            adapter.submitList(it.articles)
        }
    }
}