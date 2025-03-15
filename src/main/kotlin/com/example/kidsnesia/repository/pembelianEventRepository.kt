package com.example.kidsnesia.repository

import com.example.kidsnesia.entity.Pembelian
import com.example.kidsnesia.entity.PembelianEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PembelianEventRepository : JpaRepository<PembelianEvent, Long> {
    fun findByPembelian(pembelian: Pembelian): List<PembelianEvent>
    @Query("SELECT pe FROM PembelianEvent pe JOIN pe.pembelian p WHERE p.pelanggan.idPelanggan = :pelangganId")
    fun findByPelanggan(@Param("pelangganId") pelangganId: Long): List<PembelianEvent>
    @Query("SELECT pe FROM PembelianEvent pe WHERE pe.pembelian = :pembelian AND pe.event.idEvent = :eventId")
    fun findByPembelianAndEvent(@Param("pembelian") pembelian: Pembelian, @Param("eventId") eventId: Long): PembelianEvent?

}
