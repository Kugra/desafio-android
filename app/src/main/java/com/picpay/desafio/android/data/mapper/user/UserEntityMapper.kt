package com.picpay.desafio.android.data.mapper.user

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.mapper.BaseMapper
import com.picpay.desafio.android.data.model.User

object UserEntityMapper : BaseMapper<User, UserEntity> {

    override fun transform(obj: User): UserEntity {
        return UserEntity(
            id = obj.id,
            img = obj.img,
            name = obj.name,
            username = obj.username
        )
    }

    override fun transformList(objList: List<User>): List<UserEntity> {
        return objList.map { transform(it) }
    }

}