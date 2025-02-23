package com.example.kidsnesia.controller

import com.example.kidsnesia.service.PelangganService
import com.example.kidsnesia.model.RegisterRequest
import com.example.kidsnesia.model.webResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pelanggan") // Prefix untuk semua endpoint
class PelangganController(private val pelangganService: PelangganService) {

    private val logger = LoggerFactory.getLogger(PelangganController::class.java) // 🔥 Tambahkan logger

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun register(@RequestBody request: RegisterRequest): webResponse<String> {
        logger.info("📩 Request pendaftaran diterima: {}", request) // 🔥 Log request masuk

        pelangganService.register(request)

        logger.info("✅ Pendaftaran sukses: {}", request.email) // 🔥 Log sukses

        return webResponse(data = "ok", errors = null)
    }
}
