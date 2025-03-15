package com.example.kidsnesia.model

import java.time.format.DateTimeFormatter

// Data model untuk event yang dibeli
data class EventYangDibeli(
    val namaEvent: String,
    val jumlahTiket: Int,
    val hargaEvent: Int,
    val jadwalEvent: String // Format: "Hari, dd MMM yyyy"
)

// Data model untuk nota pembelian
data class NotaPembelianResponse(
    val idPembayaran: Long,
    val idPembelian: Long,
    val bank: String,
    val totalHarga: Int,
    val tanggalBayar: String,
    val namaPelanggan: String,
    val email: String,
    val noHpPelanggan: String,
    val tanggalPembelian: String,
    val detailEvent: List<EventYangDibeli>
)

data class NotaWrapper(
    val error: Boolean,
    val nota: NotaPembelianResponse
)