package com.smart.presentation.impl.charactersScreen

import androidx.lifecycle.viewModelScope
import com.smart.base.SingleLiveEvent
import com.smart.domain.api.GetCharactersUseCase
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.presentation.api.CharacterViewModel
import com.smart.presentation.api.Router
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel
import com.smart.presentation.impl.charactersScreen.model.mapper.toPresent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class CharactersViewModelImpl(
    private val router: Router,
    private val charactersUseCase: GetCharactersUseCase,
) : CharacterViewModel() {

    private var resultsItemList: List<CharacterDomainModel> = listOf()
    override val listCharacters = SingleLiveEvent<List<CharacterPresentModel>>()
    override val showError = SingleLiveEvent<Boolean>()
    override val showBottomLoader = SingleLiveEvent<Boolean>()
    override val isRefreshing = SingleLiveEvent<Boolean>()
    private var getNewOperationJob: Job? = null

    override fun getCharacters(offset: Int, resetList: Boolean) {
        if (getNewOperationJob == null || getNewOperationJob?.isCompleted == true) {
            getNewOperationJob = viewModelScope.launch {
                if (resultsItemList.isNotEmpty()) {
                    showBottomLoader.postValue(true)
                }

                runCatching {
                    charactersUseCase.execute(offset).let {
                        if (resetList) {
                            resultsItemList = listOf()
                        }
                        resultsItemList = resultsItemList + it
                    }
                }.onSuccess {
                    isRefreshing.postValue(false)
                    showError.postValue(false)
                    listCharacters.postValue(createPresentationList(resultsItemList))
                }.onFailure {
                    showBottomLoader.value = false
                    isRefreshing.postValue(false)
                    showError.postValue(true)
                    Timber.w(it)
                }
            }
        }
    }

    private fun createPresentationList(
        itemList: List<CharacterDomainModel>,
    ): MutableList<CharacterPresentModel> {
        val resultList = mutableListOf<CharacterPresentModel>()
        itemList.forEach { item ->
            resultList.add(
                item.toPresent {
                    router.navigate(
                        CharactersFragmentDirections.actionCharactersToAdditional(
                            item.idCharacter,
                            item.name
                        )
                    )
                }
            )
        }
        return resultList
    }

    override fun loadMore() {
        getCharacters(resultsItemList.size, false)
    }
}