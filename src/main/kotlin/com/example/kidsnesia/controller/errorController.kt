package com.example.kidsnesia.controller

import com.example.kidsnesia.model.webResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(exception: ConstraintViolationException): ResponseEntity<webResponse<String>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(webResponse(data = null, errors = exception.message))
    }


    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(exception: ResponseStatusException): ResponseEntity<webResponse<String>> {
        return ResponseEntity.status(exception.statusCode)
            .body(webResponse(data = null, errors = exception.reason))
    }
}
