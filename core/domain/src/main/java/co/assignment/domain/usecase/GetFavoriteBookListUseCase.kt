package co.assignment.domain.usecase

import android.util.Log
import co.assignment.domain.repository.BookRepository
import co.assignment.model.BookInfo
import co.assignment.model.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoriteBookListUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    companion object {
        private const val TAG: String = "GetFavoriteBookListUseCase"
    }

    suspend operator fun invoke(
        sortType: SortType,
        filterPriceRange: IntRange?
    ): Flow<List<BookInfo>> = withContext(Dispatchers.IO) {
        Log.d(TAG, "invoke sortType: ${sortType.name}, filterPriceRange: $filterPriceRange")
        runCatching {
            if (filterPriceRange == null) {
                return@withContext when (sortType) {
                    SortType.Asc -> bookRepository.getFavoriteBookListSortByAscTitle()
                    SortType.Desc -> bookRepository.getFavoriteBookListSortByDescTitle()
                }
            }

            when (sortType) {
                SortType.Asc -> bookRepository.getFavoriteBookListFilterPriceSortByAscTitle(
                    startPrice = filterPriceRange.first,
                    endPrice = filterPriceRange.last
                )

                SortType.Desc -> bookRepository.getFavoriteBookListFilterPriceSortByDescTitle(
                    startPrice = filterPriceRange.first,
                    endPrice = filterPriceRange.last
                )
            }
        }.getOrElse {
            Log.e(TAG, "Error - ${it.message}")
            emptyFlow()
        }
    }
}