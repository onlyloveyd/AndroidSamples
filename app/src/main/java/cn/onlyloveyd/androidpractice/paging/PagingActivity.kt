package cn.onlyloveyd.androidpractice.paging

import android.os.Bundle
import android.widget.Toast
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
import kotlinx.coroutines.launch

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
        mBinding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            ArticleLoadStateAdapter(adapter),
            ArticleLoadStateAdapter(adapter)
        )

        lifecycleScope.launch {
            viewModel.getArticleData().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

//        adapter.addLoadStateListener { loadState ->
//            Toast.makeText(
//                this,
//                "\uD83D\uDE28  $loadState",
//                Toast.LENGTH_LONG
//            ).show()
//        }

    }
}