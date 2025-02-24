package dadm.ahararm.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import dadm.ahararm.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewQuotationViewModel : ViewModel() {

    // Propiedad privada y de solo lectura de MutableStateFlow<String>
    private val _userName: MutableStateFlow<String> = MutableStateFlow(getUserName())

    // Propiedad pública de solo lectura como StateFlow
    val userName: StateFlow<String> = _userName.asStateFlow()

    // Estado interno mutable
    private val _quotation: MutableStateFlow<Quotation?> = MutableStateFlow(null)
    val quotation: StateFlow<Quotation?> = _quotation.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _add_favourite_visible = MutableStateFlow(false)
    val add_favourite_visible: StateFlow<Boolean> = _add_favourite_visible.asStateFlow()

    private fun getUserName(): String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma", "").random()
    }

    fun getNewQuotation() {
        _isLoading.value = true
        val num = (0..99).random()
        _quotation.update {
            Quotation(
                id = "$num",
                text = "Quotation text #$num",
                author = "Author #$num"
            )
        }
        _isLoading.value = false
        _add_favourite_visible.value = true
    }

    fun addFavourite() {
        _add_favourite_visible.value = false
    }
}
