package tech.kicky.paging3.sample.data

/**
 * Ui Model
 * author: yidong
 * 2022-01-15
 */
sealed class UiModel {
    data class ArticleItem(val article: Article) : UiModel()
    data class SeparatorItem(val date: String) : UiModel()
}