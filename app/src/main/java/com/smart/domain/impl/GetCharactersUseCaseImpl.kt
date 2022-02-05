package com.smart.domain.impl

import com.smart.domain.api.GetCharactersUseCase
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.utils.Result

class GetCharactersUseCaseImpl(
    private val repo: MarvelRepository,
) : GetCharactersUseCase {

    override suspend fun execute(offset: Int): Result<List<CharacterDomainModel>> {
        return repo.getCharacters(offset)
    }
}
