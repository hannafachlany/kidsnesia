package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.LoginRequest
import com.example.kidsnesia.model.tokenResponse
import com.example.kidsnesia.model.webResponse
import com.example.kidsnesia.repository.pelangganRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertNotNull

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

 @Autowired
 private lateinit var mockMvc: MockMvc

 @Autowired
 private lateinit var pelangganRepository: pelangganRepository

 @Autowired
 private lateinit var objectMapper: ObjectMapper

 private val passwordEncoder = BCryptPasswordEncoder() // ✅ Gunakan BCrypt

 @BeforeEach
 fun setUp() {
  pelangganRepository.deleteAll() // Hapus semua data sebelum tes dimulai

  // 🔥 Simpan pelanggan uji coba ke database
  val pelanggan = Pelanggan(
   email = "hana@gmail.com",
   password = passwordEncoder.encode("1234"), // 🔥 Hash password sebelum disimpan
   nama_pelanggan = "Hana",
   no_hp_pelanggan = "08123456789",
   token = null,
   tokenExpiredAt = null
  )
  pelangganRepository.save(pelanggan)
 }

 @Test
 fun loginFailedUserNotFound() {
  val request = LoginRequest(
   email = "hana@gmail.com",
   password = "salahpassword" // 🔥 Password salah untuk uji coba gagal login
  )

  val result = mockMvc.perform(
   post("/api/auth/login")
    .accept(MediaType.APPLICATION_JSON)
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(request))
  )
   .andExpect(status().isUnauthorized) // 🔥 Pastikan statusnya 401 Unauthorized
   .andReturn()

  val response: webResponse<String> = objectMapper.readValue(
   result.response.contentAsString,
   object : TypeReference<webResponse<String>>() {}
  )

  // ✅ Tambahkan log supaya bisa dilihat di console
  println("🔥 Login Failed Test - Status: ${result.response.status}")
  println("🔥 Login Failed Test - Response Body: ${result.response.contentAsString}")

  assertNotNull(response.errors) // ✅ Pastikan response errors tidak null
 }

 @Test
 fun loginFailedWrongPassword() {


  val request = LoginRequest(
   email = "hana@gmail.com",
   password = "salahpassword" // 🔥 Password salah untuk uji coba gagal login
  )

  val result = mockMvc.perform(
   post("/api/auth/login")
    .accept(MediaType.APPLICATION_JSON)
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(request))
  )
   .andExpect(status().isUnauthorized) // 🔥 Pastikan statusnya 401 Unauthorized
   .andReturn()

  val response: webResponse<String> = objectMapper.readValue(
   result.response.contentAsString,
   object : TypeReference<webResponse<String>>() {}
  )

  // ✅ Tambahkan log supaya bisa dilihat di console
  println("🔥 Login Failed Test - Status: ${result.response.status}")
  println("🔥 Login Failed Test - Response Body: ${result.response.contentAsString}")

  assertEquals(HttpStatus.UNAUTHORIZED.value(), result.response.status) // Pastikan statusnya 401
  assertNotNull(response.errors) // ✅ Pastikan response errors tidak null
 }

// @Test
// fun loginSuccess() {
//  // 🔥 Simpan pelanggan uji coba dengan password yang di-hash
//  val pelanggan = Pelanggan(
//   nama_pelanggan = "Test",
//   email = "test@gmail.com",
//   password = passwordEncoder.encode("test"), // ✅ Hash password
//   no_hp_pelanggan = "08123456789",
//   token = null,
//   tokenExpiredAt = null
//  )
//  pelangganRepository.save(pelanggan)
//
//  val request = LoginRequest(
//   email = "hana@gmail.com",
//   password = "1234"
//  )
//
//  mockMvc.perform(
//   post("/api/auth/login")
//    .accept(MediaType.APPLICATION_JSON)
//    .contentType(MediaType.APPLICATION_JSON)
//    .content(objectMapper.writeValueAsString(request))
//  ).andExpectAll(
//   status().isOk
//  ).andDo { result ->
//   val response: webResponse<tokenResponse> = objectMapper.readValue(
//    result.response.contentAsString,
//    object : TypeReference<webResponse<tokenResponse>>() {}
//   )
//
//   // ✅ Pastikan tidak ada error dalam response
//   assertNull(response.errors)
//
//   // ✅ Pastikan token dan expiredAt tidak null
//   assertNotNull(response.data?.token, "Token tidak boleh null")
//   assertNotNull(response.data?.tokenExpiredAt, "Token expiredAt tidak boleh null")
//
//   // 🔥 Ambil data dari database setelah login
//   val pelangganDb = pelangganRepository.findByEmail("test@gmail.com")
//    ?: throw AssertionError("Pelanggan tidak ditemukan di database")
//
//   // ✅ Pastikan token di database sesuai dengan yang dikembalikan oleh API
//   assertEquals(pelanggan.token, response.data?.token)
//   assertEquals(pelanggan.tokenExpiredAt, response.data?.tokenExpiredAt)
//  }
// }


}
