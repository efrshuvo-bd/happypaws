# HappyPaws Architecture Document

| Field | Value |
| --- | --- |
| Document owner | Engineering Lead (AuraMis Lab) |
| Version | 0.1.0 |
| Status | Pre-Implementation |
| Last updated | 2026-08-08 |
| Confluence | [HappyPaws Architecture Document](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898685) |
| Parent | [HappyPaws — Pet Routine & Medication Tracker](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628) |

> Repository mirror of Confluence planning authority. Prefer Confluence if they diverge until HAP-13 alignment is merged.

`To Do → In Progress → In Review → Done`  
Testing = In Progress gate + post-merge validation before Done

## 1. Purpose

This document defines the target architecture for HappyPaws (`com.auramislab.happypaws`), an Android pet routine and medication tracker. It guides implementation Epics HAP-1–HAP-12 without including secrets, credentials, or Firebase project identifiers.

**Status:** Pre-Implementation. Design handoff is Pending.

## 2. Goals and non-goals

### Goals

* Offline-capable care logging with safety-aware sync
* Faithful medication dose representation (never alter dosages)
* Clear modular boundaries for UI, domain, data, and monetization
* Privacy-by-design for health data, documents, shares, and widgets
* Testability and observability suitable for Epic quality gates

### Non-goals

* Diagnosis/treatment engines
* Multi-platform clients in MVP
* Production credential material in documentation
* Overuse of Cloud Functions where Security Rules + client logic suffice

## 3. Architectural principles

1. **Record, do not diagnose** — product logic must not prescribe or invent medical values.
2. **Explicit over inferred** — tapering, timezone impact, and entitlements require explicit user/system rules.
3. **Offline-first for critical care** — local source of truth with sync reconciliation.
4. **Safety-aware conflict handling** — dose events are never blindly last-write-wins.
5. **Least privilege** — household roles + backend rules deny by default.
6. **Boundary isolation** — ads/billing cannot depend on domain medication internals beyond entitlement flags.
7. **Privacy minimization** — logs/analytics exclude PII and dose texts.
8. **Adaptive UI** — phone/tablet responsive layouts; Dynamic Color optional enhancement.
9. **Document decisions** — ADRs for cross-cutting choices.
10. **No secrets in repo/docs** — configuration via local/CI secrets managers only.

## 4. System context

```
[Android App Compose] -> Room local SoT + Sync Engine
  -> Firebase Auth / Firestore / Storage / FCM / App Check
  -> Optional Cloud Functions (justified only)
  -> Play Billing + Ads SDKs (isolated modules)
```

## 5. Module structure (target)

| Module | Responsibility |
| --- | --- |
| `:app` | Application shell, navigation host, DI root |
| `:core:common` | Result types, dispatchers, time clock, utils |
| `:core:model` | Immutable domain models |
| `:core:database` | Room entities, DAOs, migrations |
| `:core:datastore` | Preferences DataStore |
| `:core:network` | Firebase wrappers (no secrets), DTO mappers |
| `:core:sync` | Queue, conflict policies, workers |
| `:core:notifications` | Local scheduling + FCM handling |
| `:core:ui` | Shared Compose components, theme |
| `:feature:*` | Auth, pets, routines, meds, health, caregiver, emergency, insights, settings |
| `:feature:widgets` | App widgets |
| `:feature:billing` | Play Billing only |
| `:feature:ads` | Ads SDK only |
| `:testing` | Fakes, fixtures, medical-safety test helpers |

Dependency rule: `feature → domain/core`; `ads/billing` must not import medication domain internals; `app` wires all.

## 6. Layering: UI / domain / data

### UI

* Jetpack Compose + Material 3
* Unidirectional data flow: State/Event via ViewModel
* Navigation via type-safe routes (Navigation Compose)
* Adaptive layouts (window size classes)

### Domain

* Use cases for create/log/sync/share/export
* Pure Kotlin scheduling/conflict helpers where possible
* Medical-safety invariants enforced in domain (dose immutability, duplicate windows)

### Data

* Repositories coordinate Room + remote
* Mappers keep DTOs out of UI
* Idempotency keys on mutating care operations

## 7. Dependency injection

* Hilt (or project-standard DI) at app/feature boundaries
* Singleton for DB, sync engine, auth state
* Fake bindings in androidTest/unitTest
* Clock abstraction for timezone/DST tests

## 8. Navigation architecture

* Single activity
* Auth graph vs main graph gated by session
* Deep links from notifications/widgets/shares into feature routes
* Emergency pass reachable with minimal navigation depth
* Back stack rules: destructive confirmations are dialogs/routes with explicit cancel

## 9. Local persistence (Room)

