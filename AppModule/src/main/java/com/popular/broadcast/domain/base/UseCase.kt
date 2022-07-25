package com.popular.broadcast.domain.base

interface UseCase<T, U> {

    fun execute(param: T): U
}