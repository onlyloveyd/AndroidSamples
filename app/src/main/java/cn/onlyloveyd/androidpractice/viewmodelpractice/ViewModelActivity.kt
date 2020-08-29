package cn.onlyloveyd.androidpractice.viewmodelpractice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.onlyloveyd.androidpractice.R
import cn.onlyloveyd.androidpractice.databinding.ActivityViewModelBinding

/**
 * ViewModel
 *
 * @author yidong
 * @date 2020/6/4
 */
class ViewModelActivity : AppCompatActivity() {
    private val viewModel: RefreshViewModel by viewModels { RefreshVMFactory() }
    private lateinit var mBinding: ActivityViewModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_model)
        mBinding.viewModel = viewModel
        val adapter = BannerAdapter { banner ->
            Toast.makeText(this, "banner: ${banner.title}", Toast.LENGTH_SHORT).show()
        }
        mBinding.list.adapter = adapter

        viewModel.bannerLiveData.observe(this, Observer { banner ->
            mBinding.refresh.isRefreshing = false
            adapter.submitList(banner)
        })

        mBinding.refresh.isRefreshing = true
        viewModel.getBanner()
    }
}