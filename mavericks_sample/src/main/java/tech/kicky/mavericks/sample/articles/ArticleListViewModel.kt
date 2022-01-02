package tech.kicky.mavericks.sample.articles

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.Dispatchers
import tech.kicky.mavericks.sample.network.Retrofitance

class ArticleListViewModel(initState: ArticleListState) :
    MavericksViewModel<ArticleListState>(initState) {

    init {
        refresh()
    }

    fun refresh() = withState {
        if (it.request is Loading) return@withState

        setState { copy(isRefresh = true, nextPage = 0) }

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
                articles = (state()?.data?.datas ?: emptyList())
            )
        }

    }

    fun loadMore() = withState {
        if (it.request is Loading) return@withState

        setState { copy(isLoadMore = true) }

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