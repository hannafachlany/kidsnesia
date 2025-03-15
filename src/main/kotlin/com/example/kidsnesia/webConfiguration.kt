package com.example.kidsnesia

import com.example.kidsnesia.resolver.PelangganArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration // ✅ Tambahkan anotasi ini
class WebConfiguration(
    private val pelangganArgumentResolver: PelangganArgumentResolver
) : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(pelangganArgumentResolver) // ✅ Tambahkan resolver
    }
}
