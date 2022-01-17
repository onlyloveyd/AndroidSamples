package tech.kicky.paging3.sample.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import tech.kicky.paging3.sample.Repository
import tech.kicky.paging3.sample.data.Article

class HomeViewModel : ViewModel() {

    fun getPagingData(): Flow<PagingData<Article>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
    }
}