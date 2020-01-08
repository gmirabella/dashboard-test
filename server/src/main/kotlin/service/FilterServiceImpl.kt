package com.project.dashboard.service

import com.project.dashboard.model.*
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.IllegalArgumentException
import java.time.LocalTime
import java.util.Locale
import javax.inject.Inject


@Service
class FilterServiceImpl : FilterService {

    @Inject
    private lateinit var restTemplate: RestTemplate

    companion object {
        const val URL = "https://nominatim.openstreetmap.org"
    }

    override fun getFilters(): Filters {

        return Filters(
                countries = generateCountry(),
                dayParts = DayPart.values().toList(),
                periodDays = PeriodDays.values().toList()
        )
    }

    override fun calculateCountryFromLatLog(lat: String, lon: String): Country {

        val headers = HttpHeaders()
        headers.add("user-agent", "Mozilla/5.0 Firefox/26.0");

        return try {
            val entity = HttpEntity("parameters", headers)
            val respEntity = restTemplate.exchange("$URL/reverse.php?format=json&lat=$lat&lon=$lon", HttpMethod.GET, entity, Geocode::class.java)
            convertGeocode(respEntity.body!!)
        } catch (e: Throwable) {
            throw IllegalArgumentException("geocode for lat:<$lat>, lon=<$lon> not found")
        }
    }

    override fun calculateDayPart(hour: LocalTime): DayPart {
        return if (hour.isBefore(LocalTime.parse("00:00")) && hour.isBefore(LocalTime.parse("06:00"))) DayPart.NIGHT
        else if (hour.isAfter(LocalTime.parse("06:00")) && hour.isBefore(LocalTime.parse("12:00"))) DayPart.MORNING
        else if (hour.isAfter(LocalTime.parse("12:00")) && hour.isBefore(LocalTime.parse("18:00"))) DayPart.AFTERNOON
        else DayPart.EVENING

    }

    private fun generateCountry(): List<Country> {
        val countryCodes = Locale.getISOCountries()

        return countryCodes.map { it ->
            Country(it, Locale("", it).displayCountry)
        }
    }

    private fun convertGeocode(geocode: Geocode): Country {
        val code = Locale.getISOCountries().filter { it ->
            it == geocode.address.country_code.toUpperCase()}.firstOrNull()

        return if(code != null) Country(code, Locale("", code).displayCountry)
               else throw IllegalArgumentException("Country with code : <$code> not exists!")
        }
    }

