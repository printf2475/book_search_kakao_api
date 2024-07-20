package co.assignment.search.model

import co.assignment.model.BookInfo

sealed class SearchAction {

    data class OnClickFavorite(
        val bookInfo: BookInfo
    ): SearchAction()

    data class OnEditTextField(
        val searchText : String
    ) : SearchAction()
}