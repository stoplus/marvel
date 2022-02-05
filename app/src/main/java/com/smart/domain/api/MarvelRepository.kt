package com.smart.domain.api

import com.smart.data.impl.network.models.response.characters.ResultsItemCharacter
import com.smart.data.impl.network.models.response.comics.ResultsItemComics
import com.smart.data.impl.network.models.response.events.ResultsItemEvents
import com.smart.data.impl.network.models.response.series.ResultsItemSeries
import com.smart.data.impl.network.models.response.stories.ResultsItemStories
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.utils.Result

interface MarvelRepository {
    suspend fun getCharacters(offset: Int): Result<List<CharacterDomainModel>>
    suspend fun getCharacter(id: Int): ResultsItemCharacter
    suspend fun getCharacterComics(id: Int): List<ResultsItemComics>
    suspend fun getCharacterEvents(id: Int): List<ResultsItemEvents>
    suspend fun getCharacterSeries(id: Int): List<ResultsItemSeries>
    suspend fun getCharacterStories(id: Int): List<ResultsItemStories>
}
