package cn.onlyloveyd.androidpractice.extension

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.onlyloveyd.androidpractice.viewmodelpractice.RefreshViewModel
import coil.api.load

/**
 * 自定义Binding
 *
 * @author yidong
 * @date 2020/6/5
 */
@BindingAdapter("OnRefresh")
fun refresh(refresh: SwipeRefreshLayout, viewModel: RefreshViewModel) {
    refresh.setOnRefreshListener { viewModel.getBanner() }
}


@BindingAdapter("imgUrl")
fun showImg(imageView: AppCompatImageView, url: String) {
    imageView.load(url)
}