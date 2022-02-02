package com.smart.presentation.impl.additionalInfoScreen

import androidx.lifecycle.viewModelScope
import com.smart.base.SingleLiveEvent
import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.api.GetCharacterDetailUseCase
import com.smart.domain.impl.model.characterDetails.CharacterDetails
import com.smart.presentation.api.DetailsViewModel
import com.smart.presentation.api.Router
import kotlinx.coroutines.launch

class DetailsViewModelImpl(
    private val router: Router,
    private val characterDetailsUseCase: GetCharacterDetailUseCase,
    private val characterId: Int,
) : DetailsViewModel() {

    override val details = SingleLiveEvent<CharacterDetails>()

    override fun getCharacterDetails() {
        viewModelScope.launch {
            val characterDetails = characterDetailsUseCase.execute(characterId)
            details.postValue(characterDetails)
        }
    }

    override fun back() {
        router.navigateUp()
    }
}