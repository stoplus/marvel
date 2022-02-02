package com.smart.presentation.impl.charactersScreen.model

data class CharacterPresentModel(
    val idCharacter: Int,
    val link: String,
    val name: String,
    val description: String,
    val onClick: () -> Unit,
)