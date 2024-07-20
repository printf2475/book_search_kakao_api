package co.assignment.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import co.assignment.detail.BookDetailRoute
import co.assignment.model.BookInfo
import co.assignment.navigation.RouteModel

fun NavController.navigateBookDetail(
    bookInfo: BookInfo,
) {
    val query = bookInfo.isbn.takeIf { it.isNotEmpty() }?:bookInfo.title
    navigate(RouteModel.BookDetail(query))
}

fun NavGraphBuilder.bookDetailNavGraph(
    onClickBack: () -> Unit,
) {
    composable<RouteModel.BookDetail> {
        BookDetailRoute(
            onClickBack = onClickBack
        )
    }
}