package dadm.ahararm.quotationshake.ui.favourites

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dadm.ahararm.quotationshake.R

class DeleteAllDialogFragment : DialogFragment() {

    private val viewModel: FavouritesViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.confirm_delete_all_quotes_title))
            .setMessage(R.string.confirm_delete_all_quotes_text)
            .setPositiveButton(getString(R.string.confirm_delete_all_quotes_positive_button)) { _, _ ->
                viewModel.deleteAllDialogFragment()
            }
            .setNegativeButton(R.string.confirm_delete_all_quotes_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}
