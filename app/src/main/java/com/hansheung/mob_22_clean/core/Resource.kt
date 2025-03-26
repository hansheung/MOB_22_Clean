package com.hansheung.mob_22_clean.core

sealed class Resource<T> {
    class Loading<T>: Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val msg: String): Resource<T>()

}