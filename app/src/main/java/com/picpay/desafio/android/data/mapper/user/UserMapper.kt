package com.picpay.desafio.android.data.mapper.user

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.mapper.BaseMapper
import com.picpay.desafio.android.data.model.User

object UserMapper : BaseMapper<UserEntity, User> {

    override fun transform(obj: UserEntity): User {
        return User(id = obj.id,
            img = obj.img,
            name = obj.name,
            username = obj.username)
    }

    override fun transformList(objList: List<UserEntity>): List<User> {
        return objList.map { transform(it) }
    }

}