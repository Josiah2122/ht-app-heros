package com.hellotractor.notes.networok

import java.io.IOException

interface NetworkExceptions {

    open class RequestError(message: String?) : IOException(message)

    open class ServerError(message: String?) : RequestError(message)

    open class ClientError(
        private val code: Int = -1,
        message: String? = null,
        private val errorCode: Long = -1
    ) : RequestError(message) {

        fun code(): Int = code

        fun errorCode(): Long = errorCode

        fun isConflict(): Boolean = code == 409

        fun isNotFound(): Boolean = code == 404
    }

    class AuthenticationError(message: String?) : ClientError(401, message)
}
