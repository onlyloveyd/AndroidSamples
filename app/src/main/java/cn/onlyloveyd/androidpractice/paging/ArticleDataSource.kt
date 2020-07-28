package cn.onlyloveyd.androidpractice.paging

import androidx.paging.PagingSource
import cn.onlyloveyd.androidpractice.data.Article
import cn.onlyloveyd.androidpractice.retrofitcoroutine.Retrofitance
import retrofit2.HttpException
import java.io.IOException

/**
 * 文章数据源
 *
 * @author yidong
 * @date 2020/7/23
 */
class ArticleDataSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            //获取网络数据
            val result = Retrofitance.instance.wanAndroidApi.getHomeArticles(page)
            LoadResult.Page(
                //需要加载的数据
                data = result.data.data,
                //如果可以往上加载更多就设置该参数，否则不设置
                prevKey = null,
                //加载下一页的key 如果传null就说明到底了
                nextKey = if (result.data.curPage == result.data.pageCount) null else page + 1
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }
}