package service

import com.project.dashboard.dao.DownloadDao
import com.project.dashboard.model.DayPart
import com.project.dashboard.model.DownloadList
import com.project.dashboard.model.PeriodDays
import com.project.dashboard.service.DownloadServiceImpl
import model.DownloadSamples
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringJUnit4ClassRunner::class)
class DownloadServiceTest{

    @Mock
    private lateinit var mockDownloadDao: DownloadDao

    @InjectMocks
    private lateinit var mockDownloadService: DownloadServiceImpl

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

}