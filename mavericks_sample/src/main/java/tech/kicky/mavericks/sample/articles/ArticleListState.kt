package tech.kicky.mavericks.sample.articles

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import tech.kicky.mavericks.sample.data.Article
import tech.kicky.mavericks.sample.data.Pager
import tech.kicky.mavericks.sample.data.Response

data class ArticleListState(
    val articles: List<Article> = emptyList(),
    val request: Async<Response<Pager<Article>>> = Uninitialized,
    val keyword: String,
    val nextPage: Int = 0,
    val isRefresh: Boolean = false,
    val isLoadMore: Boolean = false,
    val isLoadMoreCompleted: Boolean = false,
) : MavericksState {
    constructor(args: Args) : this(keyword = args.keyword)
}