package com.project.dashboard.service

import com.project.dashboard.dao.DownloadDao
import com.project.dashboard.model.*
import com.project.dashboard.utils.DayPartConverter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.inject.Inject

@Service
class DownloadServiceImpl : DownloadService {

    @Inject private lateinit var downloadDao: DownloadDao

    @Inject private lateinit var filterService: FilterService

    companion object {
        val log = LoggerFactory.getLogger(DownloadServiceImpl::class.java)
    }

    override fun getAll(countryName: String?, dayPart: DayPart?, period: PeriodDays?): DownloadList {
        val downloads = filterByPeriod(period, checkFilters(countryName, dayPart))
        return DownloadList(
                count     = downloads.count(),
                downloads = downloads
        )
    }

    override fun save(now: Instant,input: InputDownload): Long {

        val country = filterService.calculateCountryFromLatLog(input.position.lat.toString(), input.position.lon.toString())
        val dayPart = DayPartConverter.parse(LocalTime.now())

        return downloadDao.save(now, input.position, input.appId, country.name, dayPart)
    }

    private fun checkFilters(countryName: String?, dayPart: DayPart?) : List<Download>{
        return if(!countryName.isNullOrEmpty() && dayPart !=null) downloadDao.getByCountryAndDayPart(countryName, dayPart)
               else if(!countryName.isNullOrEmpty()) downloadDao.getByCountry(countryName)
               else if (dayPart !=null) downloadDao.getByDayPart(dayPart)
               else downloadDao.getAll()
    }

    private fun filterByPeriod(period: PeriodDays?, downloads: List<Download>): List<Download> {
        return if (period != null) {
            log.info("--- Filter by period :<${period.name}> ---")
            downloads.filter { download ->
                download.downloadedAt.isAfter(Instant.now().minus(period.days, ChronoUnit.DAYS))
            }
        } else downloads
    }
}