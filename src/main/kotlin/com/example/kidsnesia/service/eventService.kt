package com.example.kidsnesia.service

import com.example.kidsnesia.entity.Event
import com.example.kidsnesia.model.EventDetailResponse
import com.example.kidsnesia.model.EventListResponse
import com.example.kidsnesia.repository.EventRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EventService(private val eventRepository: EventRepository) {

    // 🔥 Ambil semua event
    fun findAllEvents(): List<EventListResponse> {
        return eventRepository.findAll().map { it.toListResponse() }
    }

    // 🔥 Ambil detail event
    fun getEventDetail(id: Long): EventDetailResponse {
        val event = eventRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Event tidak ditemukan")
        }
        return event.toDetailResponse()
    }

    // 🔥 Konversi `Event` ke `EventListResponse`
    private fun Event.toListResponse(): EventListResponse {
        return EventListResponse(
            idEvent = this.idEvent ?: 0L,
            namaEvent = this.namaEvent,
            hargaEvent = this.hargaEvent,
            jadwalEvent = this.jadwalEvent.toString(),
            jadwalEventDay = this.jadwalEventDay,
            fotoEvent = this.fotoEvent,
            deskripsiEvent = this.deskripsiEvent,
            kuota = this.kuota,
            kategori = this.kategori
        )
    }

    // 🔥 Konversi `Event` ke `EventDetailResponse`
    private fun Event.toDetailResponse(): EventDetailResponse {
        return EventDetailResponse(
            idEvent = this.idEvent ?: 0L,
            namaEvent = this.namaEvent,
            hargaEvent = this.hargaEvent,
            jadwalEvent = this.jadwalEvent.toString(),
            jadwalEventDay = this.jadwalEventDay,
            fotoEvent = this.fotoEvent,
            deskripsiEvent = this.deskripsiEvent,
            kuota = this.kuota,
            kategori = this.kategori
        )
    }
}
