package tech.kicky.mavericks.sample.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofitance {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    val retrofitance =
        Retrofit.Builder()
            .baseUrl("https://icanhazdadjoke.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()


    val api = retrofitance.create(WanAndroidAPI::class.java)

}