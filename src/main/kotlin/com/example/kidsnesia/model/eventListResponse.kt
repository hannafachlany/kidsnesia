package com.example.kidsnesia.model

//response yang dikeluarkan ketika mengakses API list event
data class EventListResponse(
    val idEvent: Long,
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
        return "http://192.168.1.33:8080/$fotoEvent" // Menghasilkan URL lengkap
    }
}

data class EventListWrapper(
    val error: Boolean,
    val message: String,
    val listEvent: List<EventListResponse>
)