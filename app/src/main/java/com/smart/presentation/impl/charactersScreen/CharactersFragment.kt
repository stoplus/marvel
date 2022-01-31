package com.smart.presentation.impl.charactersScreen

import android.os.Bundle
import android.view.View

import com.smart.base.BaseFragment
import com.smart.databinding.CharactersFragmentBinding
import com.smart.presentation.api.CharacterViewModel
import timber.log.Timber

class CharactersFragment : BaseFragment<CharacterViewModel, CharactersFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.main.setOnClickListener {
            viewModel.openAdditional()
        }

        observeViewModel()
        viewModel.getCharacters(0)
    }

    private fun observeViewModel() {
        viewModel.listCharacters.observe {
            Timber.d("sdsds, $it")
        }
    }
}