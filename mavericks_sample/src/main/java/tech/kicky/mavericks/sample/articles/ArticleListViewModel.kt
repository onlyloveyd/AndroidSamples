package tech.kicky.mavericks.sample.articles

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.Success
import kotlinx.coroutines.Dispatchers
import tech.kicky.mavericks.sample.network.Retrofitance

class ArticleListViewModel(initState: ArticleListState) :
    MavericksViewModel<ArticleListState>(initState) {

    init {
        refresh()
    }

    fun refresh() {

        setState { copy(isRefresh = true, nextPage = 0) }

        withState {
            if (it.request is Loading) return@withState

            suspend {
                Retrofitance.wanAndroidAPI
                    .searchArticles(keyword = it.keyword, pageNum = it.nextPage)
            }.execute(Dispatchers.IO) { state ->
                copy(
                    nextPage = if (state is Success) nextPage.plus(1) else nextPage,
                    request = state,
                    isRefresh = false,
                    isLoadMore = false,
                    isLoadMoreCompleted = state()?.data?.over ?: false,
                    articles = (state()?.data?.datas ?: emptyList())
                )
            }
        }
    }

    fun loadMore() {
        setState { copy(isLoadMore = true) }
        withState {
            if (it.request is Loading) return@withState
            if (it.isLoadMoreCompleted) return@withState

            suspend {
                Retrofitance.wanAndroidAPI
                    .searchArticles(keyword = it.keyword, pageNum = it.nextPage)
            }.execute(Dispatchers.IO) { state ->
                copy(
                    nextPage = nextPage.plus(1),
                    request = state,
                    isRefresh = false,
                    isLoadMore = false,
                    isLoadMoreCompleted = state()?.data?.over ?: false,
                    articles = articles + (state()?.data?.datas ?: emptyList())
                )
            }
        }
    }
}