package com.smart.domain.api

import com.smart.data.impl.models.response.characters.ResultsItem

abstract class GetCharactersUseCase {
    abstract suspend fun execute(offset: Int): List<ResultsItem>
    abstract suspend fun getCharacter(id: Int): ResultsItem
}