package co.assignment.favorite.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.assignment.favorite.model.FavoriteAction
import co.assignment.favorite.component.FavoriteFilterButtonsContent
import co.assignment.favorite.component.PriceFilterBottomSheetContent
import co.assignment.favorite.component.SortBottomSheetContent
import co.assignment.favorite.model.PriceFilterType
import co.assignment.model.BookInfo
import co.assignment.model.SortType
import co.assignment.ui.component.BookInfoContent
import co.assignment.ui.component.EmptyContent
import co.assignment.ui.component.EmptyContentType
import kotlinx.collections.immutable.ImmutableList

@Composable
fun FavoriteScreen(
    bookInfoList: ImmutableList<BookInfo>,
    sortType: SortType,
    priceFilterType: PriceFilterType?,
    isVisibilitySortBottomSheet: Boolean,
    isVisibilityPriceFilterBottomSheet: Boolean,
    onAction: (FavoriteAction) -> Unit,
    onClickBookInfo: (BookInfo) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                FavoriteFilterButtonsContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    sortType = sortType,
                    priceFilterType = priceFilterType,
                    onClickSort = {
                        onAction(FavoriteAction.OnChangeSortBottomSheetVisibility(true))
                    },
                    onClickPriceFilter = {
                        onAction(FavoriteAction.OnChangePriceFilterBottomSheetVisibility(true))
                    }
                )
            }
        ) {
            if (bookInfoList.isEmpty()) {
                EmptyContent(EmptyContentType.Favorite)
            } else {
                LazyColumn(
                    modifier = Modifier.padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(bookInfoList) { bookInfo ->
                        BookInfoContent(
                            modifier = Modifier.fillMaxWidth(),
                            title = bookInfo.title,
                            authors = bookInfo.authors,
                            salePrice = bookInfo.salePrice,
                            price = bookInfo.price,
                            thumbnail = bookInfo.thumbnail,
                            contents = bookInfo.contents,
                            isFavorite = bookInfo.isFavorite,
                            onClick = {
                                onClickBookInfo(bookInfo)
                            },
                            onFavoriteClick = {
                                onAction(FavoriteAction.OnClickFavorite(bookInfo))
                            }
                        )
                    }
                }
            }
        }

        SortBottomSheetContent(
            isVisibilitySortBottomSheet = isVisibilitySortBottomSheet,
            sortType = sortType,
            onCloseBottomSheet = {
                onAction(FavoriteAction.OnChangeSortBottomSheetVisibility(false))
            },
            onSelectSortType = {
                onAction(FavoriteAction.OnChangeSortType(it))
            }
        )

        PriceFilterBottomSheetContent(
            isVisibilityPriceFilterBottomSheet = isVisibilityPriceFilterBottomSheet,
            filterType = priceFilterType,
            onCloseBottomSheet = {
                onAction(FavoriteAction.OnChangePriceFilterBottomSheetVisibility(false))
            },
            onSelectPriceFilterType = {
                onAction(FavoriteAction.OnChangeFilterPrice(it))
            }
        )
    }
}