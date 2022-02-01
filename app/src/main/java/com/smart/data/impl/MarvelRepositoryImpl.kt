package com.smart.data.impl

import com.smart.data.api.ApiInterface
import com.smart.data.impl.models.response.characters.ResultsItem
import com.smart.domain.api.MarvelRepository

class MarvelRepositoryImpl(
    private val api: ApiInterface,
) : MarvelRepository() {

    override suspend fun getCharacters(offset: Int): List<ResultsItem> {
        val dataWrappers = api.getCharacters(LIMIT, offset)
        return dataWrappers.data.results ?: emptyList()
    }

    override suspend fun getCharacter(id: Int): ResultsItem {
        val dataWrappers = api.getCharacter(id)
        return dataWrappers.data.results?.get(0)!!
    }

    companion object {
        private const val LIMIT = 5
    }
}