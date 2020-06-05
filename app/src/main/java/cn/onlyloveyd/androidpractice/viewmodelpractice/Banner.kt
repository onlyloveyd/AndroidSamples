package cn.onlyloveyd.androidpractice.viewmodelpractice

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.JsonClass

/**
 * 轮播图
 *
 * @author yidong
 * @date 2020/6/4
 */
@JsonClass(generateAdapter = true)
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Banner>() {
            override fun areItemsTheSame(oldItem: Banner, newItem: Banner) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Banner, newItem: Banner) =
                oldItem == newItem
        }
    }
}

@JsonClass(generateAdapter = true)
data class WanResp<T>(
    val errorMsg: String,
    val errorCode: Int,
    val data: List<T>
)