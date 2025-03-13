package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.PelangganResponse
import com.example.kidsnesia.model.WebResponse
import com.example.kidsnesia.service.PelangganService
import org.slf4j.LoggerFactory
import com.example.kidsnesia.model.RegisterRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//untuk user register

@RestController
@RequestMapping("/api/pelanggan")
class PelangganController(private val pelangganService: PelangganService) {

    private val logger = LoggerFactory.getLogger(PelangganController::class.java)

    // ✅ Endpoint Register
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<WebResponse<String>> {
        logger.info("📝 Register pelanggan: ${registerRequest.email}")

        pelangganService.register(registerRequest)

        return ResponseEntity.ok(WebResponse(message = "Registrasi berhasil", status = "sukses"))
    }

    // ✅ Endpoint untuk mendapatkan pelanggan yang login
    @GetMapping("/current", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCurrentPelanggan(pelanggan: Pelanggan): ResponseEntity<WebResponse<PelangganResponse>> {
        logger.info("🎯 Mengambil data pelanggan yang login: ${pelanggan.email}")

        val pelangganResponse = pelangganService.getPelangganResponse(pelanggan)

        return ResponseEntity.ok(WebResponse(message = pelangganResponse, status = "sukses"))
    }
}

