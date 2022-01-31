package com.smart.presentation.impl.charactersScreen

import androidx.lifecycle.viewModelScope
import com.smart.base.SingleLiveEvent
import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.api.GetCharactersUseCase
import com.smart.presentation.api.CharacterViewModel
import com.smart.presentation.api.Router
import kotlinx.coroutines.launch

class CharacterViewModelImpl(
    private val router: Router,
    private val charactersUseCase: GetCharactersUseCase,
) : CharacterViewModel() {

    override val listCharacters= SingleLiveEvent<List<ResultsItem>>()

    override fun openAdditional() {
        router.navigate(CharactersFragmentDirections.actionCharactersToAdditional())
    }

    override fun getCharacters(offset: Int) {
        viewModelScope.launch {
            val list = charactersUseCase.getCharacters(offset)
        }
    }
}