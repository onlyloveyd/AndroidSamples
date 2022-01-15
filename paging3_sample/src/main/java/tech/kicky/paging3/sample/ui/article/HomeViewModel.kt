package tech.kicky.paging3.sample.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.kicky.paging3.sample.data.Article
import tech.kicky.paging3.sample.data.UiModel
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    fun getAuthorArticles(): Flow<PagingData<Article>> {
        return Repository.getAuthorArticles()
            .cachedIn(viewModelScope)
    }

    fun getHomeArticles(): Flow<PagingData<UiModel>> {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        return Repository.getHomeArticles()
            .map { pagingDate ->
                pagingDate.map {
                    it.date = format.format(it.publishTime)
                    UiModel.ArticleItem(it)
                }
            }
            .map {
                it.insertSeparators { before, after ->
                    if (after != null && before?.article?.date != after.article.date) {
                        UiModel.SeparatorItem(after.article.date)
                    } else {
                        null
                    }
                }
            }.cachedIn(viewModelScope)
    }

}
