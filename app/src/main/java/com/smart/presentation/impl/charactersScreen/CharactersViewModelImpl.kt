package com.smart.presentation.impl.charactersScreen

import androidx.lifecycle.viewModelScope
import com.smart.base.SingleLiveEvent
import com.smart.domain.api.GetCharactersUseCase
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.presentation.api.CharacterViewModel
import com.smart.presentation.api.Router
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel
import com.smart.presentation.impl.charactersScreen.model.mapper.toPresent
import com.smart.utils.wrap
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class CharactersViewModelImpl(
    private val router: Router,
    private val charactersUseCase: GetCharactersUseCase,
) : CharacterViewModel() {

    private var resultsItemList: List<CharacterDomainModel> = listOf()
    override val listCharacters = SingleLiveEvent<List<CharacterPresentModel>>()
    override val showError = SingleLiveEvent<Unit>()
    override val showBottomLoader = SingleLiveEvent<Boolean>()
    override val isRefreshing = SingleLiveEvent<Boolean>()
    override val isProgress = SingleLiveEvent<Boolean>()
    private var getNewOperationJob: Job? = null

    override fun getCharacters(offset: Int, resetList: Boolean) {
        if (getNewOperationJob == null || getNewOperationJob?.isCompleted == true) {
            getNewOperationJob = viewModelScope.launch {
                if (resultsItemList.isNotEmpty()) {
                    showBottomLoader.postValue(true)
                }
                charactersUseCase.execute(offset).wrap(
                    onSuccess = { doSuccess(it, resetList) },
                    onError = { doError(it) }
                )
            }
        }
    }

    private fun doError(throwable: Throwable) {
        isProgress.postValue(false)
        showBottomLoader.value = false
        isRefreshing.postValue(false)
        showError.postValue(Unit)
        Timber.e(throwable)
    }

    private fun doSuccess(
        it: List<CharacterDomainModel>,
        resetList: Boolean,
    ) {
        if (resetList) {
            resultsItemList = listOf()
        }
        val newList = (resultsItemList + it).distinct()
        if (newList.size == resultsItemList.size) {
            showError.postValue(Unit)
        }
        resultsItemList = newList
        isProgress.postValue(false)
        isRefreshing.postValue(false)
        listCharacters.postValue(createPresentationList(resultsItemList))
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