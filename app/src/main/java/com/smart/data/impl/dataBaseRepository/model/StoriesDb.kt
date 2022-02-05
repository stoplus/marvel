package com.smart.data.impl.dataBaseRepository.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class StoriesDb(
    @PrimaryKey
    var id: String = "",
    var idCharacter: Int = 0,
    var name: String = "",
) : RealmObject()
