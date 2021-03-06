package com.vnprk.dhca.models

data class Result<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T>nothing(): Result<T> = Result(Status.NOTHING, null, null)
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data,   null)
        }

        fun <T> error(message: String?): Result<T> {
            return Result(Status.ERROR, null, message)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, message=$message)"
    }
}