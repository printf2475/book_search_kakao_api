package co.assignment.domain.usecase

import android.util.Log
import co.assignment.domain.repository.BookRepository
import co.assignment.model.BookInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateFavoriteBookListUseCase @Inject constructor(
    private val repository: BookRepository
) {
    companion object {
        private const val TAG: String = "UpdateFavoriteBookListUseCase"
    }

    suspend operator fun invoke(bookInfo: BookInfo): Boolean {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                if (!bookInfo.isFavorite) {
                    repository.insertFavoriteBook(bookInfo.copy(isFavorite = true))
                } else {
                    repository.deleteFavoriteBook(bookInfo)
                }

                return@runCatching true
            }.getOrElse {
                Log.e(TAG, "Error - ${it.message}")
                return@getOrElse false
            }
        }
    }
}