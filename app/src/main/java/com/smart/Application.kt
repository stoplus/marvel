package com.smart

import android.app.Application
import com.smart.di.appModules
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        initRealm()
        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ALL)
        }
        startKoin {
            androidContext(this@Application)
            modules(appModules)
        }
    }

    private fun initRealm() {
        Realm.init(this@Application)
        val config = RealmConfiguration.Builder()
            .name("marvel_db")
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}