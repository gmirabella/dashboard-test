package com.project.dashboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DashboardApplication

fun main(args: Array<String>) {
    runApplication<DashboardApplication>(*args)
}

