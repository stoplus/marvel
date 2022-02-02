package com.smart.domain.impl.model.character

data class CharacterDomainModel(
    val idCharacter: Int,
    val link: String = "",
    val name: String = "",
    val description: String = "",
)
