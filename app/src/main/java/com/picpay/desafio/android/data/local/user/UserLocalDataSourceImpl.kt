package com.picpay.desafio.android.data.local.user

import com.picpay.desafio.android.data.local.db.UserDao
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.model.User

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {

    override val userFlow = userDao.flow()

    override suspend fun saveUserList(userList: List<UserEntity>) {
        userList.ifEmpty { null }?.let {
            userDao.insertReplace(it)
        }
    }

}