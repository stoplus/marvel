package com.smart.presentation.impl.charactersScreen.model.mapper

import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel

internal fun CharacterDomainModel.toPresent(onClick: () -> Unit): CharacterPresentModel = this.let {
    CharacterPresentModel(
        idCharacter = it.idCharacter,
        link = it.link,
        name = it.name,
        description = it.description,
        onClick = onClick,
    )
}