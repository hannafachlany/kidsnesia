package com.example.kidsnesia.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "pembelian")
data class Pembelian(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pembelian")
    val idPembelian: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_pelanggan", nullable = false)
    val pelanggan: Pelanggan,

    @Column(name = "tanggal_pembelian", nullable = false)
    val tanggalPembelian: Timestamp = Timestamp(System.currentTimeMillis()),

    @Column(name = "total_pembelian", nullable = false)
    var totalPembelian: Int,

    @Column(name = "status_pembelian", nullable = false)
    var status: String = "Pending"
)
