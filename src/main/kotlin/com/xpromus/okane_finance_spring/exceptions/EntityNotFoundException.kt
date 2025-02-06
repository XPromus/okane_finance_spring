package com.xpromus.okane_finance_spring.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class EntityNotFoundException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause) {
}