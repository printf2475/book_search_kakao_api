package co.assignment.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import co.assignment.favorite.FavoriteRoute
import co.assignment.model.BookInfo
import co.assignment.navigation.MainTabRoute

fun NavController.navigateFavorite(navOptions: NavOptions) {
    navigate(MainTabRoute.Favorite, navOptions)
}

fun NavGraphBuilder.favoriteNavGraph(
    onClickBookInfo: (BookInfo) -> Unit
) {
    composable<MainTabRoute.Favorite> {
        FavoriteRoute(
            onClickBookInfo = onClickBookInfo
        )
    }
}