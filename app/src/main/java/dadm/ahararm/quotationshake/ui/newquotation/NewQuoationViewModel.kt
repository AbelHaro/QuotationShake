package dadm.ahararm.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewQuotationViewModel : ViewModel() {

    // Propiedad privada y de solo lectura de MutableStateFlow<String>
    private val _userName: MutableStateFlow<String> = MutableStateFlow(getUserName())

    // Propiedad pública de solo lectura como StateFlow
    val userName: StateFlow<String> = _userName.asStateFlow()

    private fun getUserName(): String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma", "").random()
    }
}
