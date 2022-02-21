package com.picpay.desafio.android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.repository.userRepository.UserRepository
import com.picpay.desafio.android.utils.helper.FailureType
import com.picpay.desafio.android.utils.helper.FailureType.*
import com.picpay.desafio.android.utils.helper.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _usersList: MutableLiveData<List<User>> = MutableLiveData()
    val usersList: LiveData<List<User>>
        get() = _usersList

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _loadingError = MutableLiveData(NONE)
    val loadingError: LiveData<FailureType>
        get() = _loadingError

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {

            userRepository.fetchUserList().collect { resource ->
                when(resource.status) {
                    Status.LOADING -> {
                        _loading.postValue(true)
                        _loadingError.postValue(NONE)
                    }

                    Status.SUCCESS -> {
                        _loading.postValue(false)
                        _loadingError.postValue(NONE)
                        resource.data.let { _usersList.postValue(it) }
                    }

                    Status.FAILURE -> {
                        _loading.postValue(false)
                        _loadingError.postValue(NO_DATA)
                    }

                    Status.FAILED_GRACEFULLY -> {
                        _loading.postValue(false)
                        _loadingError.postValue(GRACEFULLY)
                        resource.data.let { _usersList.postValue(it) }
                    }
                }
            }
        }
    }

}