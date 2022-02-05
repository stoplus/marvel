package com.smart.data.impl

import com.smart.data.impl.network.models.response.characters.ResultsItemCharacter
import com.smart.data.impl.network.models.response.comics.ResultsItemComics
import com.smart.data.impl.network.models.response.events.ResultsItemEvents
import com.smart.data.impl.network.models.response.series.ResultsItemSeries
import com.smart.data.impl.network.models.response.stories.ResultsItemStories
import com.smart.domain.api.DataBaseRepository
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.utils.Decorator
import com.smart.utils.Result

class MarvelRepositoryImpl(
    private val networkRepository: MarvelRepository,
    private val databaseRepository: DataBaseRepository,
) : MarvelRepository {

    private val decorator = Decorator(
        networkSource = networkRepository,
        databaseSource = databaseRepository
    )

    override suspend fun getCharacters(offset: Int): Result<List<CharacterDomainModel>> {
        return when (val networkUsers = networkRepository.getCharacters(offset)) {
            is Result.Success -> {
                databaseRepository.saveCharacters(networkUsers.data)
                networkUsers
            }
            is Result.Error -> {
                databaseRepository.getCharacters(offset)
            }
        }
    }

    override suspend fun getCharacter(id: Int): ResultsItemCharacter {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterComics(id: Int): List<ResultsItemComics> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterEvents(id: Int): List<ResultsItemEvents> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterSeries(id: Int): List<ResultsItemSeries> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterStories(id: Int): List<ResultsItemStories> {
        TODO("Not yet implemented")
    }
}
