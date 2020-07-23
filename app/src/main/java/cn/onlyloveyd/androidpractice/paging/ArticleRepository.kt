package cn.onlyloveyd.androidpractice.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * 文章仓库
 *
 * @author yidong
 * @date 2020/7/23
 */
class ArticleRepository {
    fun getArticleData() = Pager(PagingConfig(pageSize = 10)) {
        ArticleDataSource()
    }.flow
}