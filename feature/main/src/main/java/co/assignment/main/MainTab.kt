package co.assignment.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import co.assignment.navigation.MainTabRoute
import co.assignment.navigation.RouteModel

internal enum class MainTab(
    internal val icon: ImageVector,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {

    Search(
        icon = Icons.Filled.Search,
        contentDescription = "검색",
        MainTabRoute.Search
    ),
    Favorite(
        icon = Icons.Default.Favorite,
        contentDescription = "찜",
        MainTabRoute.Favorite,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (RouteModel) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}