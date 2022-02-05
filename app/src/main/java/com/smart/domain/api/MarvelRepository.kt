package com.smart.domain.api

import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemComicsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemEventsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemSeriesDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemStoriesDomainModel
import com.smart.utils.Result

interface MarvelRepository {
    suspend fun getCharacters(offset: Int): Result<List<CharacterDomainModel>>
    suspend fun getCharacterComics(id: Int): Result<List<ResultsItemComicsDomainModel>>
    suspend fun getCharacterEvents(id: Int): Result<List<ResultsItemEventsDomainModel>>
    suspend fun getCharacterSeries(id: Int): Result<List<ResultsItemSeriesDomainModel>>
    suspend fun getCharacterStories(id: Int): Result<List<ResultsItemStoriesDomainModel>>
}
