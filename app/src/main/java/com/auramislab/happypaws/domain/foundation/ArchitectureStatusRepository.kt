package com.auramislab.happypaws.domain.foundation

import com.auramislab.happypaws.core.common.Outcome

/**
 * Sample repository contract (domain owns the interface; data implements).
 * No Firebase/Room yet — later Tasks replace the in-memory stub.
 */
fun interface ArchitectureStatusRepository {
    fun currentStatus(): Outcome<ArchitectureStatus>
}
