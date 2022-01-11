package tech.kicky.paging3.sample.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import tech.kicky.paging3.sample.data.Article
import tech.kicky.paging3.sample.data.Response
import tech.kicky.paging3.sample.data.WanPager
import java.util.concurrent.TimeUnit

interface WanAndroidService {

    @GET("article/list/{pageNum}/json")
    suspend fun searchRepos(@Path("pageNum") page: Int): Response<WanPager<Article>>

    companion object {
        private const val BASE_URL = "https://www.wanandroid.com/"

        private val client = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()

        fun create(): WanAndroidService {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanAndroidService::class.java)
        }
    }

}
