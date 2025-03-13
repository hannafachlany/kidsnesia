package com.example.kidsnesia.service

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.LoginRequest
import com.example.kidsnesia.model.TokenResponse
import com.example.kidsnesia.repository.PelangganRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.web.server.ResponseStatusException
import java.util.*

//logika pengolahan data untuk login
@Service
class AuthService @Autowired constructor(
    private val PelangganRepository: PelangganRepository, // ✅ Gunakan `PelangganRepository`
    private val validationService: ValidationService
) {

    @Transactional
    fun login(request: LoginRequest): TokenResponse {
        validationService.validate(request)

        // 🔥 Ambil data pelanggan berdasarkan email (gunakan findByEmail di repo)
        val pelanggan = PelangganRepository.findByEmail(request.email)
            .orElseThrow { ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah") }

        // 🔥 Cek password dengan BCrypt
        if (BCrypt.checkpw(request.password, pelanggan.password)) {
            // 🔥 Jika berhasil login, buat token baru & simpan ke database
            pelanggan.token = UUID.randomUUID().toString()
            pelanggan.tokenExpiredAt = next30Days() // ✅ Pastikan tokenExpiredAt tetap `Long`

            PelangganRepository.save(pelanggan) // Simpan update token pelanggan

            // 🔥 Return response token ke client
            return TokenResponse(
                token = pelanggan.token!!,
                tokenExpiredAt = pelanggan.tokenExpiredAt!!.toString() // ✅ Konversi Long ke String saat return
            )
        } else {
            //jika password/email salah dimasukkan
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah")
        }
    }

    //pembuatan tokenExpiredAt
    private fun next30Days(): Long {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30) // ✅ 30 Hari ke depan dalam `Long`
    }
}
