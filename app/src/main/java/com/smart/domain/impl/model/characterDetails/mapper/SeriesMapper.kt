package com.smart.domain.impl.model.characterDetails.mapper

import com.smart.data.impl.dataBaseRepository.model.SeriesDb
import com.smart.data.impl.network.models.response.series.ResultsItemSeries
import com.smart.domain.impl.model.characterDetails.ResultsItemSeriesDomainModel

fun ResultsItemSeries.networkToDomain(idCharacter: Int): ResultsItemSeriesDomainModel = this.let {
    val path = it.thumbnail?.path ?: ""
    val extension = it.thumbnail?.extension ?: ""
    ResultsItemSeriesDomainModel(
        idCharacter = idCharacter,
        id = requireNotNull(it.id) { "No such field id" },
        link = "$path.$extension",
        name = it.title,
    )
}

fun SeriesDb.databaseToDomain(): ResultsItemSeriesDomainModel = this.let {
    ResultsItemSeriesDomainModel(
        idCharacter = it.idCharacter,
        id = it.id,
        link = it.link,
        name = it.name,
    )
}

fun ResultsItemSeriesDomainModel.domainToDatabase(): SeriesDb = this.let {
    SeriesDb(
        idCharacter = it.idCharacter,
        id = it.id,
        link = it.link,
        name = it.name,
    )
}
