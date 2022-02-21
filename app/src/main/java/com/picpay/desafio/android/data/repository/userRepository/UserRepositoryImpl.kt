package com.picpay.desafio.android.data.repository.userRepository

import com.picpay.desafio.android.data.local.user.UserLocalDataSource
import com.picpay.desafio.android.data.mapper.user.UserEntityMapper
import com.picpay.desafio.android.data.mapper.user.UserMapper
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.user.UserRemoteDataSource
import com.picpay.desafio.android.utils.extensions.isTrue
import com.picpay.desafio.android.utils.helper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun fetchUserList(): Flow<Resource<List<User>>> {

        return flow {

            val response = userRemoteDataSource.fetchUserList()

            emit(Resource.loading())

            if (response?.isSuccessful.isTrue()) {
                emit(Resource.success(userRemoteDataSource.fetchUserList()?.body()))

                userLocalDataSource.saveUserList(
                    UserEntityMapper.transformList(
                        userRemoteDataSource.fetchUserList()?.body() ?: emptyList()
                    )
                )

            } else {

                val userList = userLocalDataSource.getUsersList()

                if (userList.isNotEmpty()) {
                    emit(Resource.failGracefully(userList.map { UserMapper.transform(it) }))
                } else {
                    emit(Resource.failure(response?.message()))
                }

            }

        }.flowOn(Dispatchers.IO)

    }

}

