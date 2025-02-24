package dadm.ahararm.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ahararm.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

class FavouritesViewModel : ViewModel() {

    private val _favouriteQuotations = MutableStateFlow(getFavoriteQuotations())
    val favouriteQuotations: StateFlow<List<Quotation>> = _favouriteQuotations.asStateFlow()

    val isDeleteAllMenuVisible: StateFlow<Boolean> = favouriteQuotations
        .map { it.isNotEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )

    private fun getFavoriteQuotations(): List<Quotation> {
        return List(20) {
            Quotation(
                id = it.toString(),
                text = "Cita de ejemplo #$it",
                author = if (Random.nextBoolean()) "Autor $it" else "Desconocido"
            )
        }
    }

    fun deleteAllDialogFragment() {
        _favouriteQuotations.value = emptyList()
    }
}
