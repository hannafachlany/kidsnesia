package com.example.kidsnesia.service

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.NotaPembelianResponse
import com.example.kidsnesia.model.EventYangDibeli
import com.example.kidsnesia.repository.PembayaranRepository
import com.example.kidsnesia.repository.PembelianRepository
import com.example.kidsnesia.repository.PembelianEventRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.format.DateTimeFormatter

@Service
class NotaPembelianService(
    private val pembayaranRepository: PembayaranRepository,
    private val pembelianRepository: PembelianRepository,
    private val pembelianEventRepository: PembelianEventRepository
) {
    fun getNotaPembelian(pelanggan: Pelanggan, idPembelian: Long): NotaPembelianResponse {
        val pembelian = pembelianRepository.findById(idPembelian)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Pembelian tidak ditemukan!") }

        if (pembelian.pelanggan.idPelanggan != pelanggan.idPelanggan) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Anda tidak bisa mengakses nota orang lain!")
        }

        val pembayaran = pembayaranRepository.findByPembelian(pembelian)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Pembayaran tidak ditemukan!")

        val pembelianEventList = pembelianEventRepository.findByPembelian(pembelian)

        if (pembelianEventList.isEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tidak ada event dalam nota ini!")
        }

        val detailEvent = pembelianEventList.map { eventPembelian ->
            val event = eventPembelian.event // Ambil data event dari relasi
            EventYangDibeli(
                namaEvent = event.namaEvent,
                jumlahTiket = eventPembelian.jumlah,
                hargaEvent = eventPembelian.hargaTotal,
                jadwalEvent = "${event.jadwalEventDay}, ${event.jadwalEvent.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}"
            )
        }

        return NotaPembelianResponse(
            idPembayaran = pembayaran.idPembayaran ?: 0,
            idPembelian = pembayaran.pembelian.idPembelian ?: 0,
            bank = pembayaran.bank,
            totalHarga = pembayaran.totalHarga,
            tanggalBayar = pembayaran.tanggalBayar?.let {
                it.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
            } ?: "Belum dibayar",
            namaPelanggan = pelanggan.namaPelanggan,
            email = pelanggan.email,
            noHpPelanggan = pelanggan.noHpPelanggan, // Sesuai model
            tanggalPembelian = pembelian.tanggalPembelian.toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")), // Sesuai model
            detailEvent = detailEvent
        )
    }
}

