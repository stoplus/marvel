package com.smart.domain.impl

import com.smart.domain.api.GetCharacterDetailUseCase
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.model.characterDetails.CharacterDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetCharacterDetailUseCaseImpl(
    private val repo: MarvelRepository,
) : GetCharacterDetailUseCase() {

    override suspend fun execute(id: Int): CharacterDetails = coroutineScope {
        val character = async { repo.getCharacter(id) }
        val comics = async { repo.getCharacterComics(id) }
        val events = async { repo.getCharacterEvents(id) }
        val series = async { repo.getCharacterSeries(id) }
        val stories = async { repo.getCharacterStories(id) }
        return@coroutineScope CharacterDetails(
            character = character.await(),
            characterComics = comics.await(),
            characterEvents = events.await(),
            characterSeries = series.await(),
            characterStories = stories.await(),
        )
    }
}