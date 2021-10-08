package com.picpay.desafio.android.data.service.picpay

import retrofit2.Retrofit

fun providePicPayService(retrofit: Retrofit): PicPayService =
    retrofit.create(PicPayService::class.java)

