package com.project.dashboard.service

import com.project.dashboard.model.Country
import com.project.dashboard.model.DayPart
import com.project.dashboard.model.Filters
import org.springframework.stereotype.Service
import java.util.Locale



@Service
class FilterServiceImpl : FilterService {

    override fun getFilters(): Filters {

        return Filters(
                countries = generateCountry() ,
                dayParts  = DayPart.values().toList()
        )
    }

    private fun generateCountry() : List<Country>{
        val countryCodes = Locale.getISOCountries()

        return countryCodes.map { it ->
            Country(it, Locale("",it).displayCountry)
        }
    }
}
