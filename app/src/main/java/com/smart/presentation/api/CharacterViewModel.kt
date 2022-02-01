package com.smart.presentation.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel

abstract class CharacterViewModel : ViewModel() {

    abstract val listCharacters: LiveData<List<CharacterPresentModel>>
    abstract val showBottomLoader: LiveData<Boolean>

    abstract fun openAdditional(characterId: Int)
    abstract fun getCharacters(offset: Int)
    abstract fun loadMore()

}