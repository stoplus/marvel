package com.smart.domain.impl.model.characterDetails

import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.data.impl.models.response.comics.ResultsItemComics
import com.smart.data.impl.models.response.events.ResultsItemEvents
import com.smart.data.impl.models.response.series.ResultsItemSeries
import com.smart.data.impl.models.response.stories.ResultsItemStories

data class CharacterDetails(
    val character: ResultsItem,
    val characterComics: List<ResultsItemComics>,
    val characterEvents: List<ResultsItemEvents>,
    val characterSeries: List<ResultsItemSeries>,
    val characterStories: List<ResultsItemStories>,
)
