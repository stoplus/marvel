package com.smart.domain.impl

import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.api.GetCharactersUseCase
import com.smart.domain.api.MarvelRepository

class GetCharactersUseCaseImpl(
    private val repo: MarvelRepository,
) : GetCharactersUseCase() {

    override suspend fun getCharacters(offset: Int): List<ResultsItem> {
        return repo.getCharacters(offset)
    }

    override suspend fun getCharacter(id: Int): ResultsItem {
        return repo.getCharacter(id)
    }
}