package com.smart.domain.api

import com.smart.data.impl.models.response.characters.ResultsItem

abstract class GetCharacterDetailUseCase {
    abstract suspend fun execute(id: Int): ResultsItem
}