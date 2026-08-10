package com.auramislab.happypaws.core.common

import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable clock for timezone/DST-sensitive domain logic in later Epics.
 * Default implementation uses wall-clock UTC Instant.
 */
fun interface AppClock {
    fun now(): Instant
}

@Singleton
class SystemAppClock @Inject constructor() : AppClock {
    override fun now(): Instant = Instant.now()
}
