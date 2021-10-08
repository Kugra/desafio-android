package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.data.local.db.DATABASE_NAME
import com.picpay.desafio.android.data.local.db.ProjectDatabase
import com.picpay.desafio.android.data.local.user.UserLocalDataSource
import com.picpay.desafio.android.data.local.user.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.remote.user.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.user.UserRemoteDataSourceImpl
import org.koin.dsl.module

private val userLocalDataSource = module {
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
}

private val userRemoteDataSource = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
}

private val databaseModule = module {
    fun provideDao(projectDatabase: ProjectDatabase) = projectDatabase.userDao()

    single {
        Room.databaseBuilder(
            get(),
            ProjectDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single { provideDao(get()) }

}


val dataSource = databaseModule +
        userLocalDataSource +
        userRemoteDataSource