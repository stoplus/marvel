package com.smart.data.impl.dataBaseRepository.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ComicsDb(
    @PrimaryKey
    var id: String = "",
    var idCharacter: Int = 0,
    var link: String = "",
    var name: String = "",
) : RealmObject()
