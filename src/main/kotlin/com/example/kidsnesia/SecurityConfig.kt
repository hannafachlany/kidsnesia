package com.example.kidsnesia

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.slf4j.LoggerFactory

@Configuration
class SecurityConfig {

    private val log = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        log.info("🔥 SecurityConfig telah dipanggil!")

        http
            .authorizeHttpRequests { it.anyRequest().permitAll() } // 🔥 Matikan semua autentikasi
            .csrf { it.disable() } // 🔥 Matikan CSRF
            .cors { it.disable() } // 🔥 Matikan CORS
            .formLogin { it.disable() }
            .httpBasic { it.disable() }

        return http.build()
    }
}
