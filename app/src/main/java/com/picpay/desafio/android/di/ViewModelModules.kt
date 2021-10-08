package com.picpay.desafio.android.di

import com.picpay.desafio.android.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val viewModelModules = mainViewModelModule