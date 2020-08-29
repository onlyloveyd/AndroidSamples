package cn.onlyloveyd.androidpractice.viewmodelpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL


/**
 * 刷新列表View Model
 *
 * @author yidong
 * @date 2020/6/4
 */
class RefreshViewModel(val param: String) : ViewModel() {
    val bannerLiveData = MutableLiveData<List<Banner>>()
    val moshi = Moshi.Builder().build()

    fun getBanner() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url = URL("https://www.wanandroid.com/banner/json")
                val connection = url.openConnection()
                val inputStream = connection.getInputStream()
                val inputStreamReader = InputStreamReader(inputStream, "utf-8")
                val br = BufferedReader(inputStreamReader)
                val sb = StringBuffer()
                var line = br.readLine()
                while (line != null) {
                    sb.append(line)
                    line = br.readLine()
                }
                val result = sb.toString()
                val wrapType = Types.newParameterizedType(WanResp::class.java, Banner::class.java)
                val adapter = moshi.adapter<WanResp<Banner>>(wrapType)
                val banners: WanResp<Banner>? = adapter.fromJson(result)
                banners?.let {
                    bannerLiveData.postValue(it.data)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}