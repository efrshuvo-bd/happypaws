# HappyPaws Requirements Traceability Matrix

| Field | Value |
| --- | --- |
| Document owner | QA / Product / Engineering (AuraMis Lab) |
| Version | 0.2.0 |
| Status | Pre-Implementation |
| Last updated | 2026-08-08 |
| Parent | [HappyPaws — Pet Routine & Medication Tracker](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628) |
| Confluence | [5996570](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996570) |

> **Repository mirror** of Confluence planning authority (space HA). If this file and Confluence diverge, treat Confluence as authoritative until an explicit alignment Task updates both. Status remains **Pre-Implementation** — requirements are not marked implemented.

**Related repository docs:** [Project Plan](project-plan.md) · [SRS](software-requirements-specification.md) · [Architecture](architecture.md) · [Test Guidelines](test-guidelines-and-quality-strategy.md) · [Completion Report](completion-report.md)

## Purpose

Maps every SRS ID to owning Epic, primary implementation Task, Quality Gate Task, and planned test level. Evidence fields remain Pending until real implementation. Never fabricate test results, PR numbers, commit hashes, or release evidence.

## Workflow reminder

Jira statuses: `To Do → In Progress → In Review → Done`. Testing runs during In Progress (pre-PR) and again post-merge before Done.

## Matrix

