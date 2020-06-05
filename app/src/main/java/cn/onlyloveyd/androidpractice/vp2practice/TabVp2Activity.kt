package cn.onlyloveyd.androidpractice.vp2practice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cn.onlyloveyd.androidpractice.R
import cn.onlyloveyd.androidpractice.databinding.ActivityTabVp2Binding
import com.google.android.material.tabs.TabLayoutMediator

class TabVp2Activity : AppCompatActivity() {

    private lateinit var mBinding: ActivityTabVp2Binding
    private val viewModel: Vp2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tab_vp2)
        mBinding.viewModel = viewModel
        val adapter = Vp2Adapter(this)
        mBinding.vp2.adapter = adapter

        val tabConfigurationStrategy =
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "first"
                    1 -> tab.text = "second"
                }
            }
        val tabLayoutMediator =
            TabLayoutMediator(mBinding.tab, mBinding.vp2, tabConfigurationStrategy)
        tabLayoutMediator.attach()
    }
}