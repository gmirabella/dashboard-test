package config

import io.restassured.module.kotlin.extensions.Given
import io.restassured.specification.RequestSpecification
import org.mockito.Mockito
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.TestConfiguration

@TestConfiguration
class TestAppConfiguration {

    fun <T> any(): T = Mockito.any<T>()

    fun <T> anyType(type: Class<T>): T = Mockito.any<T>(type)
}

