package com.auramislab.happypaws.core.common

/**
 * Lightweight typed outcome for domain/data boundaries.
 * Prefer this over throwing across layers for recoverable failures.
 */
sealed class Outcome<out T> {
    data class Success<T>(val value: T) : Outcome<T>()
    data class Failure(val message: String, val cause: Throwable? = null) : Outcome<Nothing>()
}
