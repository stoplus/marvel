package com.smart.domain.impl

import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.presentation.impl.charactersScreen.model.mapper.toPresent
import com.smart.domain.api.GetCharactersUseCase
import com.smart.domain.api.MarvelRepository
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel

class GetCharactersUseCaseImpl(
    private val repo: MarvelRepository,
) : GetCharactersUseCase() {

    override suspend fun execute(offset: Int): List<ResultsItem> {
        return repo.getCharacters(offset)
    }
}