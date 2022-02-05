package com.smart.domain.impl.model.characterDetails.mapper

import com.smart.data.impl.dataBaseRepository.model.ComicsDb
import com.smart.data.impl.network.models.response.comics.ResultsItemComics
import com.smart.domain.impl.model.characterDetails.ResultsItemComicsDomainModel

fun ResultsItemComics.networkToDomain(idCharacter: Int): ResultsItemComicsDomainModel = this.let {
    val path = it.thumbnail?.path ?: ""
    val extension = it.thumbnail?.extension ?: ""
    ResultsItemComicsDomainModel(
        idCharacter = idCharacter,
        id = requireNotNull(it.id) { "No such field id" },
        link = "$path.$extension",
        name = it.title,
    )
}

fun ComicsDb.databaseToDomain(): ResultsItemComicsDomainModel = this.let {
    ResultsItemComicsDomainModel(
        idCharacter = it.idCharacter,
        id = it.id,
        link = it.link,
        name = it.name,
    )
}

fun ResultsItemComicsDomainModel.domainToDatabase(): ComicsDb = this.let {
    ComicsDb(
        idCharacter = it.idCharacter,
        id = it.id,
        link = it.link,
        name = it.name,
    )
}
