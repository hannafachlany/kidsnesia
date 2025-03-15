package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.NotaPembelianResponse
import com.example.kidsnesia.model.NotaWrapper
import com.example.kidsnesia.service.NotaPembelianService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/nota-pembelian")
class NotaPembelianController(
    private val notaPembelianService: NotaPembelianService
) {
    @GetMapping("/{idPembelian}")
    fun getNotaPembelian(
        @PathVariable idPembelian: Long,
        pelanggan: Pelanggan // Ambil pelanggan dari token
    ): NotaWrapper {
        val notaPembelian = notaPembelianService.getNotaPembelian(pelanggan, idPembelian)
        return NotaWrapper(
            error = false,
            nota = notaPembelian
        )
    }
}
