package com.smart.domain.impl.model.character.mapper

import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.impl.model.character.CharacterDomainModel

internal fun ResultsItem.toDomain(): CharacterDomainModel = this.let {
    CharacterDomainModel(
        idCharacter = requireNotNull(it.id) { "No such field id" },
        link = it.thumbnail.path + "." + it.thumbnail.extension,
        name = it.name,
        description = it.description,
    )
}