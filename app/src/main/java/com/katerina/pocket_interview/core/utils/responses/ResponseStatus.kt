package com.katerina.pocket_interview.core.utils.responses

sealed class ResponseStatus {
    class Loading() : ResponseStatus()
    class Success(): ResponseStatus()
    class Error(val throwable: Throwable): ResponseStatus()
}