package cn.onlyloveyd.androidpractice.retrofitcoroutine

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit Instance
 *
 * @author yidong
 * @date 2020/7/5
 */
class Retrofitance() {
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val wanAndroidApi: WanAndroidApi by lazy {
        retrofit.create(WanAndroidApi::class.java)
    }

    companion object {
        val instance: Retrofitance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Retrofitance()
        }
    }
}