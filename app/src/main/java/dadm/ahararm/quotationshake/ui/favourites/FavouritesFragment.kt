package dadm.ahararm.quotationshake.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.ahararm.quotationshake.R
import dadm.ahararm.quotationshake.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment(R.layout.fragment_favourites), MenuProvider {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouritesViewModel by activityViewModels()

    private val itemTouchHelper =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean = false
            override fun isItemViewSwipeEnabled(): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteQuotationAtPosition(viewHolder.adapterPosition)
            }
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Define la función onItemClick
        val onItemClick: (String) -> Unit = { authorName ->
            if (authorName == "Anonymous") {
                Snackbar.make(
                    binding.root,
                    "No se puede mostrar información para autor anónimo",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                try {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://en.wikipedia.org/wiki/Special:Search?search=$authorName".toUri()
                    )
                    startActivity(intent)
                } catch (e: Exception) {
                    Snackbar.make(
                        binding.root,
                        "No es posible gestionar la acción solicitada",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val adapter = QuotationListAdapter(onItemClick)
        binding.rvFavourites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavourites.adapter = adapter

        itemTouchHelper.attachToRecyclerView(binding.rvFavourites)

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
