package controller

import com.cuebiq.audiencebuilder.container.KPostgresSQLContainer
import com.project.dashboard.DashboardApplication
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DashboardApplication::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(initializers = [FilterControllerTest.Initializer::class])
@ActiveProfiles("test")
class FilterControllerTest {
    companion object {
        const val port = 8081
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

    @Test fun getFilters() {
        Given {
            header("user-agent", "Mozilla/5.0 Firefox/26.0")
        } When {
            get("/filters")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
            body("countries", Matchers.`is`(Matchers.notNullValue()))
            body("dayParts", Matchers.`is`(Matchers.notNullValue()))
            body("periodDays", Matchers.`is`(Matchers.notNullValue()))
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