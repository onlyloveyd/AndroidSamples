package tech.kicky.mavericks.sample.data

/**
 * Response Data Class
 * author: yidong
 * 2022-01-01
 */
data class Response<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)

data class HotKey(
    val id: String,
    val link: String,
    val name: String,
    val order: String,
    val visible: String,
)