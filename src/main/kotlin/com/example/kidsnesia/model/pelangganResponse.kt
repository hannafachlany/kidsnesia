package com.example.kidsnesia.model
//response ketika mengakses api/pelanggan/current (login)
data class PelangganResponse(
    val namaPelanggan: String,
    val email: String,
    val noHpPelanggan: String
)