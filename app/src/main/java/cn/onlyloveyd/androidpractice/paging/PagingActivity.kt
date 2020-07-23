package cn.onlyloveyd.androidpractice.paging

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import cn.onlyloveyd.androidpractice.R
import cn.onlyloveyd.androidpractice.databinding.ActivityPagingBinding
import cn.onlyloveyd.androidpractice.extension.ActivityBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

/**
 * 分页显示文章Activity
 *
 * @author yidong
 * @date 2020/7/23
 */
class PagingActivity : AppCompatActivity() {

    private val viewModel by viewModels<PagingViewModel>()

    private val adapter: ArticleAdapter by lazy { ArticleAdapter() }

    private val mBinding: ActivityPagingBinding by ActivityBinding(R.layout.activity_paging)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.list.adapter = adapter.withLoadStateFooter(PostsLoadStateAdapter(adapter))
        //获取数据并渲染UI
        viewModel.getArticleData().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })
        //监听刷新状态当刷新完成之后关闭刷新
        lifecycleScope.launchWhenCreated {
            @OptIn(ExperimentalCoroutinesApi::class)
            adapter.loadStateFlow.collectLatest {
                if (it.refresh !is LoadState.Loading) {
                    mBinding.refresh.isRefreshing = false
                }
            }
        }
        mBinding.refresh.setOnRefreshListener {
            adapter.refresh()
        }
    }
}