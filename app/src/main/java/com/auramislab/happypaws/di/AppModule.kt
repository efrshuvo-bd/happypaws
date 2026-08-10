package com.auramislab.happypaws.di

import com.auramislab.happypaws.core.common.AppClock
import com.auramislab.happypaws.core.common.SystemAppClock
import com.auramislab.happypaws.data.foundation.InMemoryArchitectureStatusRepository
import com.auramislab.happypaws.domain.foundation.ArchitectureStatusRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt bindings for architecture foundation placeholders (HAP-15).
 * Feature modules add their own @Module types later; ads/billing stay isolated.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindAppClock(impl: SystemAppClock): AppClock

    @Binds
    @Singleton
    abstract fun bindArchitectureStatusRepository(
        impl: InMemoryArchitectureStatusRepository,
    ): ArchitectureStatusRepository
}
