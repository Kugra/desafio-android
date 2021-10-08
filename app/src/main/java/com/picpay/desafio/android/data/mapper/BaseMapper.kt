package com.picpay.desafio.android.data.mapper

interface BaseMapper<Input, Output> {

    fun transform(obj: Input): Output

    fun transformList(objList: List<Input>): List<Output>

}