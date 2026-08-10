package com.auramislab.happypaws.data.foundation

import com.auramislab.happypaws.core.common.Outcome
import com.auramislab.happypaws.domain.foundation.ArchitectureStatus
import com.auramislab.happypaws.domain.foundation.ArchitectureStatusRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * In-memory placeholder repository for architecture foundation (HAP-15).
 * Replaced when Firebase/Room Tasks land — no secrets or remote IDs here.
 */
@Singleton
class InMemoryArchitectureStatusRepository @Inject constructor() : ArchitectureStatusRepository {
    override fun currentStatus(): Outcome<ArchitectureStatus> =
        Outcome.Success(
            ArchitectureStatus(
                productName = "HappyPaws",
                applicationId = "com.auramislab.happypaws",
                layeringReady = true,
                diReady = true,
                notes = "Firebase-first + Room local SoT planned; record-not-diagnose medical safety.",
            ),
        )
}
