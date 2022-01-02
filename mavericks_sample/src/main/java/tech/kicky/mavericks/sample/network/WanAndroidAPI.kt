package tech.kicky.mavericks.sample.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import tech.kicky.mavericks.sample.data.Article
import tech.kicky.mavericks.sample.data.HotKey
import tech.kicky.mavericks.sample.data.Pager
import tech.kicky.mavericks.sample.data.Response

interface WanAndroidAPI {
    @GET("hotkey/json")
    suspend fun hotKey(): Response<List<HotKey>>

    @POST("article/query/{pageNum}/json")
    suspend fun searchArticles(@Path("pageNum") pageNum: Int, @Query("k") keyword: String)
            : Response<Pager<Article>>
}