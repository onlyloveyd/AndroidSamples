package cn.onlyloveyd.androidpractice.retrofitcoroutine

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.onlyloveyd.androidpractice.R
import cn.onlyloveyd.androidpractice.adapter.ArticleAdapter
import cn.onlyloveyd.androidpractice.data.Article
import cn.onlyloveyd.androidpractice.databinding.ActivityRetrofitCoroutineBinding
import cn.onlyloveyd.androidpractice.extension.ActivityBinding

/**
 * Retrofit Coroutine Activity
 *
 * @author yidong
 * @date 2020/7/5
 */
class RetrofitCoroutineActivity : AppCompatActivity() {

    private val mBinding: ActivityRetrofitCoroutineBinding by ActivityBinding(R.layout.activity_retrofit_coroutine)
    private val mAdapter: ArticleAdapter by lazy {
        ArticleAdapter(Article.DiffCallback) { article ->
            Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()
        }
    }
    private val viewModel by viewModels<RetrofitCoroutineViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.list.adapter = mAdapter

        mBinding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val last = layoutManager.findLastCompletelyVisibleItemPosition()
                if (last == mAdapter.itemCount - 1) {
                    viewModel.getArticles(false)
                }
            }
        })
        viewModel.articles.observe(this, Observer { articles ->
            mAdapter.submitList(articles)
        })
        viewModel.status.observe(this, Observer { status ->
            when (status) {
                RequestStatus.FAILED -> {
                    mBinding.refresh.isRefreshing = false
                }
                RequestStatus.SUCCESS -> {
                    mBinding.refresh.isRefreshing = false
                }
                RequestStatus.REFRESH -> {
                    mBinding.refresh.isRefreshing = true
                }
                else -> mBinding.refresh.isRefreshing = false
            }
        })
        mBinding.refresh.setOnRefreshListener {
            viewModel.getArticles(true)
        }
        viewModel.getArticles(true)
    }

    fun loadMore() {

    }
}