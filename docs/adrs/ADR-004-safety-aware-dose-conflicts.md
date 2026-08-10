# ADR-004 — Safety-aware dose conflicts

- **Status:** Accepted
- **Date:** 2026-08-08 (documented in repo 2026-08-11, HAP-15)
- **Related:** [Architecture Document](../architecture.md) §17–18

## Context

Multiple caregivers and offline devices can log the same medication occurrence, creating double-dose risk.

## Decision

On conflicting Given evidence: warn in UI, require acknowledge to proceed, and retain conflicting evidence on sync. Never silently discard Given dose events via last-write-wins.

## Consequences

- More UX and test coverage than simple LWW merge.
- Sync must create conflict records rather than deleting evidence.
- Ads must not appear on duplicate-dose confirmation surfaces.
