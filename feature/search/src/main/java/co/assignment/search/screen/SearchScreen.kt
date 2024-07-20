package co.assignment.search.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import co.assignment.model.BookInfo
import co.assignment.search.model.SearchAction
import co.assignment.ui.component.BookInfoContent
import co.assignment.ui.component.EmptyContent
import co.assignment.ui.component.EmptyContentType

@Composable
fun SearchScreen(
    searchText: String,
    bookPagingData: LazyPagingItems<BookInfo>,
    onAction: (SearchAction) -> Unit,
    onClickBookInfo: (BookInfo) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = {
                    onAction(SearchAction.OnEditTextField(it))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (bookPagingData.itemCount == 0) {
                EmptyContent(EmptyContentType.Search)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    items(bookPagingData.itemCount) {
                        val bookInfo = bookPagingData[it] ?: return@items
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
                                onAction(SearchAction.OnClickFavorite(bookInfo))
                            }
                        )
                    }
                }
            }
        }

        if (bookPagingData.loadState.refresh == LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}