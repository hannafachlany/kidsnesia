package com.example.kidsnesia.service

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class ValidationService @Autowired constructor(
    private val validator: Validator
) {
    fun validate(request: Any) {
        val violations = validator.validate(request)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
