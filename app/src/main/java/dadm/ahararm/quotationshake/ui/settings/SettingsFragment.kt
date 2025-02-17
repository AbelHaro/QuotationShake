package dadm.ahararm.quotationshake.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.ahararm.quotationshake.R
import dadm.ahararm.quotationshake.databinding.FragmentNewQuotationBinding

    class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var _binding : FragmentNewQuotationBinding? = null
    private val binding = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewQuotationBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}