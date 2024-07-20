package co.assignment.detail.model


sealed class DetailSideEffect {
    data object OnUpdateFavoriteError : DetailSideEffect()

    data object OnSearchBookError: DetailSideEffect()
}