package cn.onlyloveyd.androidpractice.retrofitcoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.onlyloveyd.androidpractice.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Retrofit Coroutine View Model
 *
 * @author yidong
 * @date 2020/7/5
 */
class RetrofitCoroutineViewModel : ViewModel() {
    val articles = MutableLiveData<List<Article>>()
    val status = MutableLiveData<RequestStatus>()
    val message = MutableLiveData<String>()
    val pageNum = MutableLiveData(0)

    fun getArticles(isRefresh: Boolean) {
        if (isRefresh) {
            pageNum.value == 0
        }
//        status.value = RequestStatus.START
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val resp =
                        Retrofitance.instance.wanAndroidApi.getHomeArticles(pageNum.value ?: 0)
                    if (resp.errorCode == 0) {
                        status.postValue(RequestStatus.SUCCESS)
                        if (pageNum.value == 0) {
                            articles.postValue(resp.data.data)
                        } else {
                            val list = mutableListOf<Article>()
                            list.addAll(articles.value!!)
                            list.addAll(resp.data.data)
                            articles.postValue(list)
                        }
                        pageNum.postValue(pageNum.value?.plus(1))
                    } else {
                        status.postValue(RequestStatus.FAILED)
                        message.postValue(resp.errorMsg)
                    }
                }
            } catch (e: Exception) {
                status.postValue(RequestStatus.FAILED)
                message.postValue(e.message)
            }
        }
    }
}