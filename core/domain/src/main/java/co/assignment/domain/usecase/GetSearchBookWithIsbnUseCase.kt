package co.assignment.domain.usecase

import android.util.Log
import co.assignment.domain.repository.BookRepository
import co.assignment.model.BookInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSearchBookWithIsbnUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    companion object {
        private const val TAG: String = "GetSearchBookWithIsbnUseCase"
    }

    suspend operator fun invoke(
        isbn: String,
    ): BookInfo? = withContext(Dispatchers.IO) {
        runCatching {
            val bookInfo = bookRepository.getSearchBookList(isbn, 1, 1)
                .second.firstOrNull() ?: return@withContext null
            val favoriteBookInfoList = bookRepository.getFavoriteBookListSortByAscTitle()
                .firstOrNull() ?: return@withContext null

            return@withContext if (bookInfo.isbn in favoriteBookInfoList.map { it.isbn }) {
                bookInfo.copy(isFavorite = true)
            } else bookInfo
        }.getOrElse {
            Log.e(TAG, "Error - ${it.message}")
            return@withContext null
        }
    }
}