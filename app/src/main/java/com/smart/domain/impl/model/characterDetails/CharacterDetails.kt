package com.smart.domain.impl.model.characterDetails

import com.smart.domain.impl.model.character.CharacterDomainModel

data class CharacterDetails(
    val character: CharacterDomainModel,
    val characterComics: List<ResultsItemComicsDomainModel>,
    val characterEvents: List<ResultsItemEventsDomainModel>,
    val characterSeries: List<ResultsItemSeriesDomainModel>,
    val characterStories: List<ResultsItemStoriesDomainModel>,
)
