package com.example.kidsnesia.entity

import jakarta.persistence.*

@Entity
@Table(name = "produk")
data class Produk(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produk")
    val idProduk: Long? = null,

    @Column(name = "nama_produk", nullable = false)
    val namaProduk: String,

    @Column(name = "foto_produk", nullable = false)
    val fotoProduk: String
)
