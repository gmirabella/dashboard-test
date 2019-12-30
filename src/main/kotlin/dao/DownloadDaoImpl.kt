package com.project.dashboard.dao

import com.project.dashboard.model.AppId
import com.project.dashboard.model.Download
import com.project.dashboard.model.DownloadRowMapper
import com.project.dashboard.model.Position
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.Instant
import javax.inject.Inject

@Repository
class DownloadDaoImpl : DownloadDao {

    private val log : Logger = LoggerFactory.getLogger(DownloadDaoImpl::class.java)

    @Inject private lateinit var jdbcTemplate: JdbcTemplate

    override fun getAll() : List<Download> {
        log.info("--- Getting all downloads ---")
        return jdbcTemplate.query("SELECT * FROM download", DownloadRowMapper())
    }

    override fun getById(id: Long): Download? {
        log.info("---Getting download by id : <$id> ---")
        val sqlQuery = "SELECT * FROM download WHERE id = ?"

        val statement = PreparedStatementCreator { con ->
            con.prepareStatement(sqlQuery).apply {
                setLong(1, id)
            }
        }

        return jdbcTemplate.query(statement, DownloadRowMapper()).firstOrNull()
    }

    override fun save(downloadedAt: Instant, position: Position, appId: AppId): Long {
        log.info("--- Saving download from <$appId> with lat, lon = <${position.lat}, ${position.lon}> ---")

        val sqlQuery = """
            INSERT INTO download (
            id,
            lat,
            lon,
            app_id,
            downloaded_at
            ) values (nextval('sq_download'), ?, ?, ?, ?)
            returning id
        """.trimIndent()

        val statement = PreparedStatementCreator { con ->
            con.prepareStatement(sqlQuery).apply {
                setBigDecimal(1, position.lat)
                setBigDecimal(2, position.lon)
                setString(3, appId.name)
                setTimestamp(4, Timestamp.from(downloadedAt))
            }
        }

        return jdbcTemplate.query(statement, RowMapper{ rs, _ -> rs.getLong("id")}).first()
    }
}