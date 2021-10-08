package com.picpay.desafio.android.data.repository.userRepository

import com.picpay.desafio.android.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun fetchUserList()

    val userFlow: Flow<List<User>>

}