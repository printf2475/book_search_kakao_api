package co.assignment.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.assignment.domain.repository.BookRepository
import co.assignment.model.BookInfo

class SearchBookPagingSource(
    private val repository: BookRepository,
    private val title: String,
) : PagingSource<Int, BookInfo>() {

    companion object {
        private const val FIRST_PAGE_INDEX = 1
        const val PAGING_SIZE = 20
        private const val TAG: String = "SearchBookPagingSource"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookInfo> {
        val page = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response: Pair<Boolean, List<BookInfo>> = repository.getSearchBookList(
                title = title, page = page, size = PAGING_SIZE
            )
            LoadResult.Page(
                data = response.second,
                prevKey = (page - 1).takeIf { page != FIRST_PAGE_INDEX },
                nextKey = (page + params.loadSize / PAGING_SIZE).takeIf { !response.first }
            )
        } catch (exception: Exception) {
            Log.e(TAG, "Error - ${exception.message}")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BookInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
