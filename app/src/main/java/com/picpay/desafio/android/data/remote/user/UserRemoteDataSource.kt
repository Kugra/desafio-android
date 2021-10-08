package com.picpay.desafio.android.data.remote.user

import com.picpay.desafio.android.data.model.User
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun fetchUserList(): Response<List<User>>?

}