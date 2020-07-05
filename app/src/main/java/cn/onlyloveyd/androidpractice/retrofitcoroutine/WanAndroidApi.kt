package cn.onlyloveyd.androidpractice.retrofitcoroutine

import cn.onlyloveyd.androidpractice.data.Article
import cn.onlyloveyd.androidpractice.data.BaseResp
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * WanAndroid API
 *
 * @author yidong
 * @date 2020/7/5
 */
interface WanAndroidApi {
    @GET("article/list/{pageNum}/json")
    suspend fun getHomeArticles(@Path("pageNum") pageNum: Int): BaseResp<Article>
}