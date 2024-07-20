package co.assignment.favorite.mapper

import co.assignment.model.SortType

val SortType.title: String
    get() = when (this) {
        SortType.Asc -> "제목 오름차순"
        SortType.Desc -> "제목 내림차순"
    }
