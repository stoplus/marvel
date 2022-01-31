package com.smart.data.api

import com.smart.data.impl.models.response.characters.CharacterDataWrapper
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): CharacterDataWrapper

    @GET("characters/{characterId}")
    fun getCharacter(
        @Path("characterId") characterId: Int?,
    ): CharacterDataWrapper
}