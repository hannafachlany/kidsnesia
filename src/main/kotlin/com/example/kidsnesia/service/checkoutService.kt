package com.example.kidsnesia.service

import com.example.kidsnesia.entity.*
import com.example.kidsnesia.model.CheckoutResponse
import com.example.kidsnesia.model.CheckoutEventResponse
import com.example.kidsnesia.model.CheckoutRequest
import com.example.kidsnesia.model.UpdateJumlahTiketRequest
import com.example.kidsnesia.repository.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class CheckoutService(
    private val eventRepository: EventRepository,
    private val pembelianRepository: PembelianRepository,
    private val pembelianEventRepository: PembelianEventRepository
) {
    @Transactional
    fun checkout(pelanggan: Pelanggan, request: CheckoutRequest): CheckoutResponse {
        val pembelian = pembelianRepository.findById(request.idPembelian)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Pembelian tidak ditemukan!") }

        if (pembelian.pelanggan.idPelanggan != pelanggan.idPelanggan) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Anda tidak bisa checkout pembelian orang lain!")
        }

        // 🔥 Ambil daftar event dalam pembelian
        val pembelianEvents = pembelianEventRepository.findByPembelian(pembelian)
        if (pembelianEvents.isEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tidak ada tiket dalam pembelian ini!")
        }

        val checkoutItems = pembelianEvents.map { pembelianEvent ->
            val event = pembelianEvent.event

            // ✅ Cek apakah kuota cukup sebelum checkout
            if (pembelianEvent.jumlah > event.kuota) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Kuota event '${event.namaEvent}' tidak mencukupi.")
            }

            // 🔥 Kurangi kuota event
            event.kuota -= pembelianEvent.jumlah
            eventRepository.save(event)

            CheckoutEventResponse(
                idPembelianEvent = pembelianEvent.idPembelianEvent ?: 0,
                namaEvent = event.namaEvent,
                jumlah = pembelianEvent.jumlah,
                kategori = event.kategori
            )
        }

        return CheckoutResponse(
            idPembelian = pembelian.idPembelian ?: 0,
            totalPembelian = pembelian.totalPembelian,
            status = "Pending",
            tanggalPembelian = pembelian.tanggalPembelian.toString(),
            items = checkoutItems
        )
    }


    @Transactional
    fun updateJumlahTiket(pelanggan: Pelanggan, request: UpdateJumlahTiketRequest): String {
        val pembelian = pembelianRepository.findById(request.idPembelian)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Pembelian tidak ditemukan!") }

        if (pembelian.pelanggan.idPelanggan != pelanggan.idPelanggan) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Anda tidak bisa mengupdate tiket orang lain!")
        }

        for (item in request.items) {
            val pembelianEvent = pembelianEventRepository.findById(item.idPembelianEvent)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Tiket tidak ditemukan!") }

            if (pembelianEvent.pembelian.idPembelian != pembelian.idPembelian) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tiket tidak terkait dengan pembelian ini!")
            }

            // Ambil data event
            val event = eventRepository.findById(pembelianEvent.event.idEvent)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Event tidak ditemukan!") }


            // ✅ Cek apakah jumlah tiket yang diminta lebih besar dari kuota baru yang tersedia
            if (item.jumlahBaru > event.kuota) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Kuota event '${event.namaEvent}' tidak mencukupi.")
            }

            // ✅ Update jumlah tiket di pembelian_event
            pembelianEvent.jumlah = item.jumlahBaru
            pembelianEventRepository.save(pembelianEvent)
            eventRepository.save(event) // 🔥 **Simpan update kuota event**
        }

        return "Jumlah tiket berhasil diperbarui!"
    }

}
