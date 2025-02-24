package dadm.ahararm.quotationshake.ui.favourites

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dadm.ahararm.quotationshake.R
import dadm.ahararm.quotationshake.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment(R.layout.fragment_favourites), MenuProvider {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouritesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val adapter = QuotationListAdapter()
        binding.rvFavourites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavourites.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favouriteQuotations.collectLatest { list ->
                adapter.submitList(list)
            }
        }

        // Observa la propiedad y actualiza el menú cuando cambie
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isDeleteAllMenuVisible.collectLatest {
                requireActivity().invalidateMenu()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_favourites, menu)
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)
        val deleteAllItem = menu.findItem(R.id.iDeleteAll)
        deleteAllItem?.isVisible = viewModel.isDeleteAllMenuVisible.value
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.iDeleteAll) {
            findNavController().navigate(R.id.deleteAllDialogFragment)
            return true
        }
        return false
    }
}
