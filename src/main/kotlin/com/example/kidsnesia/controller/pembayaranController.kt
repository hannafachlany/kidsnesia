package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.DetailBayarWrapper
import com.example.kidsnesia.model.PembayaranRequest
import com.example.kidsnesia.model.WebResponse
import com.example.kidsnesia.service.PembayaranService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pembayaran")
class PembayaranController(private val pembayaranService: PembayaranService) {

    // ✅ 1. GET Detail Pembayaran (Sebelum Pilih Bank)
    @GetMapping("/detail")
    fun getDetailPembayaran(pelanggan: Pelanggan): DetailBayarWrapper {
        val detailBayar = pembayaranService.getDetailPembayaran(pelanggan)
        return DetailBayarWrapper(
            error = false,
            detailBayar = detailBayar
        )
    }

    // ✅ 2. POST Pilih Bank
    @PostMapping("/pilih-bank")
    fun pilihBank(@RequestBody request: PembayaranRequest, pelanggan: Pelanggan): ResponseEntity<WebResponse<String>> {
        val response = pembayaranService.pilihBank(pelanggan, request.bank)
        return ResponseEntity.ok(WebResponse(message = response, status = "Sukses"))
    }

    // ✅ 3. POST Konfirmasi Pembayaran
    @PostMapping("/konfirmasi-bayar")
    fun konfirmasiBayar(pelanggan: Pelanggan): ResponseEntity<WebResponse<String>> {
        val response = pembayaranService.konfirmasiBayar(pelanggan)
        return ResponseEntity.ok(WebResponse(message = response, status = "Sukses"))
    }
}
