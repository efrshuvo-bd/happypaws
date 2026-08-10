# ADR-002 — Room as local source of truth

- **Status:** Accepted
- **Date:** 2026-08-08 (documented in repo 2026-08-11, HAP-15)
- **Related:** [Architecture Document](../architecture.md)

## Context

Critical care logging (routines, dose events, emergency pass cache) must work offline, including airplane mode for previously cached data.

## Decision

Room is the local source of truth. Mutations write locally and enqueue sync operations. Remote Firebase data reconciles through a sync engine with explicit conflict policies.

## Consequences

- Schema migrations must be versioned and tested; destructive migration is forbidden for release builds with user data.
- Conflict handling is more complex than last-write-wins (especially for dose Given events — ADR-004).
- Room/database modules are not provisioned in HAP-15; package boundaries prepare for `:core:database` / `:core:sync`.
