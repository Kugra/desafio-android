package com.picpay.desafio.android.data.repository.userRepository

import com.picpay.desafio.android.data.local.user.UserLocalDataSource
import com.picpay.desafio.android.data.mapper.user.UserEntityMapper
import com.picpay.desafio.android.data.mapper.user.UserMapper
import com.picpay.desafio.android.data.remote.user.UserRemoteDataSource
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override val userFlow = userLocalDataSource.userFlow.map { UserMapper.transformList(it) }

    override suspend fun fetchUserList() {
        userLocalDataSource.saveUserList(
            UserEntityMapper.transformList(userRemoteDataSource.fetchUserList() ?: emptyList())
        )
    }

}