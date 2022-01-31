package com.smart.presentation.impl.additionalInfoScreen

import androidx.lifecycle.viewModelScope
import com.smart.base.SingleLiveEvent
import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.api.GetCharacterDetailUseCase
import com.smart.presentation.api.AdditionalViewModel
import com.smart.presentation.api.Router
import kotlinx.coroutines.launch

class AdditionalViewModelImpl(
    private val router: Router,
    private val characterDetailsUseCase: GetCharacterDetailUseCase,
    private val characterId: Int,
) : AdditionalViewModel() {

    override val details = SingleLiveEvent<ResultsItem>()

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