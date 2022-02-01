package com.smart.presentation.impl.charactersScreen

import androidx.lifecycle.viewModelScope
import com.smart.base.SingleLiveEvent
import com.smart.domain.api.GetCharactersUseCase
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel
import com.smart.presentation.api.CharacterViewModel
import com.smart.presentation.api.Router
import com.smart.presentation.impl.charactersScreen.model.mapper.toPresent
import kotlinx.coroutines.launch
import timber.log.Timber

class CharacterViewModelImpl(
    private val router: Router,
    private val charactersUseCase: GetCharactersUseCase,
) : CharacterViewModel() {

    private var presentList = mutableListOf<CharacterPresentModel>()

    override val listCharacters = SingleLiveEvent<List<CharacterPresentModel>>()
    override val showBottomLoader = SingleLiveEvent<Boolean>()

    override fun openAdditional(characterId: Int) {
        router.navigate(CharactersFragmentDirections.actionCharactersToAdditional(characterId))
    }

    override fun getCharacters(offset: Int) {
        viewModelScope.launch {
            if (presentList.isNotEmpty()) {
                showBottomLoader.postValue(true)
            }
            val list = charactersUseCase.execute(offset)
            val newPresentList: List<CharacterPresentModel> = list.map {
                it.toPresent {

                }
            }
            presentList.addAll(newPresentList)
            listCharacters.postValue(presentList)
            Timber.d("sdsds, $list")
        }
    }

    override fun loadMore() {
        getCharacters(presentList.size)
    }
}