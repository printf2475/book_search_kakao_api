package co.assignment.data.repository

import co.assignment.data.api.BookSearchApi
import co.assignment.data.mapper.toBookInfo
import co.assignment.database.FavoriteBookInfoDao
import co.assignment.domain.repository.BookRepository
import co.assignment.model.BookInfo
import kotlinx.coroutines.flow.Flow

internal class BookRepositoryImpl(
    private val bookSearchApi: BookSearchApi,
    private val favoriteBookInfoDao: FavoriteBookInfoDao
) : BookRepository {

    override suspend fun getSearchBookList(
        title: String,
        page: Int,
        size: Int
    ): Pair<Boolean, List<BookInfo>> {
        val response = bookSearchApi.searchBooks(title, page, size)
        return response.meta.isEnd to response.documents.map { it.toBookInfo() }
    }

    override suspend fun insertFavoriteBook(bookInfo: BookInfo) =
        favoriteBookInfoDao.insertBookInfo(bookInfo)

    override suspend fun deleteFavoriteBook(bookInfo: BookInfo) =
        favoriteBookInfoDao.deleteBookInfoByIsbn(bookInfo.isbn)

    override suspend fun getFavoriteBookListSortByAscTitle(): Flow<List<BookInfo>> =
        favoriteBookInfoDao.getFavoriteBookListSortByAscTitle()

    override suspend fun getFavoriteBookListSortByDescTitle(): Flow<List<BookInfo>> =
        favoriteBookInfoDao.getFavoriteBookListSortByDescTitle()

    override suspend fun getFavoriteBookListFilterPriceSortByAscTitle(
        startPrice: Int, endPrice: Int
    ): Flow<List<BookInfo>> = favoriteBookInfoDao.getFavoriteBookListFilterPriceSortByAscTitle(
        startPrice, endPrice
    )

    override suspend fun getFavoriteBookListFilterPriceSortByDescTitle(
        startPrice: Int,
        endPrice: Int
    ): Flow<List<BookInfo>> = favoriteBookInfoDao.getFavoriteBookListFilterPriceSortByDescTitle(
        startPrice, endPrice
    )
}