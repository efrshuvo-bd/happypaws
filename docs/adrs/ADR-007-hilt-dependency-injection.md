# ADR-007 — Hilt dependency injection

- **Status:** Accepted
- **Date:** 2026-08-11
- **Task:** HAP-15
- **Related:** [Architecture Document](../architecture.md) §7, AuraMis Hourora precedent

## Context

HappyPaws needs a DI baseline at app/feature boundaries with compile-time verification, ViewModel injection, and test fakes. Alternatives considered: Hilt (Dagger) and Koin.

## Decision

Adopt **Hilt** as the project-standard DI framework.

Rationale:

- Matches Confluence Architecture §7 guidance and AuraMis Android precedent (Hourora).
- Compile-time binding validation catches wiring errors before runtime.
- First-class `@HiltViewModel` / `@AndroidEntryPoint` integration with Compose Navigation.
- Clear `SingletonComponent` boundaries for DB, sync, and auth singletons in later Tasks.

Koin remains rejected for this codebase to avoid dual-DI styles across AuraMis Android apps.

## Consequences

- Requires KSP + Hilt Gradle plugins in `:app`.
- Feature Tasks add focused `@Module` types; avoid a single god-module.
- Unit tests can replace bindings with fakes; HAP-15 ships a sample use-case test without full Hilt test runtime.
