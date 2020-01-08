package model

import com.project.dashboard.model.AppId
import com.project.dashboard.model.DayPart
import com.project.dashboard.model.Download
import com.project.dashboard.model.Position
import java.math.BigDecimal
import java.time.Instant

object DownloadSamples{

    val test_1 = Download(
            id = 1,
            appId = AppId.IOS_ALLERT,
            downloadedAt = Instant.now(),
            position = Position(BigDecimal.ONE, BigDecimal.ONE),
            countryName = "ITALY",
            dayPart = DayPart.EVENING
    )

    val test_2 = Download(
            id = 2,
            appId = AppId.IOS_ALLERT,
            downloadedAt = Instant.now(),
            position = Position(BigDecimal.ONE, BigDecimal.ONE),
            countryName = "ITALY",
            dayPart = DayPart.MORNING

    )

    val test_3 = Download(
            id = 3,
            appId = AppId.IOS_MATE,
            downloadedAt = Instant.now(),
            position = Position(BigDecimal.ONE, BigDecimal.ONE),
            countryName = "FRANCE",
            dayPart = DayPart.EVENING

    )

    val all = listOf(test_1, test_2, test_3)
    val sameCountry = listOf(test_1, test_2)
    val sameDayPart = listOf(test_1, test_3)
    val lastWeek = listOf(test_1, test_2, test_3)
    val sameCountryAndDayPart = listOf(test_1)
}