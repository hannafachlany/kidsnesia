package com.example.kidsnesia.model

data class PembelianResponse(
    val idPembelian: Long,
    val totalPembelian: Int,
    val status: String,
    val tanggalPembelian: String,
    val items: List<PembelianEventResponse>
)

data class PembelianEventResponse(
    val idPembelianEvent: Long,
    val namaEvent: String,
    val jumlah: Int,
    val kategori: String
)

data class PembelianWrapper(
    val error: Boolean,
    val pembelianResponse: PembelianResponse
)
