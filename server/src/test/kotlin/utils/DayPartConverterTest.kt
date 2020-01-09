package utils

import com.project.dashboard.model.DayPart
import com.project.dashboard.utils.DayPartConverter
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringJUnit4ClassRunner::class)
class DayPartConverterTest{

    var formatter = DateTimeFormatter.ISO_LOCAL_TIME

    @Test
    fun testCalculateDayPartNight(){
        val result = DayPartConverter.parse(LocalTime.parse("03:00:00", formatter))
        assertNotNull(result)
        assertEquals(result, DayPart.NIGHT)
    }

    @Test
    fun testCalculateDayPartMorning(){
        val result = DayPartConverter.parse(LocalTime.parse("09:00:00", formatter))
        assertNotNull(result)
        assertEquals(result, DayPart.MORNING)
    }

    @Test
    fun testCalculateDayPartAfternoon(){
        val result = DayPartConverter.parse(LocalTime.parse("16:00:00", formatter))
        assertNotNull(result)
        assertEquals(result, DayPart.AFTERNOON)
    }

    @Test
    fun testCalculateDayPartEvening(){
        val result = DayPartConverter.parse(LocalTime.parse("22:00:00", formatter))
        assertNotNull(result)
        assertEquals(result, DayPart.EVENING)
    }
}