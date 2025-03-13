package com.example.kidsnesia.controller

import com.example.kidsnesia.model.WebResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(exception: ConstraintViolationException): ResponseEntity<WebResponse<String>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(WebResponse(message = null, status = exception.message))
    }


    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(exception: ResponseStatusException): ResponseEntity<WebResponse<String>> {
        return ResponseEntity.status(exception.statusCode)
            .body(WebResponse(message = null, status = exception.reason))
    }
}
