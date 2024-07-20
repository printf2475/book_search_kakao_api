package co.assignment.favorite.model

import co.assignment.model.BookInfo
import co.assignment.model.SortType

data class FavoriteUiState(
    val bookInfoList: List<BookInfo> = emptyList(),
    val sortType: SortType = SortType.Asc,
    val priceFilterType: PriceFilterType? = null,
    val isVisibilitySortBottomSheetVisibility: Boolean = false,
    val isVisibilityPriceFilterBottomSheetVisibility: Boolean = false,
)
