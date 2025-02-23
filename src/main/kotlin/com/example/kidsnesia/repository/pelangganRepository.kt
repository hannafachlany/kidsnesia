package com.example.kidsnesia.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.example.kidsnesia.entity.Pelanggan
import java.util.*

@Repository
interface pelangganRepository : JpaRepository<Pelanggan, Int> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<Pelanggan> // 🔥 Tambahkan ini!

}