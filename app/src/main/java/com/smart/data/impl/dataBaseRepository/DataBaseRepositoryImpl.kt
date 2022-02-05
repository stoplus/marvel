package com.smart.data.impl.dataBaseRepository

import com.smart.data.impl.dataBaseRepository.model.CharacterDb
import com.smart.data.impl.network.models.response.characters.ResultsItemCharacter
import com.smart.data.impl.network.models.response.comics.ResultsItemComics
import com.smart.data.impl.network.models.response.events.ResultsItemEvents
import com.smart.data.impl.network.models.response.series.ResultsItemSeries
import com.smart.data.impl.network.models.response.stories.ResultsItemStories
import com.smart.domain.api.DataBaseRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.character.mapper.databaseToDomain
import com.smart.domain.impl.model.character.mapper.domainToDatabase
import com.smart.utils.Result
import com.smart.utils.wrapSafe
import io.realm.Realm

class DataBaseRepositoryImpl : DataBaseRepository {

    override suspend fun getCharacters(offset: Int): Result<List<CharacterDomainModel>> =
        wrapSafe {
            val realm: Realm = Realm.getDefaultInstance()
            val list = realm.copyFromRealm(realm.where(CharacterDb::class.java).findAll())
            realm.close()
            list.map { it.databaseToDomain() }
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

    override fun saveCharacters(item: List<CharacterDomainModel>) {
        val list = item.map { it.domainToDatabase() }
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync {
            it.insertOrUpdate(list)
        }
        realm.close()
    }
}
