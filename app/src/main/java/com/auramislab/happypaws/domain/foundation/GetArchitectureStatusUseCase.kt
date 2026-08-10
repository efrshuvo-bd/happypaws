package com.auramislab.happypaws.domain.foundation

import com.auramislab.happypaws.core.common.Outcome
import javax.inject.Inject

/**
 * Sample use case showing presentation → domain → data flow without feature logic.
 */
class GetArchitectureStatusUseCase @Inject constructor(
    private val repository: ArchitectureStatusRepository,
) {
    operator fun invoke(): Outcome<ArchitectureStatus> = repository.currentStatus()
}
