package com.picpay.desafio.android.utils.helper

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {

    companion object {

        fun <T : Any> loading(): Resource<T> {
            return Resource(Status.LOADING)
        }

        fun <T : Any> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T : Any> failure(msg: String?, data: T? = null): Resource<T> {
            return Resource(Status.FAILURE, data, msg)
        }

        fun <T: Any> failGracefully(data: T?): Resource<T> {
            return Resource(Status.FAILED_GRACEFULLY, data)
        }

    }

}