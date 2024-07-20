package co.assignment.navigation

import kotlinx.serialization.Serializable

sealed interface RouteModel {
    @Serializable
    data class BookDetail(
        val query: String
    ) : RouteModel
}

sealed interface MainTabRoute : RouteModel {
    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object Favorite : MainTabRoute
}