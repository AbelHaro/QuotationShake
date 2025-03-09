package dadm.ahararm.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.ahararm.quotationshake.data.favourites.FavouritesRepository
import dadm.ahararm.quotationshake.data.newquotation.NewQuotationRepository
import dadm.ahararm.quotationshake.data.settings.SettingsRepository
import dadm.ahararm.quotationshake.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val newQuotationRepository: NewQuotationRepository,
    private val settingsRepository: SettingsRepository,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    // Flujos de estado internos
    private val _quotation = MutableStateFlow<Quotation?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _addFavouriteVisible = MutableStateFlow(true)
    private val _errorToDisplay = MutableStateFlow<Throwable?>(null)

    // Flujos de estado expuestos
    val quotation: StateFlow<Quotation?> = _quotation.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    val addFavouriteVisible: StateFlow<Boolean> = _addFavouriteVisible.asStateFlow()
    val errorToDisplay: StateFlow<Throwable?> = _errorToDisplay.asStateFlow()

    // Flujo del nombre del usuario
    val userName: StateFlow<String> = settingsRepository.getUserName().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )

    // Reinicia el error a mostrar
    fun resetError() {
        _errorToDisplay.value = null
    }

    // Obtiene una nueva cotización
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
            _isLoading.value = false
        }
    }

    // Añade la cotización actual a favoritos
    fun addFavourite() {
        viewModelScope.launch {
            _quotation.value?.let { quotation ->
                favouritesRepository.addQuotationToFavourites(quotation)
                _addFavouriteVisible.value = false
            }
        }
    }
}