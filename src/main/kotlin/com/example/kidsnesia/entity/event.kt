package com.example.kidsnesia.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "event")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    val idEvent: Long? = null, // ID auto-increment

    @Column(name = "nama_event", nullable = false)
    val namaEvent: String,

    @Column(name = "harga_event", nullable = false)
    val hargaEvent: Int,

    @Column(name = "jadwal_event", nullable = false)
    val jadwalEvent: Timestamp,

    @Column(name = "jadwal_event_day", nullable = false)
    val jadwalEventDay: String,

    @Column(name = "foto_event", nullable = false)
    val fotoEvent: String,

    @Column(name = "deskripsi_event", nullable = false)
    val deskripsiEvent: String,

    @Column(name = "kuota", nullable = false)
    var kuota: Int,

    @Column(name = "kategori", nullable = false)
    var kategori: String
)
