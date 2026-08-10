package com.auramislab.happypaws.domain.foundation

/**
 * Foundation placeholder status only — not a product feature model.
 * Demonstrates domain-layer shapes for HAP-15 without pets/meds/auth entities.
 */
data class ArchitectureStatus(
    val productName: String,
    val applicationId: String,
    val layeringReady: Boolean,
    val diReady: Boolean,
    val notes: String,
)
