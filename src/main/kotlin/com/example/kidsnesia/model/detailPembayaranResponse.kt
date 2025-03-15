package com.example.kidsnesia.model

data class DetailPembayaranResponse(
    val namaEvent: String,
    val jumlahTiket: Int,
    val hargaEvent: Int // Harga event setelah dikali jumlah tiket
)

data class DetailBayarWrapper(
    val error: Boolean,
    val detailBayar: PembayaranResponse
)