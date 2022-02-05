package com.smart.data.impl.dataBaseRepository

import com.smart.data.impl.dataBaseRepository.model.*
import com.smart.domain.api.DataBaseRepository
import com.smart.domain.impl.model.character.CharacterDomainModel
import com.smart.domain.impl.model.character.mapper.databaseToDomain
import com.smart.domain.impl.model.character.mapper.domainToDatabase
import com.smart.domain.impl.model.characterDetails.ResultsItemComicsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemEventsDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemSeriesDomainModel
import com.smart.domain.impl.model.characterDetails.ResultsItemStoriesDomainModel
import com.smart.domain.impl.model.characterDetails.mapper.databaseToDomain
import com.smart.domain.impl.model.characterDetails.mapper.domainToDatabase
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

    override suspend fun getCharacterComics(id: Int): Result<List<ResultsItemComicsDomainModel>> =
        wrapSafe {
            val realm: Realm = Realm.getDefaultInstance()
            val list = realm.copyFromRealm(realm.where(ComicsDb::class.java)
                .containsValue("idCharacter", id)
                .findAll())
            realm.close()
            list.map { it.databaseToDomain() }
        }

    override suspend fun getCharacterEvents(id: Int): Result<List<ResultsItemEventsDomainModel>> =
        wrapSafe {
            val realm: Realm = Realm.getDefaultInstance()
            val list = realm.copyFromRealm(realm.where(EventsDb::class.java)
                .containsValue("idCharacter", id)
                .findAll())
            realm.close()
            list.map { it.databaseToDomain() }
        }

    override suspend fun getCharacterSeries(id: Int): Result<List<ResultsItemSeriesDomainModel>> =
        wrapSafe {
            val realm: Realm = Realm.getDefaultInstance()
            val list = realm.copyFromRealm(realm.where(SeriesDb::class.java)
                .containsValue("idCharacter", id)
                .findAll())
            realm.close()
            list.map { it.databaseToDomain() }
        }

    override suspend fun getCharacterStories(id: Int): Result<List<ResultsItemStoriesDomainModel>> =
        wrapSafe {
            val realm: Realm = Realm.getDefaultInstance()
            val list = realm.copyFromRealm(realm.where(StoriesDb::class.java)
                .containsValue("idCharacter", id)
                .findAll())
            realm.close()
            list.map { it.databaseToDomain() }
        }

    override fun saveCharacters(item: List<CharacterDomainModel>) {
        val list = item.map { it.domainToDatabase() }
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { it.insertOrUpdate(list) }
        realm.close()
    }

    override fun saveComics(item: List<ResultsItemComicsDomainModel>) {
        val list = item.map { it.domainToDatabase() }
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { it.insertOrUpdate(list) }
        realm.close()
    }

    override fun saveEvents(item: List<ResultsItemEventsDomainModel>) {
        val list = item.map { it.domainToDatabase() }
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { it.insertOrUpdate(list) }
        realm.close()
    }

    override fun saveSeries(item: List<ResultsItemSeriesDomainModel>) {
        val list = item.map { it.domainToDatabase() }
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { it.insertOrUpdate(list) }
        realm.close()
    }

    override fun saveStories(item: List<ResultsItemStoriesDomainModel>) {
        val list = item.map { it.domainToDatabase() }
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { it.insertOrUpdate(list) }
        realm.close()
    }
}
