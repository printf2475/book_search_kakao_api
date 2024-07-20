package co.assignment.favorite

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.assignment.favorite.model.FavoriteSideEffect
import co.assignment.favorite.screen.FavoriteScreen
import co.assignment.model.BookInfo
import kotlinx.collections.immutable.toPersistentList

@Composable
fun FavoriteRoute(
    viewModel: FavoriteBookViewModel = hiltViewModel(),
    onClickBookInfo: (BookInfo) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                FavoriteSideEffect.OnUpdateFavoriteError -> {
                    Toast.makeText(context, "Favorite Update Fail", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    FavoriteScreen(
        bookInfoList = uiState.bookInfoList.toPersistentList(),
        sortType = uiState.sortType,
        priceFilterType = uiState.priceFilterType,
        isVisibilitySortBottomSheet = uiState.isVisibilitySortBottomSheetVisibility,
        isVisibilityPriceFilterBottomSheet = uiState.isVisibilityPriceFilterBottomSheetVisibility,
        onAction = viewModel::onAction,
        onClickBookInfo = onClickBookInfo
    )
}