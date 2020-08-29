package cn.onlyloveyd.androidpractice.paging

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import cn.onlyloveyd.androidpractice.R
import cn.onlyloveyd.androidpractice.databinding.ActivityPagingBinding
import cn.onlyloveyd.androidpractice.extension.ActivityBinding
import cn.onlyloveyd.androidpractice.retrofitcoroutine.Retrofitance
import cn.onlyloveyd.androidpractice.room.AppDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 分页显示文章Activity
 *
 * @author yidong
 * @date 2020/7/23
 */
@ExperimentalPagingApi
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
            viewModel.getArticleData(
                Retrofitance.instance.wanAndroidApi,
                AppDatabase.getInstance(this@PagingActivity)
            ).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}