Primary local store for pets, households, memberships, routines, medications, taper steps, dose events, inventory, reactions, metrics, diet, emergency pass cache, documents metadata, SyncOps queue, conflict records, entitlement cache.

Migrations are versioned and tested. Destructive migration is forbidden for release builds with user data.

## 10. Firebase architecture (no IDs/secrets)

### Auth

Google + Email/Password; session via auth state flows; sign-out clears secure session material and private caches per privacy design.

### Firestore

Hierarchical data by household/pet with security rules; server timestamps + client operation IDs; soft-delete/archive; rules enforce membership roles.

### Storage

Pet documents/images under authorized paths; content-type/size validation; rules mirror Firestore authorization.

### Cloud Functions (justified only)

Use only when client cannot safely enforce: share-link token minting/expiry helpers, account deletion orchestration, abuse-resistant invite validation. Avoid medication dosage intelligence in Functions.

### FCM

Device token registration; preference-aware pushes; data vs notification messages per UX needs.

### App Check

Required for deployed backend abuse protection; fail closed in production configurations.

## 11. Pets and household

Household is tenancy boundary; pets belong to household; roles Owner/Editor/Viewer (exact matrix open); invites single-use/expirable; removal revokes access and dependent share tokens.

## 12. Routines

Recurrence definitions stored explicitly; occurrence materialization strategy in ADR; completions are events with actor + timestamp; pause affects future occurrences only.

## 13. Medication and tapering

* Medication stores **dose text verbatim**
* Tapering is ordered explicit steps (dose text + schedule bounds)
* Scheduler reads steps; never interpolates doses
* Skip/missed/given are first-class statuses
* Duplicate-dose detector uses safety window + occurrence identity
* Domain tests mandatory for med invariants

## 14. Inventory and refill

Optional inventory quantity/unit/threshold; decrements on given doses when enabled; refill events increase quantity; inventory math must not rewrite dose text.

## 15. Timezone architecture

Persist IANA timezone IDs; travel change UX keep wall-clock vs convert-with-preview; DST vectors covered by unit tests using injectable Clock; notifications rescheduled after confirmed TZ policy application.

## 16. Notifications architecture

Local alarms/WorkManager; FCM for multi-device/caregiver fanout; action handlers call domain use cases including duplicate-dose checks; preference center gates categories; quiet hours policy documented; permission onboarding for modern Android.

## 17. Duplicate-dose architecture

```
LogDose intent → load occurrence window →
  if conflicting Given exists → Warning UI →
    Cancel | Confirm-with-ack → persist DoseEvent (+ flags) → enqueue sync
```

Sync path runs equivalent detector for concurrent caregiver offline logs and creates conflict records rather than deleting evidence.

## 18. Offline, sync, and conflicts

### Offline

Room is source of truth; critical reads today/med due/emergency cache; mutations write local + SyncOp.

### Sync

Push pending ops; pull remote changes; backoff + batching; progress visible; auth failures trigger re-auth.

### Conflicts

| Entity | Policy sketch |
| --- | --- |
| DoseEvent | Keep both / flag conflict; never silent discard of Given |
| Pet profile fields | Field-level merge or user prompt |
| Routine definition | Version compare + prompt |
| Emergency pass | Latest explicit save with cache refresh stamp |
| Inventory | Event-sourced adjustments preferred over absolute blind overwrite |

## 19. Metrics and diet

Append-only entries with timestamps; charts/insights descriptive only; export includes selected ranges; no diagnostic inference layer.

## 20. Emergency cache

Dedicated local protected cache for Emergency Vet Pass snapshots; last-updated watermark; refresh on successful remote save/sync; available in airplane mode for previously cached pets.

## 21. Documents

Metadata in DB; bytes in Storage; upload/download via repository; delete removes both with consistency checks; not embedded into widgets.

## 22. Secure link and QR

Tokenized shares with scope + expiry + revocation; QR encodes share URL/token reference not raw medical dumps; access validates token server-side; audit minimal access metadata without unnecessary PII.

## 23. PDF / CSV export

Generator in export module; dose texts injected verbatim; background dispatcher; share via FileProvider; golden tests where feasible.

## 24. Widgets

Glance/AppWidget module reads local DB; refresh via periodic + content-change triggers; privacy redaction; deep link into app routes.

## 25. Adaptive layouts and Dynamic Color

WindowSizeClass-driven layouts; Material 3 theming; Dynamic Color optional with fallback; critical medical screens prioritize readability.

## 26. Ads and billing boundaries

```
:feature:ads  → EntitlementReader (interface in core)
:feature:billing → EntitlementWriter/Reader
:domain:care  → NO dependency on ads SDK
UI denylist → emergency, serious reaction, duplicate-dose confirm
```

