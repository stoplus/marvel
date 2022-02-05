package com.smart.domain.impl

import com.smart.domain.api.GetCharacterDetailUseCase
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.characterDetails.CharacterDetails
import com.smart.utils.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetCharacterDetailUseCaseImpl(
    private val repo: MarvelRepository,
) : GetCharacterDetailUseCase {

    override suspend fun execute(model: CharacterDomainModel): CharacterDetails = coroutineScope {
        val comics = async { repo.getCharacterComics(model.idCharacter) }
        val events = async { repo.getCharacterEvents(model.idCharacter) }
        val series = async { repo.getCharacterSeries(model.idCharacter) }
        val stories = async { repo.getCharacterStories(model.idCharacter) }
        return@coroutineScope CharacterDetails(
            character = model,
            characterComics = when (val res = comics.await()) {
                is Result.Success -> res.data
                is Result.Error -> emptyList()
            },
            characterEvents = when (val res = events.await()) {
                is Result.Success -> res.data
                is Result.Error -> emptyList()
            },
            characterSeries = when (val res = series.await()) {
                is Result.Success -> res.data
                is Result.Error -> emptyList()
            },
            characterStories = when (val res = stories.await()) {
                is Result.Success -> res.data
                is Result.Error -> emptyList()
            }
        )
    }
}
