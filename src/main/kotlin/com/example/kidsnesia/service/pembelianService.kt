package com.example.kidsnesia.service

import com.example.kidsnesia.entity.*
import com.example.kidsnesia.model.PembelianRequest
import com.example.kidsnesia.model.PembelianResponse
import com.example.kidsnesia.model.PembelianEventResponse
import com.example.kidsnesia.repository.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.sql.Timestamp

@Service
class PembelianService(
    private val eventRepository: EventRepository,
    private val pembelianRepository: PembelianRepository,
    private val pembelianEventRepository: PembelianEventRepository
) {
    @Transactional
    fun beliEvent(request: PembelianRequest, pelanggan: Pelanggan): PembelianResponse {
        val tanggalPembelian = Timestamp(System.currentTimeMillis())
        var totalHarga = 0
        val pembelianItems = mutableListOf<PembelianEventResponse>()

        val pembelian = pembelianRepository.save(
            Pembelian(
                pelanggan = pelanggan,
                tanggalPembelian = tanggalPembelian,
                totalPembelian = 0, // Total dihitung setelah semua tiket dimasukkan
                status = "Pending" // ✅ Status tetap "Pending" setelah pembelian
            )
        )

        for (item in request.items) {
            val event = eventRepository.findById(item.idEvent).orElseThrow {
                ResponseStatusException(HttpStatus.NOT_FOUND, "Event dengan ID ${item.idEvent} tidak ditemukan")
            }

            if (item.jumlah > event.kuota) {
                throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Kuota event '${event.namaEvent}' tidak mencukupi! (Tersisa: ${event.kuota})"
                )
            }

            // ✅ Kuota dikurangi saat pembelian langsung
            event.kuota -= item.jumlah
            eventRepository.save(event) // Simpan perubahan kuota

            val subTotal = item.jumlah * event.hargaEvent
            totalHarga += subTotal

            val pembelianEvent = pembelianEventRepository.save(
                PembelianEvent(
                    pembelian = pembelian,
                    event = event,
                    jumlah = item.jumlah,
                    namaEvent = event.namaEvent,
                    kategori = event.kategori,
                    hargaTotal = subTotal
                )
            )

            pembelianItems.add(
                PembelianEventResponse(
                    idPembelianEvent = pembelianEvent.idPembelianEvent ?: 0,
                    namaEvent = event.namaEvent,
                    jumlah = item.jumlah,
                    kategori = event.kategori
                )
            )
        }

        // ✅ Update total pembelian setelah semua tiket masuk
        pembelian.totalPembelian = totalHarga
        pembelianRepository.save(pembelian)

        return PembelianResponse(
            idPembelian = pembelian.idPembelian ?: 0,
            totalPembelian = pembelian.totalPembelian,
            status = pembelian.status, // ✅ Status tetap "Pending"
            tanggalPembelian = pembelian.tanggalPembelian.toString(),
            items = pembelianItems
        )
    }



//    fun getRiwayatPembelian(pelanggan: Pelanggan): List<PembelianResponse> {
//        val pembelianList = pembelianRepository.findByPelanggan(pelanggan)
//
//        return pembelianList.map { pembelian ->
//            val items = pembelianEventRepository.findByPembelian(pembelian).map { event ->
//                PembelianEventResponse(
//                    idPembelianEvent = event.idPembelianEvent ?: 0,
//                    namaEvent = event.namaEvent,
//                    jumlah = event.jumlah,
//                    kategori = event.kategori
//                )
//            }
//
//            PembelianResponse(
//                idPembelian = pembelian.idPembelian ?: 0,
//                totalPembelian = pembelian.totalPembelian,
//                status = pembelian.status,
//                tanggalPembelian = pembelian.tanggalPembelian.toString(),
//                items = items
//            )
//        }
//    }
}
