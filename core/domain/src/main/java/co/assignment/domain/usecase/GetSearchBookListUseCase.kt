package co.assignment.domain.usecase

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.assignment.domain.paging.SearchBookPagingSource
import co.assignment.domain.paging.SearchBookPagingSource.Companion.PAGING_SIZE
import co.assignment.domain.repository.BookRepository
import co.assignment.model.BookInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSearchBookListUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    companion object {
        private const val TAG: String = "GetSearchBookListUseCase"
    }

    suspend operator fun invoke(
        title: String,
        scope: CoroutineScope
    ): Flow<PagingData<BookInfo>> {
        Log.d(TAG, "invoke")
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val favoriteBookListFlow = bookRepository.getFavoriteBookListSortByAscTitle()
                val pagingBookListFlow = Pager(
                    config = PagingConfig(
                        pageSize = PAGING_SIZE,
                        enablePlaceholders = false,
                    ),
                    pagingSourceFactory = { SearchBookPagingSource(bookRepository, title) }
                ).flow

                return@runCatching pagingBookListFlow
                    .cachedIn(scope)
                    .combine(favoriteBookListFlow) { pagingBookList, favoriteBookList ->
                        pagingBookList.map {
                            if (it.isbn in favoriteBookList.map { it.isbn }) {
                                it.copy(isFavorite = true)
                            } else it
                        }
                    }
            }.getOrElse {
                Log.e(TAG, "Error - ${it.message}")
                return@getOrElse emptyFlow()
            }
        }
    }
}

