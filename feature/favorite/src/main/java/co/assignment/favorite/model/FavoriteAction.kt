package co.assignment.favorite.model

import co.assignment.model.BookInfo
import co.assignment.model.SortType

sealed class FavoriteAction {

    data class OnClickFavorite(
        val bookInfo: BookInfo
    ): FavoriteAction()

    data class OnChangeSortType(
        val sortType: SortType
    ): FavoriteAction()

    data class OnChangeFilterPrice(
        val priceFilterType: PriceFilterType?
    ): FavoriteAction()

    data class OnChangeSortBottomSheetVisibility(
        val isVisibility: Boolean
    ) : FavoriteAction()

    data class OnChangePriceFilterBottomSheetVisibility(
        val isVisibility: Boolean
    ) : FavoriteAction()
}