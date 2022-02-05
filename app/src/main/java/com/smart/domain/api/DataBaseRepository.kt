package com.smart.domain.api

import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemComicsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemEventsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemSeriesDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemStoriesDomainModel

interface DataBaseRepository : MarvelRepository {
    fun saveCharacters(item: List<CharacterDomainModel>)
    fun saveComics(item: List<ResultsItemComicsDomainModel>)
    fun saveEvents(item: List<ResultsItemEventsDomainModel>)
    fun saveSeries(item: List<ResultsItemSeriesDomainModel>)
    fun saveStories(item: List<ResultsItemStoriesDomainModel>)
}
