package com.smart.data.impl.network

import com.smart.data.api.ApiInterface
import com.smart.data.impl.network.models.response.characters.ResultsItemCharacter
import com.smart.data.impl.network.models.response.comics.ResultsItemComics
import com.smart.data.impl.network.models.response.events.ResultsItemEvents
import com.smart.data.impl.network.models.response.series.ResultsItemSeries
import com.smart.data.impl.network.models.response.stories.ResultsItemStories
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.character.mapper.toDomain
import com.smart.utils.Result
import com.smart.utils.wrapSafe
import timber.log.Timber

class NetworkRepositoryImpl(
    private val api: ApiInterface,
) : MarvelRepository {

    override suspend fun getCharacters(offset: Int): Result<List<CharacterDomainModel>> =
        wrapSafe {
            val dataWrappers = api.getCharacters(LIMIT, offset)
            (dataWrappers.data?.results?.mapNotNull { item ->
                try {
                    item.toDomain()
                } catch (e: IllegalArgumentException) {
                    Timber.d(e)
                    null
                }
            } ?: emptyList())
        }

    override suspend fun getCharacter(id: Int): ResultsItemCharacter {
        val dataWrappers = api.getCharacter(id)
        return (dataWrappers.data?.results ?: emptyList())[0]
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
