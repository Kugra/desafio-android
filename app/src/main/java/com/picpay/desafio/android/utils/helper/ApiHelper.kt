package com.picpay.desafio.android.utils.helper

object ApiHelper {

    suspend fun <ResponseType> responseHandler(
        default: ResponseType,
        apiCall: suspend () -> ResponseType
    ): ResponseType {

        return runCatching {
            apiCall()
        }.getOrElse {
            default
        }
    }
}
