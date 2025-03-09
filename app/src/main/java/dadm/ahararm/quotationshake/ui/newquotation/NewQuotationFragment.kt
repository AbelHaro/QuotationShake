package dadm.ahararm.quotationshake.ui.newquotation

import android.os.Bundle
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

        setupMenuProvider()
        setupObservers()
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Configuración del menú
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.i_refresh -> {
                viewModel.getNewQuotation()
                true
            }

            else -> false
        }
    }

    // Configuración del MenuProvider
    private fun setupMenuProvider() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    // Configuración de observadores
    private fun setupObservers() {
        observeUserName()
        observeQuotation()
        observeLoadingState()
        observeErrors()
    }

    private fun observeUserName() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userName.collect { userName ->
                    val welcomeMessage = if (userName.isNotEmpty()) {
                        getString(R.string.welcome_message, userName)
                    } else {
                        getString(R.string.welcome_message, getString(R.string.anonymous))
                    }
                    binding.tvWelcomeMessage.text = welcomeMessage
                }
            }
        }
    }

    private fun observeQuotation() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.quotation.collect { quotation ->
                    binding.tvWelcomeMessage.isVisible = quotation == null
                    binding.tvQuote.text = quotation?.text ?: ""
                    binding.tvAuthor.text = quotation?.author.takeIf { it?.isNotEmpty() == true }
                        ?: getString(R.string.anonymous)
                    binding.tvAuthor.isVisible = quotation != null

                    binding.fabFavorite.isVisible =
                        quotation != null && !binding.tvWelcomeMessage.isVisible
                }
            }
        }
    }

    private fun observeLoadingState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    binding.srlMain.isRefreshing = isLoading
                }
            }
        }
    }

    private fun observeErrors() {
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
    }

    // Configuración de listeners
    private fun setupListeners() {
        binding.fabFavorite.setOnClickListener {
            viewModel.addFavourite()
        }

        binding.srlMain.setOnRefreshListener {
            viewModel.getNewQuotation()
        }
    }
}