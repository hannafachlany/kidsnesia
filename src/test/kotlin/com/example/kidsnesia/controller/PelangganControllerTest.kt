package com.example.kidsnesia.controller

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.model.RegisterRequest
import com.example.kidsnesia.model.webResponse
import com.example.kidsnesia.repository.pelangganRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.test.context.support.WithMockUser
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
class PelangganControllerTest {

 @Autowired
 private lateinit var mockMvc: MockMvc

 @Autowired
 private lateinit var pelangganRepository: pelangganRepository

 @Autowired
 private lateinit var objectMapper: ObjectMapper

 private val passwordEncoder = BCryptPasswordEncoder()

 @BeforeEach
 fun setUp() {
  pelangganRepository.deleteAll() // Hapus semua data sebelum tes dimulai

  val pelanggan = Pelanggan(
   nama_pelanggan = "test",
   password = passwordEncoder.encode("rahasia"), // 🔥 Hash password dengan benar
   email = "hana@gmail.com",
   no_hp_pelanggan = "123",
   token = null,
   tokenExpiredAt = null
  )

  pelangganRepository.save(pelanggan)
 }

 @Test
 @WithMockUser(username = "testuser", roles = ["USER"]) // Mencegah error 403 Forbidden
 fun testRegisterSuccess() {
  val request = RegisterRequest(
   nama_pelanggan = "test user",
   password = "rahasia",
   email = "newuser@gmail.com",
   no_hp_pelanggan = "08123456789"
  )

  val result = mockMvc.perform(
   post("/api/pelanggan")
    .accept(MediaType.APPLICATION_JSON)
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(request))
  )
   .andExpect(status().isOk) // Pastikan status HTTP 200 OK
   .andReturn()

  val response: webResponse<String> = objectMapper.readValue(
   result.response.contentAsString,
   object : TypeReference<webResponse<String>>() {}
  )

  assertEquals("ok", response.data)
 }

 @Test
 @WithMockUser(username = "testuser", roles = ["USER"])
 fun testRegisterDuplicate() {
  val request = RegisterRequest(
   nama_pelanggan = "test",
   password = "rahasia",
   email = "hana@gmail.com", // 🔥 Email ini sudah ada di database (duplikat)
   no_hp_pelanggan = "123"
  )

  val result = mockMvc.perform(
   post("/api/pelanggan")
    .accept(MediaType.APPLICATION_JSON)
    .contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(request))
  )
   .andExpect(status().isBadRequest) // 🔥 Seharusnya Bad Request karena email sudah dipakai
   .andReturn()

  val response: webResponse<String> = objectMapper.readValue(
   result.response.contentAsString,
   object : TypeReference<webResponse<String>>() {}
  )

  assertEquals("Email sudah dipakai", response.errors)
 }
}