Billing uses Play Billing Library; entitlements cached locally and refreshed. Ads fail soft.

## 27. Security and privacy architecture

TLS only; secure token storage; App Check; security rules deny cross-household access; PII/health redaction in logs/crash/analytics; share TTL + revocation; widget minimization; consent gates for optional telemetry/ads.

## 28. Lifecycle

Auth state drives navigation; process death uses SavedStateHandle where needed with durable care data in Room; background sync workers and notification reschedule after reboot as platform allows; app update runs migrations before feature use.

## 29. Deletion architecture

Account/pet deletion: confirmation challenges → revoke shares/invites → delete/anonymize remote data → clear local DB/caches → sign-out. Sole-owner constraints require transfer/dissolve guidance before account deletion.

## 30. Error handling

Typed Result/Outcome in domain; user-facing recoverable errors with actions; non-fatal logging with codes; never show stack traces or secrets in UI.

## 31. Observability

Structured logs (no PII/doses); crash reporting with scrubbing; sync success/failure counters; performance traces for cold start/sync on demand in debug/internal builds.

## 32. Migration strategy

Room schema versions with explicit migrations; remote schema evolves additively; feature flags for risky cutovers; downtime-free client compatibility window for one prior schema when feasible.

## 33. Backup and restore

Rely on user export + carefully configured platform backup; do not silently restore stale dose state over newer conflict-flagged state; document Auto Backup inclusions/exclusions in implementation Epic.

## 34. Performance architecture

Lazy lists; paging for history; avoid main-thread disk/network; image caching; sync batching; startup deferral of non-critical SDK init (ads/billing).

## 35. Testability architecture

Injectable clock, conflict detector, repositories; in-memory Room; fake Auth/Firestore layers; medical-safety fixture library; pure tests for scheduling/timezone/DST.

## 36. Release architecture

Branches: `main`, `develop`, `task/HAP-…`, later `release/internal-vX.Y.Z`. PR target `develop`; human review; no self-merge. Epic gate publishes `docs/test-reports/<epic-key>-test-report.md`. Internal release only after HAP-12 quality gate.

## 37. Architecture Decision Records (ADRs)

### ADR-001 — Firebase-first backend

* **Context:** Need auth, sync, storage quickly for MVP
* **Decision:** Firebase Auth/Firestore/Storage/FCM/App Check
* **Consequences:** Rules skill required; Functions used sparingly

### ADR-002 — Room as local source of truth

* **Context:** Offline care logging required
* **Decision:** Room-first with sync engine
* **Consequences:** Migration discipline; conflict engine complexity

### ADR-003 — Verbatim dose text

* **Context:** Medical safety
* **Decision:** Store/display dose as entered; no silent coercion
* **Consequences:** Unit conversion only via explicit user flows if ever added

### ADR-004 — Safety-aware dose conflicts

* **Context:** Multi-caregiver double-dose risk
* **Decision:** Warn + acknowledge; sync keeps evidence
* **Consequences:** More UX/testing than LWW

### ADR-005 — Monetization isolation

* **Context:** Ads/billing risk coupling
* **Decision:** Separate feature modules + entitlement interface
* **Consequences:** Extra DI surfaces; safer care core

### ADR-006 — Functions only when justified

* **Context:** Prefer Security Rules
* **Decision:** Functions for token/deletion/abuse cases
* **Consequences:** Smaller serverless surface

## 38. Limitations (Phase 0 / MVP)

* Design handoff incomplete (PawMinder naming residual in Figma URL — historical metadata only)
* Exact caregiver matrix and premium matrix open
* Lock-screen widgets may be deferred
* Advanced insights may be later-release
* No production Firebase configuration in Phase 0

## 39. Open decisions

1. MVP localization language set
2. Premium entitlement vs ads removal matrix
3. Caregiver role permission matrix details
4. Emergency Pass default fields + offline retention window
5. Lock-screen widgets MVP vs later
6. Occurrence materialization strategy specifics
7. Auto Backup include/exclude final list

## 40. Related documents

* [Software Requirements Specification](software-requirements-specification.md)
* [Test Guidelines and Quality Strategy](test-guidelines-and-quality-strategy.md)
* [Requirements Traceability Matrix](requirements-traceability-matrix.md)
* [Project Plan](project-plan.md)
* [Completion Report](completion-report.md) (template until release)
* ADR stubs: [docs/adrs/](adrs/)

## Document control

| Version | Date | Notes |
| --- | --- | --- |
| 0.1.0 | 2026-08-08 | Phase 0 architecture baseline |
| 0.1.0-repo | 2026-08-08 | HAP-13 repository documentation mirror |
