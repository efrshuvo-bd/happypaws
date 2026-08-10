package com.auramislab.happypaws.domain.foundation

import com.auramislab.happypaws.core.common.Outcome
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetArchitectureStatusUseCaseTest {

    @Test
    fun invoke_returnsSuccessFromRepository() {
        val repository = ArchitectureStatusRepository {
            Outcome.Success(
                ArchitectureStatus(
                    productName = "HappyPaws",
                    applicationId = "com.auramislab.happypaws",
                    layeringReady = true,
                    diReady = true,
                    notes = "test",
                ),
            )
        }
        val useCase = GetArchitectureStatusUseCase(repository)

        val result = useCase()

        assertTrue(result is Outcome.Success)
        val status = (result as Outcome.Success).value
        assertEquals("HappyPaws", status.productName)
        assertEquals("com.auramislab.happypaws", status.applicationId)
        assertTrue(status.layeringReady)
        assertTrue(status.diReady)
    }
}
