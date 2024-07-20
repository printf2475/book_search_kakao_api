package co.assignment.search.model

sealed class SearchSideEffect {
    data object OnUpdateFavoriteError : SearchSideEffect()
}