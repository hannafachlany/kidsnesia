package com.example.kidsnesia.service

import com.example.kidsnesia.model.ProdukResponse
import com.example.kidsnesia.repository.ProdukRepository
import org.springframework.stereotype.Service

@Service
class ProdukService(private val produkRepository: ProdukRepository) {

    fun getAllProduk(): List<ProdukResponse> {
        val produkList = produkRepository.findAll()
        return produkList.map {
            ProdukResponse(
                idProduk = it.idProduk ?: 0,
                namaProduk = it.namaProduk,
                fotoProduk = it.fotoProduk
            )
        }
    }
}
