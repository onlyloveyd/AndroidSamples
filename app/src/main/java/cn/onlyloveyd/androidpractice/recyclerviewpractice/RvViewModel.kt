package cn.onlyloveyd.androidpractice.recyclerviewpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.onlyloveyd.androidpractice.viewmodelpractice.WanResp
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * Rv Practice ViewModel
 *
 * @author yidong
 * @date 2020/6/6
 */
class RvViewModel : ViewModel() {
    val articles = MutableLiveData<List<Article>>()
    val moshi = Moshi.Builder().build()

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val url = URL("https://www.wanandroid.com/article/list/0/json?cid=60")
            val connection = url.openConnection()
            val inputStream = connection.getInputStream()
            val inputStreamReader = InputStreamReader(inputStream, "utf-8");
            val br = BufferedReader(inputStreamReader);
            val sb = StringBuffer()
            var line = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }
            val result = sb.toString();
            val wrapType = Types.newParameterizedType(WanResp::class.java, Article::class.java)
            val adapter = moshi.adapter<WanResp<Article>>(wrapType)
            val list: WanResp<Article>? = adapter.fromJson(result)
            list?.let {
                articles.postValue(it.data)
            }
        }
    }

    fun loadMore() {

    }
}