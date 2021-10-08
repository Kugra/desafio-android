package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.repository.userRepository.UserRepository
import com.picpay.desafio.android.data.repository.userRepository.UserRepositoryImpl
import org.koin.dsl.module


private val userRepository = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

val repositoryModules = userRepository