package com.smart.presentation.impl.additionalInfoScreen

import android.os.Bundle
import android.view.View

import com.smart.base.BaseFragment
import com.smart.databinding.DetailsFragmentBinding
import com.smart.presentation.api.AdditionalViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class AdditionalFragment: BaseFragment<AdditionalViewModel, DetailsFragmentBinding>() {

    override fun getParameters(): ParametersDefinition = {
        parametersOf(AdditionalFragmentArgs.fromBundle(requireArguments()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.details.observe {
            Timber.d("sdsds, $it")
        }
    }
}