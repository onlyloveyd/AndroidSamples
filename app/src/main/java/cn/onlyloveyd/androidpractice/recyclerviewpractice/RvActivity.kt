package cn.onlyloveyd.androidpractice.recyclerviewpractice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.onlyloveyd.androidpractice.R
import cn.onlyloveyd.androidpractice.databinding.ActivityRvBinding

class RvActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRvBinding
    val viewModel: RvViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rv)
        mBinding.viewModel = viewModel

        val adapter = ArticleAdapter { article ->
            Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()
        }
        mBinding.list.adapter = adapter

        viewModel.articles.observe(this, Observer { articles ->
            adapter.submitList(articles)
        })
    }
}