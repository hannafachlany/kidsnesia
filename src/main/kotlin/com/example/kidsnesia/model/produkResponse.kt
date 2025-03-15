package com.example.kidsnesia.model

data class ProdukResponse(
    val idProduk: Long,
    val namaProduk: String,
    val fotoProduk: String
)

// 🔥 Wrapper untuk daftar produk
data class ProdukListWrapper(
    val error: Boolean,
    val message: String,
    val listProduk: List<ProdukResponse>
)
