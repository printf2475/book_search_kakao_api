package co.assignment.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import co.assignment.model.BookInfo
import co.assignment.navigation.MainTabRoute
import co.assignment.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(MainTabRoute.Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    onClickBookInfo: (BookInfo) -> Unit
) {
    composable<MainTabRoute.Search> {
        SearchRoute(
            onClickBookInfo = onClickBookInfo
        )
    }
}