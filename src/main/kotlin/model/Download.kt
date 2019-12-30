package com.project.dashboard.model

import java.math.BigDecimal
import java.time.Instant


data class Download(
        val id : Long,
        val pos : Position,
        val appId: AppId,
        val downloadedAt: Instant
)

data class Position(
        val lat: BigDecimal,
        val lon: BigDecimal
)

enum class AppId(displayName : String){
    IOS_MATE("Ios Mate"),
    IOS_ALLERT("Ios Allert")
}