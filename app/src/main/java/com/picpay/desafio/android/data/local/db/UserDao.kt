package com.picpay.desafio.android.data.local.db

import androidx.room.Dao
import androidx.room.Query
import com.picpay.desafio.android.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao: BaseDao<UserEntity, Int>(USER_TABLE) {

    @Query("SELECT * FROM $USER_TABLE")
    abstract fun flow(): Flow<List<UserEntity>>

}