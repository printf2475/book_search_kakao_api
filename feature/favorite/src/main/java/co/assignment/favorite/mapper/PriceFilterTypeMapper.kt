package co.assignment.favorite.mapper

import co.assignment.favorite.model.PriceFilterType

val PriceFilterType.title: String
    get() = when (this) {
        PriceFilterType.Under10 -> "만원 이하"
        PriceFilterType.Between12To14 -> "12000 ~ 14000 원"
        PriceFilterType.Between14To16 -> "14000 ~ 16000 원"
        PriceFilterType.Between16To18 -> "16000 ~ 18000 원"
        PriceFilterType.Between18To20 -> "18000 ~ 20000 원"
        PriceFilterType.Over20 -> "2만원 이상"
    }

val PriceFilterType.range: IntRange
    get() = when (this) {
        PriceFilterType.Under10 -> IntRange(Int.MIN_VALUE, 10000)
        PriceFilterType.Between12To14 -> IntRange(12000, 14000)
        PriceFilterType.Between14To16 -> IntRange(14000, 16000)
        PriceFilterType.Between16To18 -> IntRange(16000, 18000)
        PriceFilterType.Between18To20 -> IntRange(16000, 18000)
        PriceFilterType.Over20 -> IntRange(20000, Int.MAX_VALUE)
    }