package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.*
import com.example.kidsnesia.service.CheckoutService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/api/checkout")
class CheckoutController(private val checkoutService: CheckoutService) {

    private val logger: Logger = LoggerFactory.getLogger(CheckoutController::class.java)
    // ✅ API Checkout (POST)
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun checkout(
        @RequestBody request: CheckoutRequest,
        pelanggan: Pelanggan
    ): CheckoutWrapper {
        val checkoutWrapper = checkoutService.checkout(pelanggan, request)
        logger.info("✅ Checkout Berhasil")
        return CheckoutWrapper(
            error = false,
            checkout = checkoutWrapper
        )

    }

    // ✅ API PATCH untuk Update Jumlah Tiket di Cart (dengan URL yang sama)
    @PatchMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateJumlahTiket(
        @RequestBody request: UpdateJumlahTiketRequest,
        pelanggan: Pelanggan
    ): ResponseEntity<WebResponse<String>> {
        val response = checkoutService.updateJumlahTiket(pelanggan, request)
        return ResponseEntity.ok(WebResponse(message = response, status = "sukses"))
    }
}
