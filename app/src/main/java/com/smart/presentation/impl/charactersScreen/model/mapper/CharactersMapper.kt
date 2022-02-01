package com.smart.presentation.impl.charactersScreen.model.mapper

import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.presentation.impl.charactersScreen.model.CharacterPresentModel

internal fun ResultsItem.toPresent(onClick: () -> Unit): CharacterPresentModel = this.let {
    CharacterPresentModel(
        idCharacter = it.id,
        link = it.thumbnail.path + "." + it.thumbnail.extension,
        name = it.name,
        description = it.description,
        onClick = onClick,
    )
}