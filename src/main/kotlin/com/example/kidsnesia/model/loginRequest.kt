package com.example.kidsnesia.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


//request yang perlu diisi user, isinya:
data class LoginRequest(
    @field:NotBlank
    @field:Size(max = 225)
    val email: String,

    @field:NotBlank
    @field:Size(max = 225)
    val password: String
)
