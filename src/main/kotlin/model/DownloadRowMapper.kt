package com.project.dashboard.model

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class DownloadRowMapper : RowMapper<Download> {

    override fun mapRow(rs: ResultSet?, rowNum: Int): Download {
        if (rs == null) throw IllegalArgumentException("Resultset is null")
        return Download(
                id            = rs.getLong("id"),
                pos           = Position(rs.getLong("lat"), rs.getLong("lon")),
                appId         = AppId.valueOf(rs.getString("app_id")) ,
                downloadedAt = rs.getTimestamp("downloaded_at").toInstant()
        )
    }
}