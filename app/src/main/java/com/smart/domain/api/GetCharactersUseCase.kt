package com.smart.domain.api

import com.smart.domain.impl.model.character.CharacterDomainModel

abstract class GetCharactersUseCase {
    abstract suspend fun execute(offset: Int): List<CharacterDomainModel>
}