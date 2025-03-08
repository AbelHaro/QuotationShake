package dadm.ahararm.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    // Estado interno mutable para la cotización
    private val _quotation: MutableStateFlow<Quotation?> = MutableStateFlow(null)
    val quotation: StateFlow<Quotation?> = _quotation.asStateFlow()

    // Estado interno mutable para el indicador de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Estado interno mutable para la visibilidad del botón de favoritos
    private val _addFavouriteVisible = MutableStateFlow(false)
    val addFavouriteVisible: StateFlow<Boolean> = _addFavouriteVisible.asStateFlow()

    // Estado interno mutable para el error a mostrar
    private val _errorToDisplay = MutableStateFlow<Throwable?>(null)
    val errorToDisplay: StateFlow<Throwable?> = _errorToDisplay.asStateFlow()

    // Estado del nombre del usuario, convertido directamente a StateFlow desde el repositorio
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
        _addFavouriteVisible.value = false
    }
}