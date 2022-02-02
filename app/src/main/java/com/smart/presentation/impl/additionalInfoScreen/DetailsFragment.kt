package com.smart.presentation.impl.additionalInfoScreen

import android.os.Bundle
import android.view.View

import com.smart.base.BaseFragment
import com.smart.databinding.DetailsFragmentBinding
import com.smart.presentation.api.DetailsViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class DetailsFragment : BaseFragment<DetailsViewModel, DetailsFragmentBinding>() {

    override fun getParameters(): ParametersDefinition = {
        parametersOf(DetailsFragmentArgs.fromBundle(requireArguments()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.getCharacterDetails()
    }

    private fun observeViewModel() {
        viewModel.details.observe {
            Timber.d("sdsds, $it")
        }
    }
}