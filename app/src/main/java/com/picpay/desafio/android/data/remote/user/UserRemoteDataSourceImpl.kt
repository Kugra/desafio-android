package com.picpay.desafio.android.data.remote.user

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.service.picpay.PicPayService
import com.picpay.desafio.android.utils.helper.ApiHelper

class UserRemoteDataSourceImpl(
    private val service: PicPayService,
) : UserRemoteDataSource {

    override suspend fun fetchUserList(): List<User> {

        return ApiHelper.responseHandler(emptyList()) {
            service.getUsers()
        }

    }

}