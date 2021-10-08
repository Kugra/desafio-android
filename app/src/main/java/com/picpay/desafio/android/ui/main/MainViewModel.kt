package com.picpay.desafio.android.ui.main

import android.widget.Toast
import androidx.lifecycle.*
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.repository.userRepository.UserRepository
import com.picpay.desafio.android.utils.helper.Resource
import com.picpay.desafio.android.utils.helper.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository): ViewModel() {

    val userLiveData = userRepository.userFlow.asLiveData(Dispatchers.IO)

    private val _livedata: MutableLiveData<List<User>> = MutableLiveData()
    val liveData: LiveData<List<User>>
        get() = _livedata

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        viewModelScope.launch(Dispatchers.IO) {

            userRepository.fetchUserList().collect { resource ->
                when(resource.status) {
                    Status.LOADING -> _loading.postValue(true)

                    Status.SUCCESS -> {
                        _loading.postValue(false)
                        if (resource.data != null) {
                            _livedata.postValue(resource.data)
                        }
                    }

                    Status.FAILURE -> {
                        _loading.postValue(false)
                    }
                }

            }
        }
    }

}