package com.example.kidsnesia.repository

import com.example.kidsnesia.entity.Event
import com.example.kidsnesia.entity.Pembelian
import com.example.kidsnesia.entity.Pelanggan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface PembelianRepository : JpaRepository<Pembelian, Long> {
    fun findByPelangganAndStatus(pelanggan: Pelanggan, status: String): Pembelian?
    @Query("""
        SELECT p FROM Pembelian p 
        WHERE p.pelanggan = :pelanggan 
        AND p.status = 'Pending'
        ORDER BY p.idPembelian DESC
    """)
    fun findLatestPendingByPelanggan(@Param("pelanggan") pelanggan: Pelanggan): Optional<Pembelian>
}
