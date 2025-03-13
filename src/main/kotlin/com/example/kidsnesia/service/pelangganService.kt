package com.example.kidsnesia.service

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.PelangganResponse
import com.example.kidsnesia.model.RegisterRequest
import com.example.kidsnesia.repository.PelangganRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Service
class PelangganService(
    private val pelangganRepository: PelangganRepository,
    private val validationService: ValidationService
) {
    private val passwordEncoder = BCryptPasswordEncoder()
    private val logger: Logger = LoggerFactory.getLogger(PelangganService::class.java)

    fun register(registerData: RegisterRequest) {
        validationService.validate(registerData)
        // kalo email sudah dipake
        if (pelangganRepository.existsByEmail(registerData.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email sudah dipakai")
        }

        // encrypt password(?)
        val hashedPassword = passwordEncoder.encode(registerData.password)

        // nyimpen data user yang register ke tabel pelanggan
        val pelanggan = Pelanggan(
            email = registerData.email,
            password = hashedPassword,
            namaPelanggan = registerData.namaPelanggan,
            noHpPelanggan = registerData.noHpPelanggan,
            token = null,
            tokenExpiredAt = null
        )

        pelangganRepository.save(pelanggan)
    }

    // function response ketika akses api dengan GET
    fun getPelangganResponse(pelanggan: Pelanggan): PelangganResponse {
        logger.info("🔄 Membuat response untuk pelanggan: ID=${pelanggan.idPelanggan}, " +
                "Nama=${pelanggan.namaPelanggan}, Email=${pelanggan.email}, No HP=${pelanggan.noHpPelanggan}")


        return PelangganResponse(
            email = pelanggan.email ?: "Tidak ada email",
            namaPelanggan = pelanggan.namaPelanggan ?: "Tidak ada nama",
            noHpPelanggan = pelanggan.noHpPelanggan ?: "Tidak ada nomor HP"
        )
    }

}
