package com.project.dashboard.service

import com.project.dashboard.model.DayPart
import com.project.dashboard.model.Geocode
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.ws.rs.POST
import org.apache.catalina.manager.StatusTransformer.setContentType
import org.springframework.http.*
import java.time.*
import java.util.*


@Service
class CalculateFilterServiceImpl : CalculateFilterService {

    @Inject private lateinit var restTemplate: RestTemplate

    companion object {
        const val URL = "https://nominatim.openstreetmap.org"
    }

    override fun calculateCountryFromLatLog(lat: String, lon: String): Geocode {

        val headers = HttpHeaders()
        headers.add("user-agent", "Mozilla/5.0 Firefox/26.0");

        return try {
            val entity = HttpEntity("parameters", headers)
            val respEntity = restTemplate.exchange("$URL/reverse.php?format=json&lat=$lat&lon=$lon", HttpMethod.GET, entity, Geocode::class.java)
            respEntity.body
        } catch (e: Throwable) {
            throw IllegalArgumentException("geocode for lat:<$lat>, lon=<$lon> not found")
        }
    }

    override fun calculateDayPart(hour: LocalTime): DayPart {

        return if(hour.isBefore(LocalTime.parse("00:00")) && hour.isBefore(LocalTime.parse("06:00")) ) DayPart.NIGHT
               else if(hour.isAfter(LocalTime.parse("06:00")) && hour.isBefore(LocalTime.parse("12:00"))) DayPart.MORNING
               else if (hour.isAfter(LocalTime.parse("12:00")) && hour.isBefore(LocalTime.parse("18:00"))) DayPart.AFTERNOON
               else DayPart.EVENING

    }
}

