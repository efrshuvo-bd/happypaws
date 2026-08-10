# ADR-005 — Monetization isolation

- **Status:** Accepted
- **Date:** 2026-08-08 (documented in repo 2026-08-11, HAP-15)
- **Related:** [Architecture Document](../architecture.md) §26

## Context

Ads and Play Billing SDKs must not couple into medication/care domain internals, and must not appear on critical medical-safety screens.

## Decision

Isolate `:feature:ads` and `:feature:billing` behind an entitlement interface in core. Care domain must not depend on ads SDK. Denylist ads on emergency, serious reaction, and duplicate-dose confirmation surfaces.

## Consequences

- Extra DI surfaces for entitlements.
- Safer care core; monetization can evolve without touching dose logic.
- Module extraction may follow package-layering bootstrap in `:app` (HAP-15).
