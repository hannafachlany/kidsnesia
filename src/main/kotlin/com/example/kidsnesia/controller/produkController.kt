package com.example.kidsnesia.controller

import com.example.kidsnesia.model.ProdukResponse
import com.example.kidsnesia.model.ProdukListWrapper
import com.example.kidsnesia.service.ProdukService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/produk")
class ProdukController(private val produkService: ProdukService) {

    @GetMapping
    fun getAllProduk(): ProdukListWrapper {
        val listProduk = produkService.getAllProduk()
        return ProdukListWrapper(
            error = false,
            message = "Data produk berhasil diambil",
            listProduk = listProduk
        )
    }
}
