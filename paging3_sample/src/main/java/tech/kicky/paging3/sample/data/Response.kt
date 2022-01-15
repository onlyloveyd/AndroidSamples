package tech.kicky.paging3.sample.data

data class Response<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)

data class WanPager<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class Article(
    val id: String,
    val title: String,
    val link: String,
    val chapterName: String,
    val publishTime: Long
) {
    var date = ""
}