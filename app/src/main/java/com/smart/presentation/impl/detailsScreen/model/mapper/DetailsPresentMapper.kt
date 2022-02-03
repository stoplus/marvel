package com.smart.presentation.impl.detailsScreen.model.mapper

import com.smart.data.impl.models.response.comics.ResultsItemComics
import com.smart.data.impl.models.response.events.ResultsItemEvents
import com.smart.data.impl.models.response.series.ResultsItemSeries
import com.smart.data.impl.models.response.stories.ResultsItemStories
import com.smart.domain.impl.model.characterDetails.CharacterDetails
import com.smart.presentation.impl.detailsScreen.model.DetailsListModel
import com.smart.presentation.impl.detailsScreen.model.DetailsPresentModel
import timber.log.Timber

fun CharacterDetails.toPresent(): DetailsPresentModel = this.let {
    DetailsPresentModel(
        link = it.character.thumbnail.path + "." + it.character.thumbnail.extension,
        description = it.character.description,
        comics = it.characterComics.mapNotNull { res ->
            try {
                res.toPresent()
            } catch (e: IllegalArgumentException) {
                Timber.d(e)
                null
            }
        },
        events = it.characterEvents.mapNotNull { res ->
            try {
                res.toPresent()
            } catch (e: IllegalArgumentException) {
                Timber.d(e)
                null
            }
        },
        series = it.characterSeries.mapNotNull { res ->
            try {
                res.toPresent()
            } catch (e: IllegalArgumentException) {
                Timber.d(e)
                null
            }
        },
        stories = it.characterStories.mapNotNull { res ->
            try {
                res.toPresent()
            } catch (e: IllegalArgumentException) {
                Timber.d(e)
                null
            }
        },
    )
}

private fun ResultsItemComics.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = requireNotNull(it.id) { "No such field id" },
        link = it.thumbnail?.let { thumbnail -> thumbnail.path + "." + thumbnail.extension } ?: "",
        name = it.title
    )
}

private fun ResultsItemEvents.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = requireNotNull(it.id) { "No such field id" },
        link = it.thumbnail?.let { thumbnail -> thumbnail.path + "." + thumbnail.extension } ?: "",
        name = it.title
    )
}

private fun ResultsItemSeries.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = requireNotNull(it.id) { "No such field id" },
        link = it.thumbnail?.let { thumbnail -> thumbnail.path + "." + thumbnail.extension } ?: "",
        name = it.title
    )
}

private fun ResultsItemStories.toPresent(): DetailsListModel = this.let {
    DetailsListModel(
        id = requireNotNull(it.id) { "No such field id" },
        name = it.title
    )
}
