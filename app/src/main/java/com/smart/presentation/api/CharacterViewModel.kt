package com.smart.presentation.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smart.data.impl.models.response.characters.ResultsItem

abstract class CharacterViewModel : ViewModel() {

    abstract val listCharacters: LiveData<List<ResultsItem>>

    abstract fun openAdditional()
    abstract fun getCharacters(offset: Int)
}