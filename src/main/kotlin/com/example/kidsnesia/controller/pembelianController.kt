package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.PembelianRequest
import com.example.kidsnesia.model.PembelianResponse
import com.example.kidsnesia.model.PembelianWrapper
import com.example.kidsnesia.model.WebResponse
import com.example.kidsnesia.service.PembelianService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/pembelian")
class PembelianController(private val pembelianService: PembelianService) {

    //menerima input beli dari user sesuai dengan request model yang telah ditentukan, di poin ini POST
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun beliEvent(@RequestBody request: PembelianRequest, pelanggan: Pelanggan): PembelianWrapper {
        val pembelian = pembelianService.beliEvent(request, pelanggan)
        return PembelianWrapper(
            error = false,
            pembelianResponse = pembelian
        )

    }
//    //GET (mengambil) data dari database sesuai dengan response model
//    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun getRiwayatPembelian(pelanggan: Pelanggan): ResponseEntity<WebResponse<List<PembelianResponse>>> {
//        val response = pembelianService.getRiwayatPembelian(pelanggan)
//        return ResponseEntity.ok(WebResponse(message = response, status = "sukses"))
//    }
}
