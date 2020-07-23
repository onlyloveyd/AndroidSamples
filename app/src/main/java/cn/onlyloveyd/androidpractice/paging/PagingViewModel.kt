package cn.onlyloveyd.androidpractice.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

/**
 * 分页文章View Model
 *
 * @author yidong
 * @date 2020/7/23
 */
class PagingViewModel : ViewModel() {
    private val repository: ArticleRepository by lazy { ArticleRepository() }

    fun getArticleData() = repository.getArticleData().asLiveData()

}