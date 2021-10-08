package com.picpay.desafio.android.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}