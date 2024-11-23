package com.mshetty.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mshetty.demoapp.databinding.LayoutItemCountryBinding
import com.mshetty.demoapp.model.CountryResponseItem

class CountryAdapter(private val onclickListner: OnClickListner) :
    ListAdapter<CountryResponseItem, CountryAdapter.CountryViewHolder>(CountryDiffCallback()),
    Filterable {

    private var originalList: List<CountryResponseItem?> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = LayoutItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding, parent.context, onclickListner)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    private val searchFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = if (constraint.isNullOrEmpty()) {
                originalList
            } else {
                originalList.filter { it?.name?.common?.contains(constraint, ignoreCase = true) == true }
            }
            return FilterResults().apply { values = filteredList }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            submitList(results.values as? List<CountryResponseItem>)
        }
    }

    fun setData(list: List<CountryResponseItem?>) {
        originalList = list
        submitList(list)
    }

    override fun getFilter(): Filter = searchFilter

    class CountryViewHolder(
        private val binding: LayoutItemCountryBinding,
        private val context: Context,
        val onclickListner: OnClickListner
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(item: CountryResponseItem) {
            binding.tvName.text = item.name?.common
            binding.tvName.tag = item.flags?.png
            binding.tvName.setOnClickListener {
                onclickListner.countryClicked(item)
            }
        }
    }

    class CountryDiffCallback : DiffUtil.ItemCallback<CountryResponseItem>() {
        override fun areItemsTheSame(oldItem: CountryResponseItem, newItem: CountryResponseItem): Boolean {
            return oldItem.name?.common == newItem.name?.common // Use a unique identifier
        }

        override fun areContentsTheSame(oldItem: CountryResponseItem, newItem: CountryResponseItem): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnClickListner {
    fun countryClicked(item: CountryResponseItem)
}