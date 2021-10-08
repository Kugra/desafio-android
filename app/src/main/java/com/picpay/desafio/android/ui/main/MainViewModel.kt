package com.picpay.desafio.android.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.repository.userRepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository): ViewModel() {

    val userLiveData = userRepository.userFlow.asLiveData(Dispatchers.IO)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.fetchUserList()
        }
    }

}