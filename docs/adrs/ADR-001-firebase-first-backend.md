# ADR-001 — Firebase-first backend

- **Status:** Accepted
- **Date:** 2026-08-08 (documented in repo 2026-08-11, HAP-15)
- **Related:** [Architecture Document](../architecture.md), Confluence HA Architecture

## Context

HappyPaws needs authentication, multi-device sync, file storage, and push notifications for an MVP without standing up custom backend infrastructure.

## Decision

Use Firebase Auth, Firestore, Storage, FCM, and App Check as the primary backend stack. Do not commit production project IDs, API keys, or `google-services.json` to git.

## Consequences

- Security Rules expertise is required for household tenancy.
- Cloud Functions are used sparingly (see ADR-006).
- Firebase provisioning remains a later Task; HAP-15 only establishes architecture posture.
