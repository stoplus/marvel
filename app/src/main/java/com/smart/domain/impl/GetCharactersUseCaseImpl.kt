package com.smart.domain.impl

import com.smart.domain.api.GetCharactersUseCase
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.character.mapper.toDomain
import timber.log.Timber

class GetCharactersUseCaseImpl(
    private val repo: MarvelRepository,
) : GetCharactersUseCase() {

    override suspend fun execute(offset: Int): List<CharacterDomainModel> {
        return repo.getCharacters(offset).mapNotNull { item ->
            try {
                item.toDomain()
            } catch (e: IllegalArgumentException) {
                Timber.d(e)
                null
            }
        }
    }
}