package com.example.kidsnesia.service

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.NotaPembelianResponse
import com.example.kidsnesia.model.DetailPembayaranResponse
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
        // Cari pembelian berdasarkan id dan pastikan milik pelanggan yang login
        val pembelian = pembelianRepository.findById(idPembelian)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Pembelian tidak ditemukan!") }

        if (pembelian.pelanggan.idPelanggan != pelanggan.idPelanggan) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Anda tidak bisa mengakses nota orang lain!")
        }

        // Cari pembayaran berdasarkan pembelian
        val pembayaran = pembayaranRepository.findByPembelian(pembelian)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Pembayaran tidak ditemukan!")

        // Ambil daftar event dalam pembelian
        val pembelianEventList = pembelianEventRepository.findByPembelian(pembelian)

        if (pembelianEventList.isEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tidak ada event dalam nota ini!")
        }

        // Buat list detail event
        val detailEvent = pembelianEventList.map { event ->
            DetailPembayaranResponse(
                namaEvent = event.namaEvent,
                jumlahTiket = event.jumlah,
                hargaEvent = event.hargaTotal // Harga event yang sudah dikali jumlah tiket
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
            detailEvent = detailEvent
        )
    }
}
