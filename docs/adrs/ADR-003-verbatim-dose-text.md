# ADR-003 — Verbatim dose text

- **Status:** Accepted
- **Date:** 2026-08-08 (documented in repo 2026-08-11, HAP-15)
- **Related:** Medical-safety principles in [Architecture Document](../architecture.md)

## Context

Medication dosage mistakes are high-severity. Automated conversion, rounding, or inference can silently change caregiver-entered medical information.

## Decision

Store and display dose text **verbatim** as entered by caregivers. HappyPaws records care data; it does not diagnose, prescribe, calculate, round, or alter dosages. Tapering requires explicit ordered steps, never interpolated doses.

## Consequences

- Unit conversion only via future explicit user-confirmed flows if ever added.
- Domain tests for medication invariants are mandatory when med features land.
- UI must prioritize dose readability and accessibility.
