package com.smart.data.impl

import com.smart.domain.api.DataBaseRepository
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemComicsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemEventsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemSeriesDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemStoriesDomainModel
import com.smart.utils.Result

class MarvelRepositoryImpl(
    private val networkRepository: MarvelRepository,
    private val databaseRepository: DataBaseRepository,
) : MarvelRepository {

    override suspend fun getCharacters(offset: Int): Result<List<CharacterDomainModel>> =
        when (val networkResult = networkRepository.getCharacters(offset)) {
            is Result.Success -> {
                databaseRepository.saveCharacters(networkResult.data)
                networkResult
            }
            is Result.Error -> databaseRepository.getCharacters(offset)
        }

    override suspend fun getCharacterComics(id: Int): Result<List<ResultsItemComicsDomainModel>> =
        when (val networkResult = networkRepository.getCharacterComics(id)) {
            is Result.Success -> {
                databaseRepository.saveComics(networkResult.data)
                networkResult
            }
            is Result.Error -> databaseRepository.getCharacterComics(id)
        }

    override suspend fun getCharacterEvents(id: Int): Result<List<ResultsItemEventsDomainModel>> =
        when (val networkResult = networkRepository.getCharacterEvents(id)) {
            is Result.Success -> {
                databaseRepository.saveEvents(networkResult.data)
                networkResult
            }
            is Result.Error -> databaseRepository.getCharacterEvents(id)
        }

    override suspend fun getCharacterSeries(id: Int): Result<List<ResultsItemSeriesDomainModel>> =
        when (val networkResult = networkRepository.getCharacterSeries(id)) {
            is Result.Success -> {
                databaseRepository.saveSeries(networkResult.data)
                networkResult
            }
            is Result.Error -> databaseRepository.getCharacterSeries(id)
        }

    override suspend fun getCharacterStories(id: Int): Result<List<ResultsItemStoriesDomainModel>> =
        when (val networkResult = networkRepository.getCharacterStories(id)) {
            is Result.Success -> {
                databaseRepository.saveStories(networkResult.data)
                networkResult
            }
            is Result.Error -> databaseRepository.getCharacterStories(id)
        }
}
