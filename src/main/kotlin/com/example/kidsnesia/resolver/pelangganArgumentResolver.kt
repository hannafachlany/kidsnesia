package com.example.kidsnesia.resolver

import com.example.kidsnesia.entity.Pelanggan
import com.example.kidsnesia.repository.PelangganRepository
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.server.ResponseStatusException

@Component
class PelangganArgumentResolver(
    private val pelangganRepository: PelangganRepository
) : HandlerMethodArgumentResolver {

    private val logger = LoggerFactory.getLogger(PelangganArgumentResolver::class.java)

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == Pelanggan::class.java //Pastikan hanya memproses parameter bertipe Pelanggan
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val request = webRequest.nativeRequest as HttpServletRequest
        val token = request.getHeader("X-API-TOKEN")
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Belum Login")

        logger.info("🔍 Mencari pelanggan dengan token: $token")

        val pelangganOptional = pelangganRepository.findFirstByToken(token)

        if (pelangganOptional.isEmpty) {
            logger.warn("🚨 Token tidak valid")
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token tidak valid")
        }

        val pelanggan = pelangganOptional.get()

        logger.info("✅ Pelanggan ditemukan: ID=${pelanggan.idPelanggan}, Email=${pelanggan.email}")

        return pelanggan
    }
}
