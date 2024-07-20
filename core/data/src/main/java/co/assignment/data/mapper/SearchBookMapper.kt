package co.assignment.data.mapper

import co.assignment.data.api.model.Document
import co.assignment.model.BookInfo

fun Document.toBookInfo() = BookInfo(
    // isbn이 없는경우가 존재하여 고유값으로 사용되는 isbn을 title으로 설정
    isbn = isbn.split(" ").firstOrNull() ?: title,
    title = title,
    authors = authors,
    translators = translators,
    publisher = publisher,
    datetime = datetime,
    salePrice = salePrice,
    price = price,
    thumbnail = thumbnail,
    status = status,
    contents = contents,
    url = url,
    isFavorite = false
)