package com.picpay.desafio.android.data.remote.user

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.service.picpay.PicPayService
import retrofit2.Response

class UserRemoteDataSourceImpl(
    private val service: PicPayService,
) : UserRemoteDataSource {

    override suspend fun fetchUserList(): Response<List<User>> {
        return service.getUsers()
    }

}