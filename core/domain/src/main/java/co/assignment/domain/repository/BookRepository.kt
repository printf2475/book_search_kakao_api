package co.assignment.domain.repository

import co.assignment.model.BookInfo
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getSearchBookList(
        title: String,
        page: Int,
        size: Int
    ): Pair<Boolean, List<BookInfo>>

    suspend fun insertFavoriteBook(bookInfo: BookInfo)

    suspend fun deleteFavoriteBook(bookInfo: BookInfo)

    suspend fun getFavoriteBookListSortByAscTitle(): Flow<List<BookInfo>>

    suspend fun getFavoriteBookListSortByDescTitle(): Flow<List<BookInfo>>

    suspend fun getFavoriteBookListFilterPriceSortByAscTitle(
        startPrice: Int,
        endPrice: Int
    ): Flow<List<BookInfo>>

    suspend fun getFavoriteBookListFilterPriceSortByDescTitle(
        startPrice: Int,
        endPrice: Int
    ): Flow<List<BookInfo>>

}