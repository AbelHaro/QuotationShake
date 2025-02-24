package dadm.ahararm.quotationshake.ui.favourites

import androidx.lifecycle.ViewModel
import dadm.ahararm.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class FavouritesViewModel : ViewModel() {

    private val _favouriteQuotations = MutableStateFlow<List<Quotation>>(getFavoriteQuotations())
    val favouriteQuotations: StateFlow<List<Quotation>> = _favouriteQuotations

    private fun getFavoriteQuotations(): List<Quotation> {
        return List(20) {
            Quotation(
                id = it.toString(),
                text = "Cita de ejemplo #$it",
                author = if (Random.nextBoolean()) "Autor $it" else ""
            )
        }
    }
}
