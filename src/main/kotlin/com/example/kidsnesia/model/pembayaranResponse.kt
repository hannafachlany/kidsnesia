package com.example.kidsnesia.model

data class PembayaranResponse(
    val idPembayaran: Int?,
    val idPembelian: Long?,
    val totalHarga: Int,
    val tanggalBayar: String,
    val status: String,
    val detailEvent: List<DetailPembayaranResponse>
)
