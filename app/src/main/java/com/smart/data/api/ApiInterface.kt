package com.smart.data.api

import com.smart.data.impl.network.models.response.characters.CharacterDataWrapper
import com.smart.data.impl.network.models.response.comics.ComicDataWrapper
import com.smart.data.impl.network.models.response.events.EventDataWrapper
import com.smart.data.impl.network.models.response.series.SeriesDataWrapper
import com.smart.data.impl.network.models.response.stories.StoryDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): CharacterDataWrapper

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
    ): ComicDataWrapper

    @GET("characters/{characterId}/events")
    suspend fun getCharacterEvents(
        @Path("characterId") characterId: Int,
    ): EventDataWrapper

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: Int,
    ): SeriesDataWrapper

    @GET("characters/{characterId}/stories")
    suspend fun getCharacterStories(
        @Path("characterId") characterId: Int,
    ): StoryDataWrapper
}
