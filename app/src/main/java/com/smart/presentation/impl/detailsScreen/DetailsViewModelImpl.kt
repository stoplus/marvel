package com.smart.presentation.impl.detailsScreen

import androidx.lifecycle.viewModelScope
import com.smart.base.SingleLiveEvent
import com.smart.domain.api.GetCharacterDetailUseCase
import com.smart.presentation.api.DetailsViewModel
import com.smart.presentation.api.Router
import com.smart.presentation.impl.detailsScreen.model.DetailsPresentModel
import com.smart.presentation.impl.detailsScreen.model.mapper.toPresent
import kotlinx.coroutines.launch

class DetailsViewModelImpl(
    private val router: Router,
    private val characterDetailsUseCase: GetCharacterDetailUseCase,
    private val characterId: Int,
) : DetailsViewModel() {

    override val details = SingleLiveEvent<DetailsPresentModel>()
    override val isProgress = SingleLiveEvent<Boolean>()

    override fun getCharacterDetails() {
        viewModelScope.launch {
            val characterDetails = characterDetailsUseCase.execute(characterId)
            val present = characterDetails.toPresent()
            isProgress.postValue(false)
            details.postValue(present)
        }
    }

    override fun back() {
        router.navigateUp()
    }
}