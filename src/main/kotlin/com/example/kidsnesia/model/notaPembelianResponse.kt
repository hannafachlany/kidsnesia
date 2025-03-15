package com.example.kidsnesia.model

import java.lang.Error

data class NotaPembelianResponse(
    val idPembayaran: Long,
    val idPembelian: Long,
    val bank: String,
    val totalHarga: Int,
    val tanggalBayar: String,
    val detailEvent: List<DetailPembayaranResponse>
)

data class NotaWrapper(
    val error: Boolean,
    val nota:NotaPembelianResponse
)
