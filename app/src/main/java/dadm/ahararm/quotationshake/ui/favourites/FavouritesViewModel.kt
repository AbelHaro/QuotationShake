package dadm.ahararm.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ahararm.quotationshake.data.favourites.FavouritesRepository
import dadm.ahararm.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    val favouriteQuotations: StateFlow<List<Quotation>> = favouritesRepository
        .getAllFavouritesQuotations()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val isDeleteAllMenuVisible: StateFlow<Boolean> = favouriteQuotations
        .map { it.isNotEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    fun deleteAllDialogFragment() {
        viewModelScope.launch {
            favouritesRepository.deleteAllFavourites()
        }
    }

    fun deleteQuotationAtPosition(position: Int) {
        viewModelScope.launch {
            val currentList = favouriteQuotations.value
            if (position in currentList.indices) {
                favouritesRepository.removeQuotationFromFavourites(currentList[position])
            }
        }
    }
}