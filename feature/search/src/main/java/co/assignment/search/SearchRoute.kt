package co.assignment.search

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import co.assignment.model.BookInfo
import co.assignment.search.model.SearchSideEffect
import co.assignment.search.screen.SearchScreen

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    onClickBookInfo: (BookInfo) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bookPagingData = viewModel.bookInfoPagingDataFlow.collectAsLazyPagingItems()
    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                SearchSideEffect.OnUpdateFavoriteError -> {
                    Toast.makeText(context, "Favorite Update Fail", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    SearchScreen(
        searchText = uiState.searchText,
        bookPagingData = bookPagingData,
        onAction = viewModel::onAction,
        onClickBookInfo = onClickBookInfo
    )
}