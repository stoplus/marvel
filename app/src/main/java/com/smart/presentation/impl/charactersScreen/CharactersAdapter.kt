package com.smart.presentation.impl.charactersScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smart.R
import com.smart.databinding.CharacterItemBinding
import com.smart.databinding.LoaderItemBinding
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel

class CharactersAdapter(private val loadNextPage: () -> Unit) :
    ListAdapter<CharacterPresentModel, RecyclerView.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<CharacterPresentModel>() {
        override fun areItemsTheSame(
            oldItem: CharacterPresentModel,
            newItem: CharacterPresentModel,
        ) = oldItem.idCharacter == newItem.idCharacter

        override fun areContentsTheSame(
            oldItem: CharacterPresentModel,
            newItem: CharacterPresentModel,
        ) = oldItem.name == newItem.name &&
            oldItem.description == newItem.description &&
            oldItem.link == newItem.link
    }

    var isLoaderVisible = false
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CHARACTER -> CharacterHolder(
                binding = CharacterItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_LOADING -> ProgressHolder(
                binding = LoaderItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid View type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position in currentList.indices) {
            val item = currentList[position]
            if (holder is CharacterHolder) holder.bind(item)
        }
        if (position == currentList.size - ITEM_PREFETCH_COUNT) {
            loadNextPage()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= currentList.size) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_CHARACTER
        }
    }

    override fun getItemCount(): Int {
        return if (isLoaderVisible) {
            currentList.size + 1
        } else {
            currentList.size
        }
    }

    fun loading(showLoader: Boolean) {
        isLoaderVisible = showLoader
        if (showLoader) {
            notifyItemInserted(currentList.size)
        } else {
            notifyItemRemoved(currentList.size)
        }
    }

    override fun submitList(list: List<CharacterPresentModel>?) {
        super.submitList(list) {
            loading(false)
        }
    }

    class ProgressHolder(binding: LoaderItemBinding) : RecyclerView.ViewHolder(binding.root)

    class CharacterHolder(
        private val binding: CharacterItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterPresentModel) {
            with(binding) {
                Glide.with(root.context)
                    .load(item.link)
                    .placeholder(R.drawable.marvel)
                    .into(imageCharacter)

                description.text = item.description
                name.text = item.name
                root.setOnClickListener { item.onClick.invoke() }
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_CHARACTER = 1
        private const val ITEM_PREFETCH_COUNT = 8
    }
}