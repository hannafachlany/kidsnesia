package com.example.kidsnesia.model

data class PembelianRequest(
    val items: List<PembelianItem>
)

data class PembelianItem(
    val idEvent: Long,
    val jumlah: Int
)
