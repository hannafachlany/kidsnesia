package com.example.kidsnesia.model

data class UpdateJumlahTiketRequest(
    val idPembelian: Long,
    val items: List<UpdateJumlahTiketItem>
)

data class UpdateJumlahTiketItem(
    val idPembelianEvent: Long,
    val jumlahBaru: Int
)

