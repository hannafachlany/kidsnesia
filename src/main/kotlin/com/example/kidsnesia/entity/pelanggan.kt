package com.example.kidsnesia.entity

import jakarta.persistence.*

@Entity
@Table(name = "pelanggan")
data class Pelanggan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelanggan")
    val id_pelanggan: Long? = null, // ID auto-increment

    @Column(name = "email", unique = true, nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "nama_pelanggan", nullable = false)
    val nama_pelanggan: String,

    @Column(name = "no_hp_pelanggan", nullable = false)
    val no_hp_pelanggan: String,

    @Column(name = "token", nullable = true)
    var token: String? = null,

    @Column(name = "token_expired_at", nullable = true)
    var tokenExpiredAt: Long? = null
)
