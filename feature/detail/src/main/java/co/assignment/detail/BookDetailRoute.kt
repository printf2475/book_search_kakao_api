package co.assignment.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.assignment.detail.model.DetailSideEffect
import co.assignment.detail.screen.BookDetailScreen


@Composable
fun BookDetailRoute(
    viewModel: BookDetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                DetailSideEffect.OnSearchBookError -> {
                    Toast.makeText(context, "Book Load Failed", Toast.LENGTH_SHORT).show()
                }

                DetailSideEffect.OnUpdateFavoriteError -> {
                    Toast.makeText(context, "Favorite Update Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    BookDetailScreen(
        bookInfo = uiState.bookInfo,
        onActions = viewModel::onAction,
        onClickBack = onClickBack
    )
}