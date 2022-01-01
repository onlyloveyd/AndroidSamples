package tech.kicky.mavericks.sample.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Retrofitance {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.SECONDS)
        .readTimeout(2, TimeUnit.SECONDS)
        .writeTimeout(2, TimeUnit.SECONDS)
        .build()

    private val retrofitance =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()


    val wanAndroidAPI: WanAndroidAPI = retrofitance.create(WanAndroidAPI::class.java)

}