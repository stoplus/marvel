package com.smart.domain.impl.model.characterDetails

import com.smart.data.impl.network.models.response.characters.ResultsItemCharacter
import com.smart.data.impl.network.models.response.comics.ResultsItemComics
import com.smart.data.impl.network.models.response.events.ResultsItemEvents
import com.smart.data.impl.network.models.response.series.ResultsItemSeries
import com.smart.data.impl.network.models.response.stories.ResultsItemStories

data class CharacterDetails(
    val character: ResultsItemCharacter,
    val characterComics: List<ResultsItemComics>,
    val characterEvents: List<ResultsItemEvents>,
    val characterSeries: List<ResultsItemSeries>,
    val characterStories: List<ResultsItemStories>,
)
