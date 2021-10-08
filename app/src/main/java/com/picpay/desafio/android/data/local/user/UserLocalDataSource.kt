package com.picpay.desafio.android.data.local.user

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun saveUserList(userList: List<UserEntity>)

    val userFlow: Flow<List<UserEntity>>

}