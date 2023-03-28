package com.project.dashboard


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
class DashboardApplication

fun main(args: Array<String>) {
    runApplication<DashboardApplication>(*args)
}

@Bean
fun corsConfigurer(): WebMvcConfigurer {
    return object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry?) {
            registry!!.addMapping("/**").allowedOrigins("http://localhost:8080")
        }
    }
}
