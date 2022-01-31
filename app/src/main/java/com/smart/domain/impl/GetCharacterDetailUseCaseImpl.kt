package com.smart.domain.impl

import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.api.GetCharacterDetailUseCase
import com.smart.domain.api.MarvelRepository

class GetCharacterDetailUseCaseImpl(
    private val repo: MarvelRepository,
) : GetCharacterDetailUseCase() {

    override suspend fun execute(id: Int): ResultsItem {
        return repo.getCharacter(id)
    }
}