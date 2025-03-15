package com.example.kidsnesia.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.example.kidsnesia.entity.Event

// repo mengambil data dari database
@Repository
interface EventRepository : JpaRepository<Event, Long> {
    fun findByNamaEvent(namaEvent: String): List<Event>
}
