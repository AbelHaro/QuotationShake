package dadm.ahararm.quotationshake.ui.newquotation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dadm.ahararm.quotationshake.R
import dadm.ahararm.quotationshake.databinding.FragmentNewQuotationBinding
import dadm.ahararm.quotationshake.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation), MenuProvider {

    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewQuotationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewQuotationBinding.bind(view)

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                binding.tvAuthor.isVisible = false

                viewModel.quotation.collect { quotation ->
                    binding.tvWelcomeMessage.isVisible = quotation == null
                    binding.tvQuote.text = quotation?.text ?: ""
                    binding.tvAuthor.text = quotation?.author.takeIf { it?.isNotEmpty() == true }
                        ?: getString(R.string.anonymous)
                    binding.tvAuthor.isVisible = quotation != null
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    Log.d(
                        "NewQuotationFragment.onViewCreated",
                        "El valor de isLoading es: $isLoading"
                    )
                    binding.srlMain.isRefreshing = isLoading
                    Log.d(
                        "NewQuotationFragment.onViewCreated",
                        "El valor de isLoading es: $isLoading"
                    )
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.addFavouriteVisible.collect { visible ->
                        binding.fabFavorite.isVisible = visible
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorToDisplay.collect { error ->
                    error?.let {
                        val messageResId = when (it) {
                            is java.net.HttpRetryException -> R.string.error_server
                            is NoInternetException -> R.string.error_network
                            else -> R.string.error_generic
                        }

                        Snackbar.make(binding.root, getString(messageResId), Snackbar.LENGTH_LONG)
                            .show()
                        viewModel.resetError()
                    }
                }
            }
        }



        binding.fabFavorite.setOnClickListener {
            viewModel.addFavourite()
        }

        binding.srlMain.setOnRefreshListener {
            viewModel.getNewQuotation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.i_refresh) {
            viewModel.getNewQuotation()
            return true
        }
        return false
    }

}
