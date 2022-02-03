package com.smart.presentation.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel

abstract class CharacterViewModel : ViewModel() {

    abstract val listCharacters: LiveData<List<CharacterPresentModel>>
    abstract val showError: LiveData<Boolean>
    abstract val showBottomLoader: LiveData<Boolean>
    abstract val isRefreshing: LiveData<Boolean>
    abstract val isProgress: LiveData<Boolean>

    abstract fun getCharacters(offset: Int, resetList: Boolean)
    abstract fun loadMore()
}