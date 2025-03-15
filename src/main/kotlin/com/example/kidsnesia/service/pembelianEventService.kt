package com.example.kidsnesia.service

import com.example.kidsnesia.model.PembelianEventListResponse
import com.example.kidsnesia.repository.PembelianEventRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PembelianEventService(
    private val pembelianEventRepository: PembelianEventRepository
) {

    fun getListEventDiikuti(pelangganId: Long): List<PembelianEventListResponse> {
        val pembelianEvents = pembelianEventRepository.findByPelanggan(pelangganId)

        if (pembelianEvents.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ada event yang pernah dibeli")
        }

        return pembelianEvents.map { event ->
            PembelianEventListResponse(
                idPembelian = event.pembelian.idPembelian ?: 0,
                idEvent = event.event.idEvent ?: 0,
                namaEvent = event.namaEvent,
                kategori = event.kategori,
                jumlah = event.jumlah,
                hargaTotal = event.hargaTotal
            )
        }
    }

}
