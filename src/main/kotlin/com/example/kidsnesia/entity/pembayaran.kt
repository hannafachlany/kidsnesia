package com.example.kidsnesia.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "pembayaran")
data class Pembayaran(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pembayaran")
    val idPembayaran: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_pembelian", nullable = false)
    val pembelian: Pembelian,

    @Column(name = "nama_pelanggan", nullable = false)
    val namaPelanggan: String,

    @Column(name = "bank", nullable = false)
    val bank: String,

    @Column(name = "total_harga", nullable = false)
    var totalHarga: Int,

    @Column(name = "tanggal_bayar", nullable = false)
    var tanggalBayar: Timestamp?,

    )
