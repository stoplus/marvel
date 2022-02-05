package com.smart.presentation.impl.detailsScreen.model.mapper

import com.smart.domain.impl.model.characterDetails.*
import com.smart.presentation.impl.detailsScreen.model.DetailsListModel
import com.smart.presentation.impl.detailsScreen.model.DetailsPresentModel

fun CharacterDetails.toPresent(): DetailsPresentModel = this.let {
    DetailsPresentModel(
        link = it.character.link,
        description = it.character.description,
        comics = it.characterComics.map { model -> model.toPresent() },
        events = it.characterEvents.map { model -> model.toPresent() },
        series = it.characterSeries.map { model -> model.toPresent() },
        stories = it.characterStories.map { model -> model.toPresent() },
    )
}

private fun ResultsItemComicsDomainModel.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = it.id,
        link = it.link,
        name = it.name
    )
}

private fun ResultsItemEventsDomainModel.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = it.id,
        link = it.link,
        name = it.name
    )
}

private fun ResultsItemSeriesDomainModel.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = it.id,
        link = it.link,
        name = it.name
    )
}

private fun ResultsItemStoriesDomainModel.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = it.id,
        name = it.name
    )
}
