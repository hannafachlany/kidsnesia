package com.example.kidsnesia.model


data class NotaPembelianResponse(
    val idPembayaran: Long,
    val idPembelian: Long,
    val bank: String,
    val totalHarga: Int,
    val tanggalBayar: String,
    val detailEvent: List<DetailPembayaranResponse>
)