| ID | Title | Epic | Primary Task | Additional Tasks | Quality Gate | Planned test level | Figma | PR/commit | Test evidence | Release | Status |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| FR-AUTH-001 | Email/password registration | HAP-2 | HAP-35 | — | HAP-44 | Integration/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-AUTH-002 | Google Sign-In | HAP-2 | HAP-34 | — | HAP-44 | Instrumented UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-AUTH-003 | Email/password sign-in | HAP-2 | HAP-35 | — | HAP-44 | Integration/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-AUTH-004 | Password reset | HAP-2 | HAP-37 | — | HAP-44 | Integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-AUTH-005 | Session persistence and sign-out | HAP-2 | HAP-39 | — | HAP-44 | Instrumented lifecycle | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ACCOUNT-001 | Create and edit profile | HAP-2 | HAP-40 | — | HAP-44 | UI/data | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ACCOUNT-002 | Account deletion request | HAP-2 | HAP-43 | — | HAP-44 | Privacy integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ACCOUNT-003 | Preferred timezone and locale | HAP-2 | HAP-40 | — | HAP-44 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PET-001 | Create pet profile | HAP-3 | HAP-50 | — | HAP-62 | UI/repository | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PET-002 | Edit pet profile | HAP-3 | HAP-50 | — | HAP-62 | UI/security | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PET-003 | Archive or delete pet | HAP-3 | HAP-50 | — | HAP-62 | UI/data policy | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PET-004 | Multi-pet switching | HAP-3 | HAP-57 | — | HAP-62 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-HOUSEHOLD-001 | Create household | HAP-3 | HAP-58 | — | HAP-62 | Integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-HOUSEHOLD-002 | Invite caregiver | HAP-7 | HAP-17 | — | HAP-38 | Integration/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-HOUSEHOLD-003 | Remove or leave household | HAP-7 | HAP-20 | — | HAP-38 | Security/integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ROUTINE-001 | Create routine | HAP-4 | HAP-63 | — | HAP-75 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ROUTINE-002 | Edit or pause routine | HAP-4 | HAP-63 | — | HAP-75 | UI/unit | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ROUTINE-003 | Complete routine occurrence | HAP-4 | HAP-68 | — | HAP-75 | UI/offline | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ROUTINE-004 | Routine calendar view | HAP-4 | HAP-72 | — | HAP-75 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-MED-001 | Create medication schedule | HAP-5 | HAP-76 | — | HAP-85 | Unit/UI medical-safety | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-MED-002 | Explicit tapering plan | HAP-5 | HAP-78 | — | HAP-85 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-MED-003 | Log medication dose | HAP-5 | HAP-80 | — | HAP-85 | UI/repository/sync | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-MED-004 | Duplicate-dose warning | HAP-5 | HAP-81 | — | HAP-85 | Unit/UI/sync | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-MED-005 | Dosage values never altered | HAP-5 | HAP-76 | — | HAP-85 | Unit medical-safety | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-MED-006 | Skip or missed dose recording | HAP-5 | HAP-80 | — | HAP-85 | UI/unit | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REFILL-001 | Track inventory quantity | HAP-5 | HAP-83 | — | HAP-85 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REFILL-002 | Low inventory alert | HAP-5 | HAP-83 | — | HAP-85 | Unit/notification | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REFILL-003 | Log refill | HAP-5 | HAP-83 | — | HAP-85 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REACTION-001 | Log adverse reaction | HAP-5 | HAP-82 | — | HAP-85 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REACTION-002 | Serious reaction guidance | HAP-8 | HAP-45 | — | HAP-61 | UI copy/tests | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REACTION-003 | Reaction history for vet prep | HAP-10 | HAP-69 | — | HAP-101 | UI/export | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-TIMEZONE-001 | Assign timezone context | HAP-4 | HAP-74 | — | HAP-75 | Unit | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-TIMEZONE-002 | Travel timezone change without silent shift | HAP-4 | HAP-74 | — | HAP-75 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-TIMEZONE-003 | DST handling | HAP-4 | HAP-74 | — | HAP-75 | Unit scheduling | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-METRIC-001 | Log health metric | HAP-6 | HAP-86 | — | HAP-95 | UI/unit | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-METRIC-002 | Log symptoms | HAP-6 | HAP-87 | — | HAP-95 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-METRIC-003 | Metric history visualization | HAP-10 | HAP-64 | — | HAP-101 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-DIET-001 | Log diet entry | HAP-6 | HAP-91 | — | HAP-95 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-DIET-002 | Diet restrictions notes | HAP-6 | HAP-91 | — | HAP-95 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-DIET-003 | Diet summary for reports | HAP-10 | HAP-69 | — | HAP-101 | Export | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-CAREGIVER-001 | Accept household invite | HAP-7 | HAP-17 | — | HAP-38 | Integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-CAREGIVER-002 | Role-based permissions | HAP-7 | HAP-20 | — | HAP-38 | Security/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-CAREGIVER-003 | Activity feed | HAP-7 | HAP-24 | — | HAP-38 | UI/sync | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-CAREGIVER-004 | Remove caregiver access | HAP-7 | HAP-20 | — | HAP-38 | Security | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SYNC-001 | Offline mutation queue | HAP-7 | HAP-28 | — | HAP-38 | Offline instrumentation | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SYNC-002 | Sync on reconnect | HAP-7 | HAP-30 | — | HAP-38 | Integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SYNC-003 | Conflict detection and resolution policy | HAP-7 | HAP-32 | — | HAP-38 | Sync conflict suite | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SYNC-004 | Caregiver concurrent dose conflict | HAP-7 | HAP-33 | — | HAP-38 | Multi-client sync | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SYNC-005 | Sync status visibility | HAP-7 | HAP-30 | — | HAP-38 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-EMERGENCY-001 | Compose Emergency Vet Pass | HAP-8 | HAP-45 | — | HAP-61 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-EMERGENCY-002 | Offline emergency cache | HAP-8 | HAP-56 | — | HAP-61 | Offline UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-EMERGENCY-003 | Secure emergency share link | HAP-8 | HAP-47 | — | HAP-61 | Security/integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-EMERGENCY-004 | Emergency QR access | HAP-8 | HAP-49 | — | HAP-61 | Security/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-EMERGENCY-005 | Update emergency contacts and vet | HAP-8 | HAP-45 | — | HAP-61 | UI/offline | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-DOCUMENT-001 | Upload pet document | HAP-8 | HAP-46 | — | HAP-61 | Integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-DOCUMENT-002 | View or download document | HAP-8 | HAP-46 | — | HAP-61 | Security/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-DOCUMENT-003 | Delete document | HAP-8 | HAP-46 | — | HAP-61 | Integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SHARE-001 | Secure share link with expiry | HAP-8 | HAP-47 | — | HAP-61 | Security | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SHARE-002 | Revoke share link | HAP-8 | HAP-52 | — | HAP-61 | Security | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SHARE-003 | Scope-limited share content | HAP-8 | HAP-47 | — | HAP-61 | Security | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REPORT-001 | Generate PDF vet summary | HAP-10 | HAP-98 | — | HAP-101 | Export tests | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REPORT-002 | Export CSV data | HAP-10 | HAP-106 | — | HAP-101 | Unit export | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-REPORT-003 | Insights overview | HAP-10 | HAP-64 | — | HAP-101 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-WIDGET-001 | Today tasks widget | HAP-9 | HAP-109 | — | HAP-116 | Widget instrumentation | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-WIDGET-002 | Medication due widget | HAP-9 | HAP-111 | — | HAP-116 | Widget tests | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-WIDGET-003 | Widget privacy constraints | HAP-9 | HAP-112 | — | HAP-116 | Privacy/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-NOTIFICATION-001 | Schedule local reminders | HAP-9 | HAP-108 | — | HAP-116 | Device notification | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-NOTIFICATION-002 | Push notification delivery | HAP-9 | HAP-108 | — | HAP-116 | FCM integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-NOTIFICATION-003 | Notification actions | HAP-9 | HAP-108 | — | HAP-116 | Instrumented notification | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-NOTIFICATION-004 | Quiet hours and preferences | HAP-11 | HAP-115 | — | HAP-123 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SETTINGS-001 | App preferences | HAP-11 | HAP-113 | — | HAP-123 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SETTINGS-002 | Notification preference center | HAP-11 | HAP-115 | — | HAP-123 | UI/unit | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-SETTINGS-003 | Theme and Dynamic Color | HAP-9 | HAP-107 | — | HAP-116 | UI a11y | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ADS-001 | Show ads for free tier | HAP-11 | HAP-119 | — | HAP-123 | UI/boundary | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ADS-002 | No ads on critical medical/emergency surfaces | HAP-11 | HAP-119 | — | HAP-123 | UI denylist | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-ADS-003 | Ads consent and privacy | HAP-11 | HAP-120 | — | HAP-123 | Privacy checklist | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-BILLING-001 | Subscribe to Premium | HAP-11 | HAP-117 | — | HAP-123 | Play Billing tests | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-BILLING-002 | Restore purchases | HAP-11 | HAP-118 | — | HAP-123 | Billing tests | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-BILLING-003 | Premium entitlement unlock | HAP-11 | HAP-121 | — | HAP-123 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PRIVACY-001 | Privacy policy and terms access | HAP-11 | HAP-122 | — | HAP-123 | UI | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PRIVACY-002 | Export personal data | HAP-11 | HAP-122 | — | HAP-123 | Privacy/export | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PRIVACY-003 | Delete account and data | HAP-11 | HAP-125 | — | HAP-123 | Privacy integration | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PRIVACY-004 | Consent management | HAP-11 | HAP-120 | — | HAP-123 | Privacy checklist | Pending Handoff | Pending | Pending | Pending | Planned |
| FR-PRIVACY-005 | Minimize PII in logs and analytics | HAP-11 | HAP-122 | — | HAP-123 | Static/instrumentation | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-SEC-001 | Secure token storage | HAP-1 | HAP-27 | — | HAP-29 | Security review/tests | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-SEC-002 | App Check for backend | HAP-1 | HAP-27 | — | HAP-29 | Security config review | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-SEC-003 | TLS transport | HAP-12 | HAP-128 | — | HAP-138 | Config/traffic | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-PRIVACY-001 | Data classification | HAP-11 | HAP-122 | — | HAP-123 | Design review | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-PRIVACY-002 | Least privilege access | HAP-11 | HAP-122 | — | HAP-123 | Rules tests | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-PRIVACY-003 | Share link retention limits | HAP-11 | HAP-122 | — | HAP-123 | Integration | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-PERF-001 | Cold start target | HAP-12 | HAP-130 | — | HAP-138 | Perf lab | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-PERF-002 | List scroll performance | HAP-12 | HAP-130 | — | HAP-138 | Perf tests | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-PERF-003 | Sync backlog processing | HAP-12 | HAP-131 | — | HAP-138 | Offline stress | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-ACCESS-001 | TalkBack support | HAP-12 | HAP-127 | — | HAP-138 | Manual a11y | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-ACCESS-002 | Font scaling | HAP-9 | HAP-102 | — | HAP-116 | UI a11y | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-ACCESS-003 | Color contrast | HAP-9 | HAP-107 | — | HAP-116 | A11y scanner | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-RELIABILITY-001 | Crash-free sessions target | HAP-12 | HAP-130 | — | HAP-138 | Crash/soak | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-RELIABILITY-002 | Idempotent dose logging | HAP-5 | HAP-81 | — | HAP-85 | Unit/sync | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-RELIABILITY-003 | Graceful offline degradation | HAP-7 | HAP-28 | — | HAP-38 | Offline | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-OFFLINE-001 | Critical reads offline | HAP-7 | HAP-28 | — | HAP-38 | Offline suite | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-OFFLINE-002 | Queue durability | HAP-7 | HAP-28 | — | HAP-38 | Instrumentation | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-OFFLINE-003 | Conflict surfacing UX | HAP-7 | HAP-32 | — | HAP-38 | UI/sync | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-LOCALIZATION-001 | Externalized strings | HAP-11 | HAP-114 | — | HAP-123 | Lint | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-LOCALIZATION-002 | RTL layout support | HAP-11 | HAP-114 | — | HAP-123 | UI RTL | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-LOCALIZATION-003 | Locale-aware dates and numbers | HAP-11 | HAP-114 | — | HAP-123 | Unit/UI | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-MAINTAINABILITY-001 | Modular architecture | HAP-1 | HAP-15 | — | HAP-29 | Architecture review | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-MAINTAINABILITY-002 | Lint and CI quality | HAP-12 | HAP-124 | — | HAP-138 | CI | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-MAINTAINABILITY-003 | Documented ADRs | HAP-1 | HAP-15 | — | HAP-29 | Doc review | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-OBSERVABILITY-001 | Structured non-PII logging | HAP-12 | HAP-124 | — | HAP-138 | Log review | Pending Handoff | Pending | Pending | Pending | Planned |
| NFR-OBSERVABILITY-002 | Crash reporting | HAP-12 | HAP-124 | — | HAP-138 | Crash drill | Pending Handoff | Pending | Pending | Pending | Planned |

| NFR-OBSERVABILITY-003 | Sync and error metrics | HAP-12 | HAP-124 | — | HAP-138 | Metrics review | Pending Handoff | Pending | Pending | Pending | Planned |
## Counts

* Unique requirement IDs mapped: **111**
* Every row has a real implementation Task owner (not Epic-only).
* Evidence columns remain Pending until implementation.

## Document control

| Version | Date | Notes |
| --- | --- | --- |
| 0.1.0 | 2026-08-08 | Phase 0 baseline matrix |
| 0.2.0 | 2026-08-08 | Populated Epic/Task/QG ownership and planned test levels |
