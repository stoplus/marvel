package com.smart.presentation.impl.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.smart.R
import com.smart.base.BaseFragment
import com.smart.databinding.DetailsFragmentBinding
import com.smart.presentation.api.DetailsViewModel
import com.smart.presentation.impl.detailsScreen.model.DetailsListModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

class DetailsFragment : BaseFragment<DetailsViewModel, DetailsFragmentBinding>() {

    private val adapterComics by lazy(LazyThreadSafetyMode.NONE) { DetailsAdapter() }
    private val adapterEvents by lazy(LazyThreadSafetyMode.NONE) { DetailsAdapter() }
    private val adapterSeries by lazy(LazyThreadSafetyMode.NONE) { DetailsAdapter() }

    override fun getParameters(): ParametersDefinition = {
        parametersOf(DetailsFragmentArgs.fromBundle(requireArguments()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = arguments?.getString("characterName") ?: getString(R.string.title_details)
        }
        binding.recyclerComics.adapter = adapterComics
        binding.recyclerEvents.adapter = adapterEvents
        binding.recyclerSeries.adapter = adapterSeries
        observeViewModel()
        viewModel.getCharacterDetails()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeViewModel() {
        viewModel.details.observe {
            if (it.description.isNotEmpty()) binding.description.text = it.description
            Glide.with(binding.root.context)
                .load(it.link)
                .placeholder(R.drawable.marvel)
                .fitCenter()
                .into(binding.imageCharacter)
            if (it.comics.isNotEmpty()) {
                adapterComics.submitList(it.comics)
                binding.titleComics.isVisible = true
            }
            if (it.events.isNotEmpty()) {
                adapterEvents.submitList(it.events)
                binding.titleEvents.isVisible = true
            }
            if (it.series.isNotEmpty()) {
                adapterSeries.submitList(it.series)
                binding.titleSeries.isVisible = true
            }
            if (it.stories.isNotEmpty()) {
                binding.titleStories.isVisible = true
                var count = 0
                it.stories.forEach { model ->
                    binding.listStories.addView(getStoriesView(model, ++count))
                }
            }
        }
        viewModel.isProgress.observe { binding.progressBar.isVisible = it }
    }

    private fun getStoriesView(model: DetailsListModel, count: Int): TextView {
        return (LayoutInflater.from(binding.root.context)
            .inflate(R.layout.stories_item, null) as TextView)
            .apply {
                val name = "$count" + ". " + model.name
                text = name
            }
    }
}