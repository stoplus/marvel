package com.smart.presentation.impl.detailsScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smart.R
import com.smart.databinding.DetailsItemBinding
import com.smart.presentation.impl.detailsScreen.model.DetailsListModel

class DetailsAdapter : ListAdapter<DetailsListModel, RecyclerView.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<DetailsListModel>() {
        override fun areItemsTheSame(
            oldItem: DetailsListModel,
            newItem: DetailsListModel,
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: DetailsListModel,
            newItem: DetailsListModel,
        ) = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsHolder {
        return DetailsHolder(
            binding = DetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        if (holder is DetailsHolder) holder.bind(item)
    }

    class DetailsHolder(
        private val binding: DetailsItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailsListModel) {
            with(binding) {
                Glide.with(root.context)
                    .load(item.link)
                    .fitCenter()
                    .placeholder(R.drawable.marvel)
                    .into(image)
                if (item.name.isNotEmpty()) {
                    name.text = item.name
                }
            }
        }
    }
}
