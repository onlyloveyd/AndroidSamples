package tech.kicky.mavericks.sample.network

import retrofit2.http.GET
import tech.kicky.mavericks.sample.data.HotKey
import tech.kicky.mavericks.sample.data.Response

interface WanAndroidAPI {
    @GET("hotkey/json")
    suspend fun hotKey(): Response<List<HotKey>>
}