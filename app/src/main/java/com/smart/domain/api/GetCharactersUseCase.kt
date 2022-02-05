package com.smart.domain.api

import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.utils.Result

abstract class GetCharactersUseCase {
    abstract suspend fun execute(offset: Int): Result<List<CharacterDomainModel>>
}