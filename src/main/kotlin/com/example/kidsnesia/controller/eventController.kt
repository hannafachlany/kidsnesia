package com.example.kidsnesia.controller

import com.example.kidsnesia.model.*
import com.example.kidsnesia.service.EventService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/event") //semua api disini dimulai dengan /api/event
class EventController(private val eventService: EventService) {

    private val logger = LoggerFactory.getLogger(EventController::class.java) //Tambahkan logger biar ada pesan di log

    // 🔥 GET Semua Event
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllEvents(): EventListWrapper {
        logger.info("📩 Request GET semua event diterima")

        val events = eventService.findAllEvents()
        logger.info("✅ Ditemukan ${events.size} event")

        return EventListWrapper(
            error = false,
            message = "Daftar event berhasil diambil",
            listEvent = events
        )
    }

    // 🔥 GET Detail Event
    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEventById(@PathVariable id: Long): EventDetailWrapper {
        logger.info("📩 Request GET detail event dengan ID: {}", id)

        val event = eventService.getEventDetail(id)
        logger.info("✅ Detail event ditemukan: {}", event.namaEvent)

        return EventDetailWrapper(
            error = false,
            message = "Detail event berhasil diambil",
            detailEvent = event
        )
    }

}
