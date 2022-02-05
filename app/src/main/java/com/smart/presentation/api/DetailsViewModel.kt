package com.smart.presentation.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smart.presentation.impl.detailsScreen.model.DetailsPresentModel

abstract class DetailsViewModel : ViewModel() {

    abstract val details: LiveData<DetailsPresentModel>
    abstract val isProgress: LiveData<Boolean>

    abstract fun getCharacterDetails()
    abstract fun back()
}
