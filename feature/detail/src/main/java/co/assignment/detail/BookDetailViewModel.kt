package co.assignment.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import co.assignment.detail.model.DetailActions
import co.assignment.detail.model.DetailSideEffect
import co.assignment.detail.model.DetailUiState
import co.assignment.domain.usecase.GetSearchBookWithIsbnUseCase
import co.assignment.domain.usecase.UpdateFavoriteBookListUseCase
import co.assignment.navigation.RouteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSearchBookWithIsbnUseCase: GetSearchBookWithIsbnUseCase,
    private val updateFavoriteBookListUseCase: UpdateFavoriteBookListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "BookDetailViewModel"
    }

    private val _sideEffect: Channel<DetailSideEffect> = Channel()
    val sideEffect: Flow<DetailSideEffect> = _sideEffect.receiveAsFlow()

    var uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
        private set

    init {
        val query = savedStateHandle.toRoute<RouteModel.BookDetail>().query
        Log.d(TAG, "init: $query")
        onSearchBook(query)
    }

    fun onAction(actions: DetailActions) {
        when (actions) {
            DetailActions.OnClickFavorite -> onUpdateFavorite()
        }
    }


    private fun onSearchBook(isbn: String) = viewModelScope.launch {
        val bookInfo = getSearchBookWithIsbnUseCase(isbn)
        if (bookInfo == null) {
            _sideEffect.send(DetailSideEffect.OnSearchBookError)
            return@launch
        }

        uiState.update {
            it.copy(bookInfo = bookInfo)
        }
    }

    private fun onUpdateFavorite() = viewModelScope.launch {
        Log.d(TAG, "onUpdateFavorite ${uiState.value}")
        val isSucceed = updateFavoriteBookListUseCase(uiState.value.bookInfo ?: return@launch)
        if (!isSucceed) {
            _sideEffect.send(DetailSideEffect.OnUpdateFavoriteError)
            return@launch
        }
        uiState.update {
            val bookInfo = it.bookInfo ?: return@launch
            it.copy(bookInfo = it.bookInfo.copy(isFavorite = !bookInfo.isFavorite))
        }
    }
}