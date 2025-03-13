package com.example.kidsnesia.model


import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(

    @field:NotBlank
    @Size(max = 225)
    val namaPelanggan: String,

    @field:NotBlank
    @Size(max = 225)
    val email: String,

    @field:NotBlank
    @Size(max = 225)
    val password: String,

    @field:NotBlank
    @Size(max = 225)
    val noHpPelanggan: String
)
