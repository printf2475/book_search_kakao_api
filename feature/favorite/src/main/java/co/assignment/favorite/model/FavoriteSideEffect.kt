package co.assignment.favorite.model

sealed class FavoriteSideEffect {
    data object OnUpdateFavoriteError : FavoriteSideEffect()
}