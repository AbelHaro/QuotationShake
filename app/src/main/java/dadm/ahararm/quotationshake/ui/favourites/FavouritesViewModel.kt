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
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    val favouriteQuotations: StateFlow<List<Quotation>> = favouritesRepository
        .getAllFavouritesQuotations()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
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
        // TODO: Implementar la lógica para borrar todas las citas
        // favouritesRepository.deleteAllFavourites()
    }

    // Función para borrar una cita en una posición específica (comentada hasta su corrección)
    fun deleteQuotationAtPosition(position: Int) {
        // TODO: Implementar la lógica para borrar una cita en la posición dada
        // val currentList = favouriteQuotations.value
        // if (position in currentList.indices) {
        //     favouritesRepository.removeQuotationFromFavourites(currentList[position])
        // }
    }
}