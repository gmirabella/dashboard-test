package com.project.dashboard.model

import java.time.Instant


data class Download(
        val id : Long,
        val pos : Position,
        val appId: AppId,
        val downloadedAt: Instant
)

data class Position(
        val lat: Long,
        val lon: Long
)

enum class AppId(displayName : String){
    IOS_MATE("Ios Mate"),
    IOS_ALLERT("Ios Allert")
}