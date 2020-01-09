package controller

import com.cuebiq.audiencebuilder.container.KPostgresSQLContainer
import com.project.dashboard.DashboardApplication
import config.TestAppConfiguration
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DashboardApplication::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = [TestAppConfiguration::class], initializers = [DownloadControllerTest.Initializer::class])
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DownloadControllerTest {

    companion object {
        const val port = 8082
        var container = KPostgresSQLContainer(baseDir = "ddl").withDatabaseName("empatica")
                .withUsername("empatica_rw")
                .withPassword("test")!!

        @BeforeClass
        @JvmStatic fun beforeClass() {
            RestAssured.port = port
            container.start()
        }

        @AfterClass
        @JvmStatic fun afterClass()  = container.stop()
    }

    @Test
    fun test_01_getAll() {
        Given {
            header("user-agent", "Mozilla/5.0 Firefox/26.0")
        } When {
            get("/downloads")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
            body("count", Matchers.`is`(13))
            body("downloads", Matchers.`is`(Matchers.notNullValue()))
        }
    }

    @Test
    fun test_02_getAllByCountry() {
        Given {
            queryParam("countryName", "Italy")
        } When {
            get("/downloads")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
            body("count", Matchers.`is`(3))
            body("downloads", Matchers.`is`(Matchers.notNullValue()))
        }
    }

    @Test
    fun test_03_getAllByPartDays() {
        Given {
            queryParam("dayPart", "MORNING")
        } When {
            get("/downloads")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
            body("count", Matchers.`is`(3))
            body("downloads", Matchers.`is`(Matchers.notNullValue()))
        }
    }

    @Test
    fun test_04_getAllByPartDaysNotCorrect() {
        Given {
            queryParam("dayPart", "LUNCH")
        } When {
            get("/downloads")
        } Then {
            statusCode(400)
            contentType(ContentType.JSON)
        }
    }

    @Test
    fun test_05_getAllByPeriod() {
        Given {
            queryParam("period", "LAST_YEAR")
        } When {
            get("/downloads")
        } Then {
            statusCode(200)
            body("count", Matchers.`is`(13))
            body("downloads", Matchers.`is`(Matchers.notNullValue()))
        }
    }

    @Test
    fun test_06_getAllByCountryAndDayPartAndPeriod() {
        Given {
            queryParam("countryName", "Italy")
            queryParam("dayPart", "AFTERNOON")
            queryParam("period", "LAST_YEAR")
        } When {
            get("/downloads")
        } Then {
            statusCode(200)
            body("count", Matchers.`is`(2))
            body("downloads", Matchers.`is`(Matchers.notNullValue()))
        }
    }

    @Test
    fun test_07_saveCorrect() {
        Given {
            body("""
                {
                    "position": {
                        "lat": 41.8948021,
                        "lon": 12.4853382
                },
                    "appId": "IOS_MATE"
                }
            """)
            contentType(ContentType.JSON)
        } When {
            post("/downloads")
        } Then {
            log().body()
            statusCode(200)
            contentType(ContentType.JSON)

        }
    }

    @Test
    fun test_07_saveErrorAppId() {
        Given {
            body("""
                {
                    "position": {
                        "lat": 41.8948021,
                        "lon": 12.4853382
                }
                }
            """)
            contentType(ContentType.JSON)
        } When {
            post("/downloads")
        } Then {
            statusCode(400)
            contentType(ContentType.JSON)
        }
    }

    class Initializer: ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(c: ConfigurableApplicationContext) {
            TestPropertyValues.of(
                    "server.port=$port",
                    "spring.datasource.url=${container.jdbcUrl}",
                    "spring.datasource.username=${container.username}",
                    "spring.datasource.password=${container.password}"
            ).applyTo(c.environment)
        }
    }

}