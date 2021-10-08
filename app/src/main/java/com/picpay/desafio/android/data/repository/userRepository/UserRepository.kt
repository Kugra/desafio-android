package com.picpay.desafio.android.data.repository.userRepository

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.utils.helper.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun fetchUserList(): Flow<Resource<List<User>>>

    val userFlow: Flow<List<User>>

}