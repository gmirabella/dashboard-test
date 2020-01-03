package com.project.dashboard.model

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

data class DownloadList(
        val downloads: List<Download>
)
