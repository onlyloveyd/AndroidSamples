package cn.onlyloveyd.androidpractice.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

/**
 * 分页文章View Model
 *
 * @author yidong
 * @date 2020/7/23
 */
class PagingViewModel : ViewModel() {
    fun getArticleData() = Pager(PagingConfig(pageSize = 10)) {
        ArticleDataSource()
    }.flow.cachedIn(viewModelScope)
}