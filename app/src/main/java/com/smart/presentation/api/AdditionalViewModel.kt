package com.smart.presentation.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smart.data.impl.models.response.characters.ResultsItem

abstract class AdditionalViewModel: ViewModel() {

    abstract val details: LiveData<ResultsItem>

    abstract fun getCharacterDetails()
    abstract fun back()
}