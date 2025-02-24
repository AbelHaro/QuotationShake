package dadm.ahararm.quotationshake.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.ahararm.quotationshake.databinding.QuotationItemBinding
import dadm.ahararm.quotationshake.domain.model.Quotation

class QuotationListAdapter :
    ListAdapter<Quotation, QuotationListAdapter.ViewHolder>(QuotationDiff) {

    object QuotationDiff : DiffUtil.ItemCallback<Quotation>() {
        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: QuotationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quotation: Quotation) {
            binding.tvQuotation.text = quotation.text
            binding.tvAuthor.text = quotation.author.ifEmpty {
                binding.root.context.getString(dadm.ahararm.quotationshake.R.string.anonymous)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            QuotationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
