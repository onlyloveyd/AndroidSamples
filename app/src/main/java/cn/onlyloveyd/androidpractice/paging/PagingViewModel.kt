package cn.onlyloveyd.androidpractice.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import cn.onlyloveyd.androidpractice.retrofitcoroutine.WanAndroidApi
import cn.onlyloveyd.androidpractice.room.AppDatabase

/**
 * 分页文章View Model
 *
 * @author yidong
 * @date 2020/7/23
 */
@ExperimentalPagingApi
class PagingViewModel : ViewModel() {
    fun getArticleData(api: WanAndroidApi, database: AppDatabase) =
        Pager(PagingConfig(pageSize = 10)) {
            ArticleRemoteMediator(
                api,
                database
            )
            database.articleDao().getArticles()
        }.flow.cachedIn(viewModelScope)
}