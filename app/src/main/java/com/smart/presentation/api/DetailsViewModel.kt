package com.smart.presentation.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.impl.model.characterDetails.CharacterDetails

abstract class DetailsViewModel: ViewModel() {

    abstract val details: LiveData<CharacterDetails>

    abstract fun getCharacterDetails()
    abstract fun back()
}