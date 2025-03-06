package dadm.ahararm.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ahararm.quotationshake.data.newquotation.NewQuotationRepository
import dadm.ahararm.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val newQuotationRepository: NewQuotationRepository
) : ViewModel() {


    // Estado interno mutable
    private val _quotation: MutableStateFlow<Quotation?> = MutableStateFlow(null)
    val quotation: StateFlow<Quotation?> = _quotation.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _addFavouriteVisible = MutableStateFlow(false)
    val addFavouriteVisible: StateFlow<Boolean> = _addFavouriteVisible.asStateFlow()

    private val _errorToDisplay = MutableStateFlow<Throwable?>(null)
    val errorToDisplay: StateFlow<Throwable?> = _errorToDisplay.asStateFlow()

    fun resetError() {
        _errorToDisplay.value = null
    }

    fun getNewQuotation() {
        _isLoading.value = true
        viewModelScope.launch {
            newQuotationRepository.getNewQuotation().fold(
                onSuccess = { quotation ->
                    _quotation.value = quotation
                },
                onFailure = { error ->
                    _errorToDisplay.value = error
                }
            )
        }
        _isLoading.value = false
    }


    fun addFavourite() {
        _addFavouriteVisible.value = false
    }
}
