package com.example.kidsnesia.service

import com.example.kidsnesia.entity.*
import com.example.kidsnesia.model.PembayaranResponse
import com.example.kidsnesia.model.DetailPembayaranResponse
import com.example.kidsnesia.repository.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.sql.Timestamp

@Service
class PembayaranService(
    private val pembayaranRepository: PembayaranRepository,
    private val pembelianRepository: PembelianRepository,
    private val pembelianEventRepository: PembelianEventRepository,
    private val eventRepository: EventRepository
) {

    // ✅ 1. GET Detail Pembayaran (Sebelum Pilih Bank)
    fun getDetailPembayaran(pelanggan: Pelanggan): PembayaranResponse {
        val pembelian = pembelianRepository.findByPelangganAndStatus(pelanggan, "Pending")
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ada pembelian yang bisa dibayar!")

        val pembelianEventList = pembelianEventRepository.findByPembelian(pembelian)
        if (pembelianEventList.isEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tidak ada tiket dalam pembelian ini!")
        }

        var totalHarga = 0
        val detailBayar = pembelianEventList.map { pembelianEvent ->
            val event = eventRepository.findById(pembelianEvent.event.idEvent)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Event tidak ditemukan!") }

            val hargaEventTotal = event.hargaEvent * pembelianEvent.jumlah
            totalHarga += hargaEventTotal

            DetailPembayaranResponse(
                namaEvent = event.namaEvent,
                jumlahTiket = pembelianEvent.jumlah,
                hargaEvent = hargaEventTotal
            )
        }

        return PembayaranResponse(
            idPembayaran = null, // Belum ada pembayaran
            idPembelian = pembelian.idPembelian,
            totalHarga = totalHarga,
            tanggalBayar = null.toString(),
            status = "Menunggu Pilihan Bank",
            detailEvent = detailBayar
        )
    }

    // ✅ 2. POST Pilih Bank
    @Transactional
    fun pilihBank(pelanggan: Pelanggan, bank: String): String {
        val pembelian = pembelianRepository.findByPelangganAndStatus(pelanggan, "Pending")
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ada pembelian yang bisa dibayar!")

        // Cek apakah pelanggan sudah memilih bank sebelumnya
        val existingPembayaran = pembayaranRepository.findByPembelian(pembelian)
        if (existingPembayaran != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Bank sudah dipilih sebelumnya!")
        }

        // Simpan bank pilihan dalam tabel Pembayaran
        val pembayaran = Pembayaran(
            pembelian = pembelian,
            bank = bank,
            totalHarga = 0, // Harga akan dihitung saat konfirmasi
            tanggalBayar = null,
            namaPelanggan = pelanggan.namaPelanggan, // Pastikan ini sesuai dengan entitas
        )

        pembayaranRepository.save(pembayaran)

        return "Bank $bank telah dipilih. Silakan konfirmasi pembayaran!"
    }


    // ✅ 3. POST Konfirmasi Pembayaran
    @Transactional
    fun konfirmasiBayar(pelanggan: Pelanggan): String {
        val pembelian = pembelianRepository.findByPelangganAndStatus(pelanggan, "Pending")
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ada pembelian yang bisa dibayar!")

        val pembayaran = pembayaranRepository.findByPembelian(pembelian)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Anda belum memilih bank!")

        val pembelianEventList = pembelianEventRepository.findByPembelian(pembelian)
        if (pembelianEventList.isEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tidak ada tiket dalam pembelian ini!")
        }

        // ✅ Perbaiki perhitungan total harga dengan menggunakan hargaTotal dari pembelian_event
        val totalHarga = pembelianEventList.sumOf { it.hargaTotal }

        pembayaran.totalHarga = totalHarga
        pembayaran.tanggalBayar = Timestamp(System.currentTimeMillis())
        pembayaranRepository.save(pembayaran)

        pembelian.status = "Lunas"
        pembelianRepository.save(pembelian)

        return "Pembayaran berhasil! Total yang dibayar: Rp $totalHarga"
    }

}
