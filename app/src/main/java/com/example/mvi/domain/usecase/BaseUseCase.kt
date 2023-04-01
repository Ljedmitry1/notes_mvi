package com.example.mvi.domain.usecase

abstract class BaseUseCase<T> {

    abstract suspend fun invoke(): T

}