package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.PembelianEventListWrapper
import com.example.kidsnesia.service.PembelianEventService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pembelian-event")
class PembelianEventController(
    private val pembelianEventService: PembelianEventService
) {

    @GetMapping
    fun getListEventDiikuti(pelanggan: Pelanggan): PembelianEventListWrapper {
        val detailBeliEvent = pembelianEventService.getListEventDiikuti(pelanggan.idPelanggan!!)
        return PembelianEventListWrapper(
            error = false,
            detailBeliEvent = detailBeliEvent
        )
    }
}
