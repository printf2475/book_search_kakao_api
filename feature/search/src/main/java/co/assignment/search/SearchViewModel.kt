package co.assignment.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.assignment.domain.usecase.GetSearchBookListUseCase
import co.assignment.domain.usecase.UpdateFavoriteBookListUseCase
import co.assignment.model.BookInfo
import co.assignment.search.model.SearchAction
import co.assignment.search.model.SearchSideEffect
import co.assignment.search.model.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getSearchBookListUseCase: GetSearchBookListUseCase,
    val updateFavoriteBookListUseCase: UpdateFavoriteBookListUseCase
) : ViewModel() {

    companion object {
        private const val DEBOUNCE_MILLIS = 500L
        private const val TAG = "SearchViewModel"
    }

    private val _sideEffect: Channel<SearchSideEffect> = Channel()
    val sideEffect: Flow<SearchSideEffect> = _sideEffect.receiveAsFlow()

    var uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
        private set

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val bookInfoPagingDataFlow = uiState.asStateFlow()
        .debounce(DEBOUNCE_MILLIS)
        .map { it.searchText }
        .distinctUntilChanged()
        .flatMapLatest { searchText ->
            if (searchText.trim().isEmpty()) {
                return@flatMapLatest flow<PagingData<BookInfo>> {
                    emit(PagingData.empty())
                }.cachedIn(viewModelScope)
            } else {
                getSearchBookListUseCase(searchText, viewModelScope)
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PagingData.empty())

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnClickFavorite -> onUpdateFavorite(action.bookInfo)
            is SearchAction.OnEditTextField -> onEditTextField(action.searchText)
        }
    }

    private fun onUpdateFavorite(bookInfo: BookInfo) = viewModelScope.launch {
        Log.d(TAG, "onUpdateFavorite $bookInfo")
        val isSucceed = updateFavoriteBookListUseCase(bookInfo)
        if (!isSucceed) {
            _sideEffect.send(SearchSideEffect.OnUpdateFavoriteError)
        }
    }

    private fun onEditTextField(text: String) = viewModelScope.launch {
        Log.d(TAG, "onEditTextField $text")
        uiState.update { it.copy(searchText = text) }
    }
}