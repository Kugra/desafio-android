package com.picpay.desafio.android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.data.local.db.USER_TABLE

@Entity(tableName = USER_TABLE)
data class UserEntity(
    @PrimaryKey val id: Int,
    val img: String,
    val name: String,
    val username: String
)