package com.example.kidsnesia.model

data class PembelianEventListResponse(
    val idPembelian: Long,
    val idEvent: Long,
    val namaEvent: String,
    val kategori: String,
    val jumlah: Int,
    val hargaTotal: Int
)

data class PembelianEventListWrapper(
    val error: Boolean,
    val detailBeliEvent: List<PembelianEventListResponse>
)




