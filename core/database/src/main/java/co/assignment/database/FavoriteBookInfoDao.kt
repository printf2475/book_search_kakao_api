package co.assignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.assignment.model.BookInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookInfoDao {
    @Insert
    suspend fun insertBookInfo(bookInfo: BookInfo)

    @Query("SELECT * FROM bookInfo")
    fun getAllBookInfo(): Flow<List<BookInfo>>

    @Query("DELETE FROM bookInfo WHERE isbn = :isbn")
    fun deleteBookInfoByIsbn(isbn: String)

    @Query("SELECT * FROM bookInfo ORDER BY title ASC")
    fun getFavoriteBookListSortByAscTitle() : Flow<List<BookInfo>>

    @Query("SELECT * FROM bookInfo ORDER BY title DESC")
    fun getFavoriteBookListSortByDescTitle() : Flow<List<BookInfo>>

    @Query("SELECT * FROM bookInfo WHERE salePrice BETWEEN :startPrice AND :endPrice ORDER BY title ASC")
    fun getFavoriteBookListFilterPriceSortByAscTitle(startPrice: Int, endPrice: Int) : Flow<List<BookInfo>>

    @Query("SELECT * FROM bookInfo WHERE salePrice BETWEEN :startPrice AND :endPrice ORDER BY title DESC")
    fun getFavoriteBookListFilterPriceSortByDescTitle(startPrice: Int, endPrice: Int) : Flow<List<BookInfo>>
}