package com.example.kidsnesia.model

data class EventDetailResponse(
    val idEvent: Int,
    val namaEvent: String,
    val jadwalEvent: String,
    val jadwalEventDay: String,
    val fotoEvent: String,
    val deskripsiEvent: String,
    val kuota: Int,
    val kategori: String,
    val hargaEvent: Int
) {
    fun getFullImageUrl(): String {
        return "http://192.168.1.16:8080/$fotoEvent"
    }
}

// 🔥 Wrapper untuk detail event
data class EventDetailWrapper(
    val error: Boolean,
    val message: String,
    val detailEvent: EventDetailResponse
)
