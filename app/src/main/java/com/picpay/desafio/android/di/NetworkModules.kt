package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.service.picpay.providePicPayService
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val provideMoshi = module {
    single { Moshi.Builder().build() }
}

private val provideRetrofit = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

}

private val picPayServiceModule = module {
    single { providePicPayService(get()) }
}

val networkModules = provideMoshi +
        provideRetrofit +
        picPayServiceModule
