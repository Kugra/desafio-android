package com.picpay.desafio.android.data.remote.user

import com.picpay.desafio.android.data.model.User

interface UserRemoteDataSource {

    suspend fun fetchUserList(): List<User>?

}