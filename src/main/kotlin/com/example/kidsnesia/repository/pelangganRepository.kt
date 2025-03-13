package com.example.kidsnesia.repository

import com.example.kidsnesia.entity.Pelanggan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PelangganRepository : JpaRepository<Pelanggan, Int> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<Pelanggan>
    fun findFirstByToken(token: String): Optional<Pelanggan>
}
