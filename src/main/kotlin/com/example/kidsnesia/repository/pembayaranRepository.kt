package com.example.kidsnesia.repository

import com.example.kidsnesia.entity.Pembayaran
import com.example.kidsnesia.entity.Pembelian
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PembayaranRepository : JpaRepository<Pembayaran, Long> {
    fun findByPembelian(pembelian: Pembelian): Pembayaran?
}
