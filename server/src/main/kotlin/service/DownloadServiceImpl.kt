package com.project.dashboard.service

import com.project.dashboard.dao.DownloadDao
import com.project.dashboard.model.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@Service
class DownloadServiceImpl : DownloadService {

    @Inject private lateinit var downloadDao: DownloadDao

    @Inject private lateinit var filterService: FilterService

    @Inject private lateinit var calculateFilterService: CalculateFilterService

    override fun getAll(countryName: String?, dayPart: DayPart?): DownloadList {
        return if(!countryName.isNullOrEmpty()) DownloadList(downloadDao.getByCountry(countryName))
               else if (dayPart !=null) DownloadList(downloadDao.getByDayPart(dayPart))
               else DownloadList(downloadDao.getAll())
    }

    override fun getById(id: Long): Download? {
        return downloadDao.getById(id)
    }

    override fun save(now: Instant,input: InputDownload): Long {

        val geocode = calculateFilterService.calculateCountryFromLatLog(input.position.lat.toString(), input.position.lon.toString())
        val dayPart = calculateFilterService.calculateDayPart(LocalTime.now())

        return downloadDao.save(now, input.position, input.appId, geocode.address.country, dayPart)
    }

   /* private fun convertGeocode(geocode: Geocode) : Country{
        filterService.getFilters().countries.map { it ->
                    if(it.isoCode == geocode.address.country_code.toUpperCase())
                   return it
                })
    }*/
}