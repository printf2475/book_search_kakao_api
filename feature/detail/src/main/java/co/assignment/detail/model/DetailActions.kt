package co.assignment.detail.model


sealed class DetailActions {
    data object OnClickFavorite : DetailActions()
}