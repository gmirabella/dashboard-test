package service

import com.project.dashboard.dao.DownloadDao
import com.project.dashboard.model.*
import com.project.dashboard.service.DownloadServiceImpl
import com.project.dashboard.service.FilterService
import com.project.dashboard.utils.DayPartConverter
import model.DownloadSamples
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.time.LocalTime
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

@RunWith(SpringJUnit4ClassRunner::class)
class DownloadServiceTest{

    @Mock private lateinit var mockDownloadDao: DownloadDao

    @Mock private lateinit var mockFilterService: FilterService

    @InjectMocks private lateinit var mockDownloadService: DownloadServiceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAll(){
        `when`(mockDownloadDao.getAll()).thenReturn(DownloadSamples.all)
        val result = mockDownloadService.getAll(null, null, null)

        assertNotNull(result)
        assertEquals(result, DownloadList(DownloadSamples.all.count(), DownloadSamples.all))
    }

    @Test
    fun testGetAllByCountry(){
        `when`(mockDownloadDao.getByCountry("ITALY")).thenReturn(DownloadSamples.sameCountry)
        val result = mockDownloadService.getAll("ITALY", null, null)

        assertNotNull(result)
        assertEquals(result, DownloadList(DownloadSamples.sameCountry.count(), DownloadSamples.sameCountry))
    }

    @Test
    fun testGetAllByDayPart(){
        `when`(mockDownloadDao.getByDayPart(DayPart.EVENING)).thenReturn(DownloadSamples.sameDayPart)
        val result = mockDownloadService.getAll(null, DayPart.EVENING, null)

        assertNotNull(result)
        assertEquals(result, DownloadList(DownloadSamples.sameDayPart.count(), DownloadSamples.sameDayPart))
    }

    @Test
    fun testGetAllByLastWeek(){
        `when`(mockDownloadDao.getAll()).thenReturn(DownloadSamples.lastWeek)
        val result = mockDownloadService.getAll(null, null, PeriodDays.LAST_WEEK)

        assertNotNull(result)
        assertEquals(result, DownloadList(DownloadSamples.lastWeek.count(), DownloadSamples.lastWeek))
    }

    @Test
    fun testGetAllByCountryAndDayPart(){
        `when`(mockDownloadDao.getByCountryAndDayPart("ITALY", DayPart.EVENING)).thenReturn(DownloadSamples.sameCountryAndDayPart)
        val result = mockDownloadService.getAll("ITALY", DayPart.EVENING, null)

        assertNotNull(result)
        assertEquals(result, DownloadList(DownloadSamples.sameCountryAndDayPart.count(), DownloadSamples.sameCountryAndDayPart))
    }

    @Test
    fun testGetAllByCountryNotExist(){
        `when`(mockDownloadDao.getByCountry("TEST")).thenReturn(emptyList())
        val result = mockDownloadService.getAll("TEST", null, null)

        assertNotNull(result)
        assertEquals(result, DownloadList(0, emptyList()))
    }

    @Test
    fun saveDownloads(){
        `when`(mockDownloadDao.save(DownloadSamples.test_1.downloadedAt,
                DownloadSamples.test_1.position,
                DownloadSamples.test_1.appId,
                DownloadSamples.test_1.countryName,
                DownloadSamples.test_1.dayPart)
        ).thenReturn(DownloadSamples.test_1.id)

        `when`(mockFilterService.calculateCountryFromLatLog(DownloadSamples.test_1.position.lat.toString(), DownloadSamples.test_1.position.lon.toString())).thenReturn(Country("it", "ITALY"))

        val result = mockDownloadService.save(DownloadSamples.test_1.downloadedAt, InputDownload(DownloadSamples.test_1.position, DownloadSamples.test_1.appId))
        assertNotNull(result)

    }

    @Test
    fun saveDownloadsError(){
        `when`(mockDownloadDao.save(DownloadSamples.test_1.downloadedAt,
                DownloadSamples.test_1.position,
                DownloadSamples.test_1.appId,
                DownloadSamples.test_1.countryName,
                DownloadSamples.test_1.dayPart)
        ).thenReturn(DownloadSamples.test_1.id)

        `when`(mockFilterService.calculateCountryFromLatLog(DownloadSamples.test_1.position.lat.toString(), DownloadSamples.test_1.position.lon.toString())).thenReturn(Country("fr", "FRANCE"))


        val result = mockDownloadService.save(DownloadSamples.test_1.downloadedAt, InputDownload(DownloadSamples.test_1.position, DownloadSamples.test_1.appId))
        assertNotEquals(result, DownloadSamples.test_1.id)

    }

    private fun <T> any(): T = Mockito.any<T>()

}