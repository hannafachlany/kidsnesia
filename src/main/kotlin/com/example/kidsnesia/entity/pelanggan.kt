package com.example.kidsnesia.entity

import jakarta.persistence.*

@Entity
@Table(name = "pelanggan")
data class Pelanggan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelanggan")
    val idPelanggan: Long? = null,

    @Column(name = "email", unique = true, nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "nama_pelanggan", nullable = false)
    var namaPelanggan: String,

    @Column(name = "no_hp_pelanggan", nullable = false)
    var noHpPelanggan: String,

    @Column(name = "token")
    var token: String? = null,

    @Column(name = "token_expired_at")
    var tokenExpiredAt: Long? = null
)