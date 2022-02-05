package com.smart.domain.impl.model.characterDetails.mapper

import com.smart.data.impl.dataBaseRepository.model.StoriesDb
import com.smart.data.impl.network.models.response.stories.ResultsItemStories
import com.smart.domain.impl.model.characterDetails.ResultsItemStoriesDomainModel

fun ResultsItemStories.networkToDomain(idCharacter: Int): ResultsItemStoriesDomainModel = this.let {
    ResultsItemStoriesDomainModel(
        idCharacter = idCharacter,
        id = requireNotNull(it.id) { "No such field id" },
        name = it.title,
    )
}

fun StoriesDb.databaseToDomain(): ResultsItemStoriesDomainModel = this.let {
    ResultsItemStoriesDomainModel(
        idCharacter = it.idCharacter,
        id = it.id,
        name = it.name,
    )
}

fun ResultsItemStoriesDomainModel.domainToDatabase(): StoriesDb = this.let {
    StoriesDb(
        idCharacter = it.idCharacter,
        id = it.id,
        name = it.name,
    )
}
