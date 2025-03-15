package com.example.kidsnesia.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "pembelian_event")
data class PembelianEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pembelian_event")
    val idPembelianEvent: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_pembelian", nullable = false)
    val pembelian: Pembelian,

    @ManyToOne
    @JoinColumn(name = "id_event", nullable = false)
    val event: Event,

    @Column(name = "jumlah", nullable = false)
    var jumlah: Int,

    @Column(name = "nama_event", nullable = false)
    val namaEvent: String,

    @Column(name = "kategori", nullable = false)
    val kategori: String,

    @Column(name = "harga_total", nullable = false)
    var hargaTotal: Int
)
