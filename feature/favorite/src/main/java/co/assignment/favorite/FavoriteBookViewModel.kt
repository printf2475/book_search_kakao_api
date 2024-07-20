package co.assignment.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.assignment.domain.usecase.GetFavoriteBookListUseCase
import co.assignment.domain.usecase.UpdateFavoriteBookListUseCase
import co.assignment.favorite.mapper.range
import co.assignment.favorite.model.FavoriteAction
import co.assignment.favorite.model.FavoriteSideEffect
import co.assignment.favorite.model.FavoriteUiState
import co.assignment.favorite.model.PriceFilterType
import co.assignment.model.BookInfo
import co.assignment.model.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteBookViewModel @Inject constructor(
    private val getFavoriteBookListUseCase: GetFavoriteBookListUseCase,
    private val updateFavoriteBookListUseCase: UpdateFavoriteBookListUseCase
) : ViewModel() {

    companion object {
        private const val TAG: String = "FavoriteBookViewModel"
    }

    private val _sideEffect: Channel<FavoriteSideEffect> = Channel()
    val sideEffect: Flow<FavoriteSideEffect> = _sideEffect.receiveAsFlow()

    var uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState())
        private set

    init {
        onChangeSortType(SortType.Asc)
    }


    fun onAction(action: FavoriteAction) {
        Log.d(TAG, "action $action")
        when (action) {
            is FavoriteAction.OnClickFavorite -> onUpdateFavorite(action.bookInfo)
            is FavoriteAction.OnChangeSortType -> onChangeSortType(action.sortType)
            is FavoriteAction.OnChangeFilterPrice -> onChangeFilterPrice(action.priceFilterType)
            is FavoriteAction.OnChangeSortBottomSheetVisibility ->
                onChangeSortBottomSheetVisibility(action.isVisibility)

            is FavoriteAction.OnChangePriceFilterBottomSheetVisibility ->
                onChangePriceFilterBottomSheetVisibility(action.isVisibility)
        }
    }

    private fun onUpdateFavorite(bookInfo: BookInfo) = viewModelScope.launch {
        Log.d(TAG, "onUpdateFavorite $bookInfo")
        val isSucceed = updateFavoriteBookListUseCase(bookInfo)
        if (!isSucceed) {
            _sideEffect.send(FavoriteSideEffect.OnUpdateFavoriteError)
        }
    }

    private fun onChangeSortType(sortType: SortType) = viewModelScope.launch {
        uiState.update { it.copy(sortType = sortType) }
        val bookInfoListFlow = getFavoriteBookListUseCase(
            sortType = sortType, filterPriceRange =
            uiState.value.priceFilterType?.range
        )

        bookInfoListFlow
            .distinctUntilChanged()
            .collectLatest { bookInfoList ->
                uiState.update { it.copy(bookInfoList = bookInfoList) }
            }
    }

    private fun onChangeFilterPrice(priceFilterType: PriceFilterType?) = viewModelScope.launch {
        uiState.update {
            it.copy(priceFilterType = priceFilterType)
        }
        val bookInfoListFlow = getFavoriteBookListUseCase(
            sortType = uiState.value.sortType,
            filterPriceRange = priceFilterType?.range
        )
        bookInfoListFlow
            .distinctUntilChanged()
            .collectLatest { bookInfoList ->
                uiState.update { it.copy(bookInfoList = bookInfoList) }
            }
    }

    private fun onChangeSortBottomSheetVisibility(isVisible: Boolean) = viewModelScope.launch {
        uiState.update {
            it.copy(isVisibilitySortBottomSheetVisibility = isVisible)
        }
    }

    private fun onChangePriceFilterBottomSheetVisibility(isVisible: Boolean) =
        viewModelScope.launch {
            uiState.update { it.copy(isVisibilityPriceFilterBottomSheetVisibility = isVisible) }
        }
}