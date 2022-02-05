package com.smart.data.impl.network

import com.smart.data.api.ApiInterface
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.character.mapper.toDomain
import com.smart.domain.impl.model.characterDetails.ResultsItemComicsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemEventsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemSeriesDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemStoriesDomainModel
import com.smart.domain.impl.model.characterDetails.mapper.networkToDomain
import com.smart.utils.Result
import com.smart.utils.wrapSafe
import timber.log.Timber

class NetworkRepositoryImpl(
    private val api: ApiInterface,
) : MarvelRepository {

    override suspend fun getCharacters(offset: Int): Result<List<CharacterDomainModel>> =
        wrapSafe {
            val dataWrappers = api.getCharacters(LIMIT, offset)
            dataWrappers.data?.results?.mapNotNull { item ->
                try {
                    item.toDomain()
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                    null
                }
            } ?: emptyList()
        }

    override suspend fun getCharacterComics(id: Int): Result<List<ResultsItemComicsDomainModel>> =
        wrapSafe {
            val dataWrappers = api.getCharacterComics(id)
            dataWrappers.data?.results?.mapNotNull { item ->
                try {
                    item.networkToDomain(id)
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                    null
                }
            } ?: emptyList()
        }

    override suspend fun getCharacterEvents(id: Int): Result<List<ResultsItemEventsDomainModel>> =
        wrapSafe {
            val dataWrappers = api.getCharacterEvents(id)
            dataWrappers.data?.results?.mapNotNull { item ->
                try {
                    item.networkToDomain(id)
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                    null
                }
            } ?: emptyList()
        }

    override suspend fun getCharacterSeries(id: Int): Result<List<ResultsItemSeriesDomainModel>> =
        wrapSafe {
            val dataWrappers = api.getCharacterSeries(id)
            dataWrappers.data?.results?.mapNotNull { item ->
                try {
                    item.networkToDomain(id)
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                    null
                }
            } ?: emptyList()
        }

    override suspend fun getCharacterStories(id: Int): Result<List<ResultsItemStoriesDomainModel>> =
        wrapSafe {
            val dataWrappers = api.getCharacterStories(id)
            dataWrappers.data?.results?.mapNotNull { item ->
                try {
                    item.networkToDomain(id)
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                    null
                }
            } ?: emptyList()
        }

    companion object {
        private const val LIMIT = 10
    }
}
