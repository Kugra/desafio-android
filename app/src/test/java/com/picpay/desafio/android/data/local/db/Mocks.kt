package com.picpay.desafio.android.data.local.db

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.model.User

object Mocks {

    val userEntityMock = UserEntity(0, "imgUrl", "Johan", "Kuggra")

    val userEntityListMock = listOf(
        UserEntity(0, "imgUrl", "Johan", "Kuggra"),
        UserEntity(1, "imgUrl", "Ferraz", "Barrabhax")
    )

    val userMock = User(0, "imgUrl", "Johan", "Kuggra")

    val userListMock = listOf(
        User(0, "imgUrl", "Johan", "Kuggra"),
        User(0, "imgUrl", "Ferraz", "Barrabhax")
    )

}