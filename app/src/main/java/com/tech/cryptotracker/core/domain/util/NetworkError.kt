package com.tech.cryptotracker.core.domain.util

enum class NetworkError : Error{

    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    NO_INTERNET_CONNECTION,
    SERIALIZATION_ERROR,
    UNKNOWN_ERROR
}