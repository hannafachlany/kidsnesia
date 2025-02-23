package com.example.kidsnesia.service

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.LoginRequest
import com.example.kidsnesia.model.tokenResponse
import com.example.kidsnesia.repository.pelangganRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class AuthService @Autowired constructor(
    private val pelangganRepository: pelangganRepository, // ✅ Gunakan `PelangganRepository`
    private val validationService: ValidationService
) {

    @Transactional
    fun login(request: LoginRequest): tokenResponse {
        // 🔥 Gunakan ValidationService untuk validasi request
        validationService.validate(request)

        // 🔥 Ambil data pelanggan berdasarkan email (gunakan findByEmail, bukan findById!)
        val pelanggan = pelangganRepository.findByEmail(request.email)
            .orElseThrow { ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah") }

        // 🔥 Cek password dengan BCrypt
        if (BCrypt.checkpw(request.password, pelanggan.password)) {
            // 🔥 Jika berhasil login, buat token baru & simpan ke database
            pelanggan.token = UUID.randomUUID().toString()
            pelanggan.tokenExpiredAt = next30Days() // ✅ Pastikan tokenExpiredAt tetap `Long`

            pelangganRepository.save(pelanggan) // Simpan update token pelanggan

            // 🔥 Return response token ke client
            return tokenResponse(
                token = pelanggan.token!!,
                tokenExpiredAt = pelanggan.tokenExpiredAt!!.toString() // ✅ Konversi Long ke String saat return
            )
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah")
        }
    }

    private fun next30Days(): Long {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30) // ✅ 30 Hari ke depan dalam `Long`
    }
}
