package com.smart.domain.impl.model.character.mapper

import com.smart.data.impl.dataBaseRepository.model.CharacterDb
import com.smart.data.impl.network.models.response.characters.ResultsItemCharacter
import com.smart.domain.impl.model.character.CharacterDomainModel

fun ResultsItemCharacter.toDomain(): CharacterDomainModel = this.let {
    val path = it.thumbnail?.path ?: ""
    val extension = it.thumbnail?.extension ?: ""
    CharacterDomainModel(
        idCharacter = requireNotNull(it.id) { "No such field id" },
        link = "$path.$extension",
        name = it.name,
        description = it.description,
    )
}

fun CharacterDomainModel.domainToDatabase(): CharacterDb = this.let {
    CharacterDb(
        idCharacter = it.idCharacter,
        name = it.name,
        description = it.description,
        link = it.link
    )
}

fun CharacterDb.databaseToDomain(): CharacterDomainModel = this.let {
    CharacterDomainModel(
        idCharacter = it.idCharacter,
        name = it.name,
        description = it.description,
        link = it.link
    )
}
