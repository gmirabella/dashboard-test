package com.project.dashboard.model

import org.springframework.jdbc.core.RowMapper
import java.math.BigDecimal
import java.sql.ResultSet
import java.time.Instant
import java.time.LocalDateTime

data class Download(
        val id : Long,
        val position : Position,
        val appId: AppId,
        val countryName: String,
        val dayPart: DayPart,
        val downloadedAt: Instant
)

data class Position(
        val lat: BigDecimal,
        val lon: BigDecimal
)

enum class AppId{
    IOS_MATE,
    IOS_ALLERT
}

class DownloadRowMapper : RowMapper<Download> {

    override fun mapRow(rs: ResultSet?, rowNum: Int): Download {
        if (rs == null) throw IllegalArgumentException("Resultset is null")
        return Download(
                id           = rs.getLong("id"),
                position     = Position(rs.getBigDecimal("lat"), rs.getBigDecimal("lon")),
                appId        = AppId.valueOf(rs.getString("app_id")),
                countryName  = rs.getString("country_name"),
                dayPart      = DayPart.valueOf(rs.getString("day_part")),
                downloadedAt = rs.getTimestamp("downloaded_at").toInstant()
        )
    }
}