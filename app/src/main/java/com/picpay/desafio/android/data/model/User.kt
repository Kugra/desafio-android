package com.picpay.desafio.android.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name="id") val id: Int,
    @Json(name="img") val img: String,
    @Json(name="name") val name: String,
    @Json(name="username") val username: String
)