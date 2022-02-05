package com.smart.domain.api

import com.smart.domain.impl.model.character.CharacterDomainModel

interface DataBaseRepository : MarvelRepository {
    fun saveCharacters(item: List<CharacterDomainModel>)
}
