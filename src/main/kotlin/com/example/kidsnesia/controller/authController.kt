package com.example.kidsnesia.controller

import com.example.kidsnesia.model.TokenResponse
import com.example.kidsnesia.model.LoginRequest
import com.example.kidsnesia.model.WebResponse
import com.example.kidsnesia.service.AuthService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/login", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Map<String, Any>> {
        return try {
            val tokenResponse = authService.login(request)
            logger.info("📝 Login pelanggan: ${request.email}")

            val response = mapOf(
                "error" to false,
                "message" to "success",
                "loginResult" to mapOf(
                    "email" to tokenResponse.email,
                    "namaPelanggan" to tokenResponse.namaPelanggan, // Gunakan `namaPelanggan`
                    "token" to tokenResponse.token
                )
            )

            ResponseEntity.ok(response)
        } catch (e: ResponseStatusException) {
            logger.error("❌ Login gagal: ${e.reason}")

            val errorResponse = mapOf(
                "error" to true,
                "message" to e.reason
            )

            ResponseEntity.status(e.statusCode).body(errorResponse)
        }
    }
}

