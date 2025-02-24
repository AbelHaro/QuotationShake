package dadm.ahararm.quotationshake.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ahararm.quotationshake.R
import dadm.ahararm.quotationshake.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        val adapter = QuotationListAdapter()
        binding.rvFavourites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavourites.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favouriteQuotations.collectLatest { list ->
                adapter.submitList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
