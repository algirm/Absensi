package com.noob.absensi.util

sealed class Resources<out T> {
    class Loading<out T> : Resources<T>()
    data class Success<out T>(val data: T) : Resources<T>()
    data class Failure(val throwable: Throwable?) : Resources<Nothing>()
}
