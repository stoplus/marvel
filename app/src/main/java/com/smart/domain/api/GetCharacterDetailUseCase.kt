package com.smart.domain.api

import com.smart.domain.impl.model.characterDetails.CharacterDetails

abstract class GetCharacterDetailUseCase {
    abstract suspend fun execute(id: Int): CharacterDetails
}