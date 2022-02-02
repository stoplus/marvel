package com.smart.data.impl

import com.smart.data.api.ApiInterface
import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.data.impl.models.response.comics.ResultsItemComics
import com.smart.data.impl.models.response.events.ResultsItemEvents
import com.smart.data.impl.models.response.series.ResultsItemSeries
import com.smart.data.impl.models.response.stories.ResultsItemStories
import com.smart.domain.api.MarvelRepository

class MarvelRepositoryImpl(
    private val api: ApiInterface,
) : MarvelRepository() {

    override suspend fun getCharacters(offset: Int): List<ResultsItem> {
        val dataWrappers = api.getCharacters(LIMIT, offset)
        return dataWrappers.data.results ?: emptyList()
    }

    override suspend fun getCharacter(id: Int): ResultsItem {
        val dataWrappers = api.getCharacter(id)
        return (dataWrappers.data.results ?: emptyList())[0]
    }

    override suspend fun getCharacterComics(id: Int): List<ResultsItemComics> {
        val dataWrappers = api.getCharacterComics(id)
        return dataWrappers.data.results ?: emptyList()
    }

    override suspend fun getCharacterEvents(id: Int): List<ResultsItemEvents> {
        val dataWrappers = api.getCharacterEvents(id)
        return dataWrappers.data.results ?: emptyList()
    }

    override suspend fun getCharacterSeries(id: Int): List<ResultsItemSeries> {
        val dataWrappers = api.getCharacterSeries(id)
        return dataWrappers.data.results ?: emptyList()
    }

    override suspend fun getCharacterStories(id: Int): List<ResultsItemStories> {
        val dataWrappers = api.getCharacterStories(id)
        return dataWrappers.data.results ?: emptyList()
    }

    companion object {
        private const val LIMIT = 10
    }
}