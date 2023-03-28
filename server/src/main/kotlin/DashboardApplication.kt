package com.project.dashboard


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.*



@SpringBootApplication
@ComponentScan
class DashboardApplication

fun main(args: Array<String>) {
    runApplication<DashboardApplication>(*args)

}