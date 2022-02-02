package com.smart.domain.api

import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.data.impl.models.response.comics.ResultsItemComics
import com.smart.data.impl.models.response.events.ResultsItemEvents
import com.smart.data.impl.models.response.series.ResultsItemSeries
import com.smart.data.impl.models.response.stories.ResultsItemStories

abstract class MarvelRepository {
    abstract suspend fun getCharacters(offset: Int): List<ResultsItem>
    abstract suspend fun getCharacter(id: Int): ResultsItem
    abstract suspend fun getCharacterComics(id: Int): List<ResultsItemComics>
    abstract suspend fun getCharacterEvents(id: Int): List<ResultsItemEvents>
    abstract suspend fun getCharacterSeries(id: Int): List<ResultsItemSeries>
    abstract suspend fun getCharacterStories(id: Int): List<ResultsItemStories>
}