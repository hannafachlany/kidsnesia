package com.example.kidsnesia.controller

import com.example.kidsnesia.model.tokenResponse
import com.example.kidsnesia.model.LoginRequest
import com.example.kidsnesia.model.webResponse
import com.example.kidsnesia.service.AuthService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException // ✅ Tambahkan ini

@RestController
@RequestMapping("/api/auth") // ✅ Semua endpoint di controller ini akan diawali dengan "/api/auth"
class AuthController(private val authService: AuthService) { // ✅ Gunakan constructor injection

    @PostMapping(
        path = ["/login"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun login(@RequestBody request: LoginRequest): ResponseEntity<webResponse<tokenResponse>> {
        return try {
            val tokenResponse = authService.login(request) // ✅ Coba login
            ResponseEntity.ok(webResponse(data = tokenResponse, errors = null)) // ✅ Jika sukses
        } catch (e: ResponseStatusException) {
            ResponseEntity
                .status(e.statusCode) // 🔥 Status 401 Unauthorized
                .body(webResponse(data = null, errors = e.reason)) // 🔥 Kirim pesan error
        }
    }
}
