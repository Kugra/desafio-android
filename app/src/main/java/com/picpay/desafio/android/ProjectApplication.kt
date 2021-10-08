package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.dataSource
import com.picpay.desafio.android.di.networkModules
import com.picpay.desafio.android.di.repositoryModules
import com.picpay.desafio.android.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ProjectApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProjectApplication)
            loadKoinModules(
                networkModules +
                        dataSource +
                        repositoryModules +
                        viewModelModules
            )
        }
    }

}