# ADR-006 — Functions only when justified

- **Status:** Accepted
- **Date:** 2026-08-08 (documented in repo 2026-08-11, HAP-15)
- **Related:** [Architecture Document](../architecture.md) §10

## Context

Prefer Firestore Security Rules and client/domain logic where safe. Unnecessary Cloud Functions increase cost and attack surface.

## Decision

Use Cloud Functions only when the client cannot safely enforce the operation — for example share-link token minting/expiry helpers, account deletion orchestration, and abuse-resistant invite validation. Never place medication dosage intelligence in Functions.

## Consequences

- Smaller serverless surface.
- Rules and client domain remain primary enforcement for care data.
