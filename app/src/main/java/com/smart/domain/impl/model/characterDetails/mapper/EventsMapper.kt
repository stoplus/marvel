package com.smart.domain.impl.model.characterDetails.mapper

import com.smart.data.impl.dataBaseRepository.model.EventsDb
import com.smart.data.impl.network.models.response.events.ResultsItemEvents
import com.smart.domain.impl.model.characterDetails.ResultsItemEventsDomainModel

fun ResultsItemEvents.networkToDomain(idCharacter: Int): ResultsItemEventsDomainModel = this.let {
    val path = it.thumbnail?.path ?: ""
    val extension = it.thumbnail?.extension ?: ""
    ResultsItemEventsDomainModel(
        idCharacter = idCharacter,
        id = requireNotNull(it.id) { "No such field id" },
        link = "$path.$extension",
        name = it.title,
    )
}

fun EventsDb.databaseToDomain(): ResultsItemEventsDomainModel = this.let {
    ResultsItemEventsDomainModel(
        idCharacter = it.idCharacter,
        id = it.id,
        link = it.link,
        name = it.name,
    )
}

fun ResultsItemEventsDomainModel.domainToDatabase(): EventsDb = this.let {
    EventsDb(
        idCharacter = it.idCharacter,
        id = it.id,
        link = it.link,
        name = it.name,
    )
}
