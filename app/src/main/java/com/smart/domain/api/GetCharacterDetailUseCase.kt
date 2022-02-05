package com.smart.domain.api

import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.characterDetails.CharacterDetails

interface GetCharacterDetailUseCase {
    suspend fun execute(model: CharacterDomainModel): CharacterDetails
}
