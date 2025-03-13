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

    // Endpoint login untuk User
    @PostMapping(
        path = ["/login"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun login(@RequestBody request: LoginRequest): ResponseEntity<WebResponse<TokenResponse>> {
        return try {
            val tokenResponse = authService.login(request)
            logger.info("📝 Login pelanggan: ${request.email}")

            ResponseEntity.ok(WebResponse(message = tokenResponse, status = "sukses"))
        } catch (e: ResponseStatusException) {
            logger.error("❌ Login gagal: ${e.reason}")

            ResponseEntity
                .status(e.statusCode)
                .body(WebResponse(message = null, status = e.reason))
        }
    }
}
