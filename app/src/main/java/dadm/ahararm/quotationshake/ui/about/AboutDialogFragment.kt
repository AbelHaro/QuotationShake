package dadm.ahararm.quotationshake.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import dadm.ahararm.quotationshake.R
import dadm.ahararm.quotationshake.databinding.FragmentAboutBinding

class AppDialogFragment : DialogFragment(R.layout.fragment_about) {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}