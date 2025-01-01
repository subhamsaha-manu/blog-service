package com.blog.blog_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        println("CorsFilter")
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOrigins = listOf("https://localhost:3002") // Allow only this origin
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific methods
        config.allowedHeaders = listOf("*") // Allow all headers

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config) // Apply to all endpoints
        return CorsFilter(source)
    }
}

