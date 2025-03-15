package com.example.kidsnesia.model

data class CheckoutResponse(
    val idPembelian: Long,
    val totalPembelian: Int,
    val status: String,
    val tanggalPembelian: String,
    val items: List<CheckoutEventResponse>
)

data class CheckoutEventResponse(
    val idPembelianEvent: Long,
    val namaEvent: String,
    val jumlah: Int,
    val kategori: String
)

data class CheckoutWrapper(
    val error: Boolean,
    val checkout: CheckoutResponse
)
