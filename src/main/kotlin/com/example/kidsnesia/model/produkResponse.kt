package com.example.kidsnesia.model

data class ProdukResponse(
    val idProduk: Long,
    val namaProduk: String,
    private val fotoProduk: String // Gunakan variabel privat untuk menyimpan path asli
) {
    val FotoProduk: String
        get() = "http://192.168.1.57:8080/$fotoProduk" // Otomatis menambahkan prefix URL
}


// 🔥 Wrapper untuk daftar produk
data class ProdukListWrapper(
    val error: Boolean,
    val message: String,
    val listProduk: List<ProdukResponse>
)
