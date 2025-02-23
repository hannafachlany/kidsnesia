package com.example.kidsnesia.service

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.RegisterRequest
import com.example.kidsnesia.repository.pelangganRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PelangganService @Autowired constructor(
    private val pelangganRepository: pelangganRepository,
    private val validationService: ValidationService // Gunakan ValidationService
) {

    private val passwordEncoder = BCryptPasswordEncoder() // Instansiasi BCrypt

    fun register(request: RegisterRequest) {
        validationService.validate(request) // 🔥 Gunakan ValidationService untuk validasi

        if (pelangganRepository.existsByEmail(request.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email sudah dipakai")
        }

        val hashedPassword = passwordEncoder.encode(request.password) // Hash password

        val pelanggan = Pelanggan(
            email = request.email,
            password = hashedPassword, // Simpan password yang sudah di-hash
            nama_pelanggan = request.nama_pelanggan,
            no_hp_pelanggan = request.no_hp_pelanggan,
            token = null,
            tokenExpiredAt = null
        )

        pelangganRepository.save(pelanggan) // Simpan ke database
    }
}
