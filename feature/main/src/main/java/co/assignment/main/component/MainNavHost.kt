package co.assignment.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import co.assignment.detail.navigation.bookDetailNavGraph
import co.assignment.favorite.navigation.favoriteNavGraph
import co.assignment.main.MainNavigator
import co.assignment.search.navigation.searchNavGraph


@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            searchNavGraph(
                onClickBookInfo = {
                    navigator.navigateBookDetail(it)
                }
            )
            favoriteNavGraph(
                onClickBookInfo = {
                    navigator.navigateBookDetail(it)
                }
            )
            bookDetailNavGraph(
                onClickBack = navigator::popBackStackIfNotHome
            )
        }
    }
}