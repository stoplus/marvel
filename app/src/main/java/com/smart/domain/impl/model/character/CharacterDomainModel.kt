package com.smart.domain.impl.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDomainModel(
    val idCharacter: Int = 0,
    val link: String = "",
    val name: String = "",
    val description: String = "",
) : Parcelable
