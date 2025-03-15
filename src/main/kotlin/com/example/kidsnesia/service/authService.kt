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
    private val pelangganRepository: PelangganRepository,
    private val validationService: ValidationService
) {

    @Transactional
    fun login(request: LoginRequest): TokenResponse {
        validationService.validate(request)

        val pelanggan = pelangganRepository.findByEmail(request.email)
            .orElseThrow { ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah") }

        if (BCrypt.checkpw(request.password, pelanggan.password)) {
            pelanggan.token = UUID.randomUUID().toString()
            pelanggan.tokenExpiredAt = next30Days()

            pelangganRepository.save(pelanggan)

            return TokenResponse(
                email = pelanggan.email,
                namaPelanggan = pelanggan.namaPelanggan, // Ubah nama field menjadi `namaPelanggan`
                token = pelanggan.token!!
            )
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah")
        }
    }

    private fun next30Days(): Long {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30)
    }
}

