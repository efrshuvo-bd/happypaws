# HappyPaws Project Plan

| Field | Value |
| --- | --- |
| Document owner | Product Manager |
| Version | 0.2.0 |
| Status | Pre-Implementation |
| Last updated | 2026-08-08 |
| Parent | [HappyPaws — Pet Routine & Medication Tracker](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628) |
| Phase 0 Task | [HAP-140](https://auramislab.atlassian.net/browse/HAP-140) (Done) |
| Confluence | [5898656](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898656) |

> **Repository mirror** of Confluence planning authority (space HA). If this file and Confluence diverge, treat Confluence as authoritative until an explicit alignment Task updates both. Status remains **Pre-Implementation** — requirements are not marked implemented.

**Related repository docs:** [SRS](software-requirements-specification.md) · [Architecture](architecture.md) · [Test Guidelines](test-guidelines-and-quality-strategy.md) · [RTM](requirements-traceability-matrix.md) · [Completion Report](completion-report.md)

## 1. Vision and value proposition

HappyPaws helps pet caregivers manage multi-pet routines, medication schedules (including irregular and tapering plans), health metrics, diet tracking, caregiver collaboration, and emergency veterinary information — without diagnosing or prescribing treatment.

## 2. Approved Jira workflow

Authoritative workflow (do not create/rename/delete statuses):

`To Do → In Progress → In Review → Done`

| Status | Meaning |
| --- | --- |
| To Do | Planned, queued, blocked, or not started |
| In Progress | Active implementation or active validation |
| In Review | PR/evidence submitted and awaiting human review |
| Done | Merged, post-merge validation passed, evidence published, acceptance criteria satisfied |

### Testing and post-merge validation policy

Testing is a quality gate inside the workflow, not a separate Jira status:

1. Run required tests while the Task is **In Progress**.
2. Open the PR only after required pre-PR validation passes.
3. Move the Task to **In Review**.
4. After human merge, validate the merge on `origin/develop`.
5. Move the Task to **Done** only after post-merge validation passes.

Absence of Ready / Code Review / Testing statuses is intentional and not a blocker.

## 3. Business objectives

1. Ship a trustworthy Android MVP for cat/dog care coordination.
2. Differentiate on medication safety, tapering schedules, offline Emergency Vet Pass, and household sync.
3. Monetize via ads + Premium Subscription without compromising medical-safety or privacy.
4. Maintain audit-ready documentation and traceability from requirements through release.

## 4. Epic roadmap (exact 12 Epics + Quality Gates)

| # | Epic | Title | Quality Gate |
| --- | --- | --- | --- |
| 1 | [HAP-1](https://auramislab.atlassian.net/browse/HAP-1) | Product Foundation, Governance, and Design Handoff | [HAP-29](https://auramislab.atlassian.net/browse/HAP-29) |
| 2 | [HAP-2](https://auramislab.atlassian.net/browse/HAP-2) | Authentication, Account, and User Profile | [HAP-44](https://auramislab.atlassian.net/browse/HAP-44) |
| 3 | [HAP-3](https://auramislab.atlassian.net/browse/HAP-3) | Pet Profiles and Household Setup | [HAP-62](https://auramislab.atlassian.net/browse/HAP-62) |
| 4 | [HAP-4](https://auramislab.atlassian.net/browse/HAP-4) | Routines, Calendar, and Scheduling | [HAP-75](https://auramislab.atlassian.net/browse/HAP-75) |
| 5 | [HAP-5](https://auramislab.atlassian.net/browse/HAP-5) | Medication, Safety, Inventory, and Refill | [HAP-85](https://auramislab.atlassian.net/browse/HAP-85) |
| 6 | [HAP-6](https://auramislab.atlassian.net/browse/HAP-6) | Health Metrics, Symptoms, Diet, and Nutrition | [HAP-95](https://auramislab.atlassian.net/browse/HAP-95) |
| 7 | [HAP-7](https://auramislab.atlassian.net/browse/HAP-7) | Caregivers, Activity Feed, and Synchronization | [HAP-38](https://auramislab.atlassian.net/browse/HAP-38) |
| 8 | [HAP-8](https://auramislab.atlassian.net/browse/HAP-8) | Emergency Vet Pass and Digital Records | [HAP-61](https://auramislab.atlassian.net/browse/HAP-61) |
| 9 | [HAP-10](https://auramislab.atlassian.net/browse/HAP-10) | Insights, Reports, and Vet Export | [HAP-101](https://auramislab.atlassian.net/browse/HAP-101) |
| 10 | [HAP-9](https://auramislab.atlassian.net/browse/HAP-9) | Adaptive Android UI, Widgets, and Notifications | [HAP-116](https://auramislab.atlassian.net/browse/HAP-116) |
| 11 | [HAP-11](https://auramislab.atlassian.net/browse/HAP-11) | Monetization, Settings, Localization, and Privacy | [HAP-123](https://auramislab.atlassian.net/browse/HAP-123) |
| 12 | [HAP-12](https://auramislab.atlassian.net/browse/HAP-12) | System Quality, Documentation, and Release | [HAP-138](https://auramislab.atlassian.net/browse/HAP-138) |

Exactly one `quality-gate` Task per Epic. Authoritative QG keys only: HAP-29, HAP-44, HAP-62, HAP-75, HAP-85, HAP-95, HAP-38, HAP-61, HAP-101, HAP-116, HAP-123, HAP-138.

## 5. Completed Phase 0 work (not in executable queue)

| Key | Summary | Status |
| --- | --- | --- |
| [HAP-140](https://auramislab.atlassian.net/browse/HAP-140) | Initialize HappyPaws governance, Jira planning, and Confluence documentation | Done |

## 6. Canonical Task-level execution queue

Includes every incomplete implementation Task and all 12 Quality Gate Tasks. Epics are not queue items. HAP-13 is first. Each Epic QG follows that Epic’s implementation Tasks. Design-dependent UI remains behind HAP-19.

| Seq | Key | Summary | Epic |
| --- | --- | --- | --- |
| seq-001 | [HAP-13](https://auramislab.atlassian.net/browse/HAP-13) | Repository documentation initialization | HAP-1 |
| seq-002 | [HAP-14](https://auramislab.atlassian.net/browse/HAP-14) | Android/Gradle bootstrap | HAP-1 |
| seq-003 | [HAP-15](https://auramislab.atlassian.net/browse/HAP-15) | Architecture foundation | HAP-1 |
| seq-004 | [HAP-16](https://auramislab.atlassian.net/browse/HAP-16) | CI and PR template | HAP-1 |
| seq-005 | [HAP-18](https://auramislab.atlassian.net/browse/HAP-18) | Automated-test foundation | HAP-1 |
| seq-006 | [HAP-19](https://auramislab.atlassian.net/browse/HAP-19) | Validate Figma/design-handoff | HAP-1 |
| seq-007 | [HAP-21](https://auramislab.atlassian.net/browse/HAP-21) | Resolve PawMinder-to-HappyPaws naming | HAP-1 |
| seq-008 | [HAP-23](https://auramislab.atlassian.net/browse/HAP-23) | Theme/token foundation | HAP-1 |
| seq-009 | [HAP-25](https://auramislab.atlassian.net/browse/HAP-25) | Navigation shell | HAP-1 |
| seq-010 | [HAP-27](https://auramislab.atlassian.net/browse/HAP-27) | Security/privacy baseline | HAP-1 |
| seq-011 | [HAP-29](https://auramislab.atlassian.net/browse/HAP-29) | Epic 1 quality gate | HAP-1 |
| seq-012 | [HAP-31](https://auramislab.atlassian.net/browse/HAP-31) | Firebase Auth foundation | HAP-2 |
| seq-013 | [HAP-34](https://auramislab.atlassian.net/browse/HAP-34) | Google sign-in | HAP-2 |
| seq-014 | [HAP-35](https://auramislab.atlassian.net/browse/HAP-35) | Email/password signup/login | HAP-2 |
| seq-015 | [HAP-37](https://auramislab.atlassian.net/browse/HAP-37) | Verification/reset | HAP-2 |
| seq-016 | [HAP-39](https://auramislab.atlassian.net/browse/HAP-39) | Session lifecycle | HAP-2 |
| seq-017 | [HAP-40](https://auramislab.atlassian.net/browse/HAP-40) | User profile | HAP-2 |
| seq-018 | [HAP-41](https://auramislab.atlassian.net/browse/HAP-41) | Authentication states | HAP-2 |
| seq-019 | [HAP-42](https://auramislab.atlassian.net/browse/HAP-42) | Account data export | HAP-2 |
| seq-020 | [HAP-43](https://auramislab.atlassian.net/browse/HAP-43) | Re-authentication/account deletion | HAP-2 |
| seq-021 | [HAP-44](https://auramislab.atlassian.net/browse/HAP-44) | Epic 2 quality gate | HAP-2 |
| seq-022 | [HAP-48](https://auramislab.atlassian.net/browse/HAP-48) | Pet data model | HAP-3 |
| seq-023 | [HAP-50](https://auramislab.atlassian.net/browse/HAP-50) | Pet CRUD | HAP-3 |
| seq-024 | [HAP-51](https://auramislab.atlassian.net/browse/HAP-51) | Pet media | HAP-3 |
| seq-025 | [HAP-53](https://auramislab.atlassian.net/browse/HAP-53) | Allergies/conditions | HAP-3 |
| seq-026 | [HAP-54](https://auramislab.atlassian.net/browse/HAP-54) | Vet/insurance/microchip | HAP-3 |
| seq-027 | [HAP-55](https://auramislab.atlassian.net/browse/HAP-55) | Cat/dog presets | HAP-3 |
| seq-028 | [HAP-57](https://auramislab.atlassian.net/browse/HAP-57) | Multi-pet selection | HAP-3 |
| seq-029 | [HAP-58](https://auramislab.atlassian.net/browse/HAP-58) | Household model | HAP-3 |
| seq-030 | [HAP-60](https://auramislab.atlassian.net/browse/HAP-60) | Household task assignment | HAP-3 |
| seq-031 | [HAP-62](https://auramislab.atlassian.net/browse/HAP-62) | Epic 3 quality gate | HAP-3 |
| seq-032 | [HAP-63](https://auramislab.atlassian.net/browse/HAP-63) | Routine model/CRUD | HAP-4 |
| seq-033 | [HAP-65](https://auramislab.atlassian.net/browse/HAP-65) | Recurrence | HAP-4 |
| seq-034 | [HAP-66](https://auramislab.atlassian.net/browse/HAP-66) | Shared household tasks | HAP-4 |
| seq-035 | [HAP-68](https://auramislab.atlassian.net/browse/HAP-68) | Per-pet partial completion | HAP-4 |
| seq-036 | [HAP-71](https://auramislab.atlassian.net/browse/HAP-71) | Routine reminders | HAP-4 |
| seq-037 | [HAP-72](https://auramislab.atlassian.net/browse/HAP-72) | Calendar/agenda | HAP-4 |
| seq-038 | [HAP-73](https://auramislab.atlassian.net/browse/HAP-73) | Routine history | HAP-4 |
| seq-039 | [HAP-74](https://auramislab.atlassian.net/browse/HAP-74) | Routine timezone/travel handling | HAP-4 |
| seq-040 | [HAP-75](https://auramislab.atlassian.net/browse/HAP-75) | Epic 4 quality gate | HAP-4 |
| seq-041 | [HAP-76](https://auramislab.atlassian.net/browse/HAP-76) | Medication model/CRUD | HAP-5 |
| seq-042 | [HAP-77](https://auramislab.atlassian.net/browse/HAP-77) | Regular schedule | HAP-5 |
| seq-043 | [HAP-78](https://auramislab.atlassian.net/browse/HAP-78) | Irregular/tapering phases | HAP-5 |
| seq-044 | [HAP-79](https://auramislab.atlassian.net/browse/HAP-79) | Medication reminders | HAP-5 |
| seq-045 | [HAP-80](https://auramislab.atlassian.net/browse/HAP-80) | Outcomes/history | HAP-5 |
| seq-046 | [HAP-81](https://auramislab.atlassian.net/browse/HAP-81) | Duplicate-dose prevention | HAP-5 |
| seq-047 | [HAP-82](https://auramislab.atlassian.net/browse/HAP-82) | Safety notes/reactions | HAP-5 |
| seq-048 | [HAP-83](https://auramislab.atlassian.net/browse/HAP-83) | Inventory/refill forecast | HAP-5 |
| seq-049 | [HAP-84](https://auramislab.atlassian.net/browse/HAP-84) | Medication travel/DST | HAP-5 |
| seq-050 | [HAP-85](https://auramislab.atlassian.net/browse/HAP-85) | Epic 5 quality gate | HAP-5 |
| seq-051 | [HAP-86](https://auramislab.atlassian.net/browse/HAP-86) | Custom metrics | HAP-6 |
| seq-052 | [HAP-87](https://auramislab.atlassian.net/browse/HAP-87) | Symptom logging | HAP-6 |
| seq-053 | [HAP-88](https://auramislab.atlassian.net/browse/HAP-88) | Veterinary fecal score | HAP-6 |
| seq-054 | [HAP-89](https://auramislab.atlassian.net/browse/HAP-89) | Charts/accessible tables | HAP-6 |
| seq-055 | [HAP-90](https://auramislab.atlassian.net/browse/HAP-90) | Weight/water/glucose/temperature | HAP-6 |
| seq-056 | [HAP-91](https://auramislab.atlassian.net/browse/HAP-91) | Diet/food/meal/treat model | HAP-6 |
| seq-057 | [HAP-92](https://auramislab.atlassian.net/browse/HAP-92) | Calorie estimate | HAP-6 |
| seq-058 | [HAP-93](https://auramislab.atlassian.net/browse/HAP-93) | Vet-confirmed target | HAP-6 |
| seq-059 | [HAP-94](https://auramislab.atlassian.net/browse/HAP-94) | Health timeline | HAP-6 |
| seq-060 | [HAP-95](https://auramislab.atlassian.net/browse/HAP-95) | Epic 6 quality gate | HAP-6 |
| seq-061 | [HAP-17](https://auramislab.atlassian.net/browse/HAP-17) | Implement caregiver invitations | HAP-7 |
| seq-062 | [HAP-20](https://auramislab.atlassian.net/browse/HAP-20) | Implement caregiver roles and permissions | HAP-7 |
| seq-063 | [HAP-22](https://auramislab.atlassian.net/browse/HAP-22) | Implement caregiver task and care assignment | HAP-7 |
| seq-064 | [HAP-24](https://auramislab.atlassian.net/browse/HAP-24) | Implement household activity feed | HAP-7 |
| seq-065 | [HAP-26](https://auramislab.atlassian.net/browse/HAP-26) | Implement one-tap routine logging for caregivers | HAP-7 |
| seq-066 | [HAP-28](https://auramislab.atlassian.net/browse/HAP-28) | Implement offline write queue for caregiver actions | HAP-7 |
| seq-067 | [HAP-30](https://auramislab.atlassian.net/browse/HAP-30) | Implement caregiver data synchronization | HAP-7 |
| seq-068 | [HAP-32](https://auramislab.atlassian.net/browse/HAP-32) | Implement sync conflict resolution for caregiver actions | HAP-7 |
| seq-069 | [HAP-33](https://auramislab.atlassian.net/browse/HAP-33) | Implement pending duplicate detection for care logging | HAP-7 |
| seq-070 | [HAP-36](https://auramislab.atlassian.net/browse/HAP-36) | Implement caregiver audit history | HAP-7 |
| seq-071 | [HAP-38](https://auramislab.atlassian.net/browse/HAP-38) | Epic 7 quality gate — Caregivers, Activity Feed, and Synchronization | HAP-7 |
| seq-072 | [HAP-45](https://auramislab.atlassian.net/browse/HAP-45) | Implement Emergency Vet Pass | HAP-8 |
| seq-073 | [HAP-46](https://auramislab.atlassian.net/browse/HAP-46) | Implement medical document storage and viewer | HAP-8 |
| seq-074 | [HAP-47](https://auramislab.atlassian.net/browse/HAP-47) | Implement secure Emergency Pass link sharing | HAP-8 |
| seq-075 | [HAP-49](https://auramislab.atlassian.net/browse/HAP-49) | Implement Emergency Pass QR sharing | HAP-8 |
| seq-076 | [HAP-52](https://auramislab.atlassian.net/browse/HAP-52) | Implement share expiration, revocation, and access log | HAP-8 |
| seq-077 | [HAP-56](https://auramislab.atlassian.net/browse/HAP-56) | Implement encrypted offline Emergency Pass cache | HAP-8 |
| seq-078 | [HAP-96](https://auramislab.atlassian.net/browse/HAP-96) | Implement offline medical document selection for emergency cache | HAP-8 |
| seq-079 | [HAP-59](https://auramislab.atlassian.net/browse/HAP-59) | Implement printable, wallet, and wallpaper Emergency Pass export | HAP-8 |
| seq-080 | [HAP-61](https://auramislab.atlassian.net/browse/HAP-61) | Epic 8 quality gate — Emergency Vet Pass and Digital Records | HAP-8 |
| seq-081 | [HAP-64](https://auramislab.atlassian.net/browse/HAP-64) | Implement Insights dashboard | HAP-10 |
| seq-082 | [HAP-67](https://auramislab.atlassian.net/browse/HAP-67) | Implement adherence and routine analytics | HAP-10 |
| seq-083 | [HAP-69](https://auramislab.atlassian.net/browse/HAP-69) | Implement health and diet insight summaries | HAP-10 |
| seq-084 | [HAP-70](https://auramislab.atlassian.net/browse/HAP-70) | Implement report configuration for vet exports | HAP-10 |
| seq-085 | [HAP-97](https://auramislab.atlassian.net/browse/HAP-97) | Implement 7/30/90/custom insight date ranges | HAP-10 |
| seq-086 | [HAP-98](https://auramislab.atlassian.net/browse/HAP-98) | Implement PDF report export | HAP-10 |
| seq-087 | [HAP-106](https://auramislab.atlassian.net/browse/HAP-106) | Implement CSV report export | HAP-10 |
| seq-088 | [HAP-99](https://auramislab.atlassian.net/browse/HAP-99) | Implement secure report sharing | HAP-10 |
| seq-089 | [HAP-100](https://auramislab.atlassian.net/browse/HAP-100) | Implement export history for reports | HAP-10 |
| seq-090 | [HAP-101](https://auramislab.atlassian.net/browse/HAP-101) | Epic 10 quality gate — Insights, Reports, and Vet Export | HAP-10 |
| seq-091 | [HAP-102](https://auramislab.atlassian.net/browse/HAP-102) | Implement compact adaptive layouts | HAP-9 |
| seq-092 | [HAP-103](https://auramislab.atlassian.net/browse/HAP-103) | Implement tablet and foldable layouts | HAP-9 |
| seq-093 | [HAP-104](https://auramislab.atlassian.net/browse/HAP-104) | Implement adaptive navigation | HAP-9 |
| seq-094 | [HAP-105](https://auramislab.atlassian.net/browse/HAP-105) | Implement list-detail and supporting panes | HAP-9 |
| seq-095 | [HAP-107](https://auramislab.atlassian.net/browse/HAP-107) | Implement Material You Dynamic Color theming | HAP-9 |
| seq-096 | [HAP-108](https://auramislab.atlassian.net/browse/HAP-108) | Implement notification foundation | HAP-9 |
| seq-097 | [HAP-109](https://auramislab.atlassian.net/browse/HAP-109) | Implement Today care widget | HAP-9 |
| seq-098 | [HAP-110](https://auramislab.atlassian.net/browse/HAP-110) | Implement Quick Routine widget | HAP-9 |
| seq-099 | [HAP-111](https://auramislab.atlassian.net/browse/HAP-111) | Implement medication confirmation widget | HAP-9 |
| seq-100 | [HAP-112](https://auramislab.atlassian.net/browse/HAP-112) | Implement widget privacy and authentication gates | HAP-9 |
| seq-101 | [HAP-116](https://auramislab.atlassian.net/browse/HAP-116) | Epic 9 quality gate — Adaptive Android UI, Widgets, and Notifications | HAP-9 |
| seq-102 | [HAP-113](https://auramislab.atlassian.net/browse/HAP-113) | Implement application Settings hub | HAP-11 |
| seq-103 | [HAP-114](https://auramislab.atlassian.net/browse/HAP-114) | Implement localization and unit preferences | HAP-11 |
| seq-104 | [HAP-115](https://auramislab.atlassian.net/browse/HAP-115) | Implement notification preferences | HAP-11 |
| seq-105 | [HAP-117](https://auramislab.atlassian.net/browse/HAP-117) | Implement subscription and Play Billing | HAP-11 |
| seq-106 | [HAP-118](https://auramislab.atlassian.net/browse/HAP-118) | Implement restore purchase | HAP-11 |
| seq-107 | [HAP-119](https://auramislab.atlassian.net/browse/HAP-119) | Implement ads foundation | HAP-11 |
| seq-108 | [HAP-120](https://auramislab.atlassian.net/browse/HAP-120) | Implement privacy and ads consent flows | HAP-11 |
| seq-109 | [HAP-121](https://auramislab.atlassian.net/browse/HAP-121) | Implement Premium entitlements matrix | HAP-11 |
| seq-110 | [HAP-122](https://auramislab.atlassian.net/browse/HAP-122) | Implement privacy and data lifecycle controls | HAP-11 |
| seq-111 | [HAP-125](https://auramislab.atlassian.net/browse/HAP-125) | Implement account cleanup and deletion | HAP-11 |
| seq-112 | [HAP-123](https://auramislab.atlassian.net/browse/HAP-123) | Epic 11 quality gate — Monetization, Settings, Localization, and Privacy | HAP-11 |
| seq-113 | [HAP-124](https://auramislab.atlassian.net/browse/HAP-124) | Plan and establish full automated-test infrastructure | HAP-12 |
| seq-114 | [HAP-126](https://auramislab.atlassian.net/browse/HAP-126) | Plan and execute end-to-end care journeys tests | HAP-12 |
| seq-115 | [HAP-127](https://auramislab.atlassian.net/browse/HAP-127) | Conduct accessibility audit | HAP-12 |
| seq-116 | [HAP-128](https://auramislab.atlassian.net/browse/HAP-128) | Conduct security and privacy audit | HAP-12 |
| seq-117 | [HAP-129](https://auramislab.atlassian.net/browse/HAP-129) | Conduct medical-safety audit | HAP-12 |
| seq-118 | [HAP-130](https://auramislab.atlassian.net/browse/HAP-130) | Conduct performance validation | HAP-12 |
| seq-119 | [HAP-131](https://auramislab.atlassian.net/browse/HAP-131) | Validate offline and recovery scenarios | HAP-12 |
| seq-120 | [HAP-132](https://auramislab.atlassian.net/browse/HAP-132) | Validate localization and RTL | HAP-12 |
| seq-121 | [HAP-133](https://auramislab.atlassian.net/browse/HAP-133) | Execute full regression suite | HAP-12 |
| seq-122 | [HAP-134](https://auramislab.atlassian.net/browse/HAP-134) | Document internal-release process | HAP-12 |
| seq-123 | [HAP-135](https://auramislab.atlassian.net/browse/HAP-135) | Prepare release documentation package | HAP-12 |
| seq-124 | [HAP-136](https://auramislab.atlassian.net/browse/HAP-136) | Update HappyPaws Completion Report | HAP-12 |
| seq-125 | [HAP-137](https://auramislab.atlassian.net/browse/HAP-137) | Perform final requirements traceability audit | HAP-12 |
| seq-126 | [HAP-139](https://auramislab.atlassian.net/browse/HAP-139) | Internal release (planning only — remains To Do) | HAP-12 |
| seq-127 | [HAP-138](https://auramislab.atlassian.net/browse/HAP-138) | Final quality gate — System Quality, Documentation, and Release | HAP-12 |

Queue length: **127** incomplete Tasks (seq-001 … seq-127).

## 7. Dependency rules

* Use Blocks only for genuine execution dependencies.
* No mutual Blocks, self-links, duplicate Blocks, or cycles.
* HAP-19 blocks every Task requiring final approved UI/design assets (foundation UI + HAP-9 wave + HAP-116).
* Each Quality Gate is blocked by every applicable implementation Task in its Epic.
* A completed Quality Gate may gate the first implementation Task of the next Epic (sequential Epic delivery).
* HAP-13 remains the first implementation candidate.

## 8. Design-handoff gate

* HAP-19 owns approved design-handoff validation.
* Figma URL/title may still contain PawMinder; HappyPaws is the authoritative product name.
* Approved handoff ZIP remains Pending until verifiable approval evidence exists.
* Do not place blocked UI Tasks before approved design handoff.

## 9. Branch and PR rules

* Branch: `task/HAP-<n>-<kebab>` from latest `develop`
* Docs: `docs/HAP-<n>-...`; fixes: `fix/HAP-<n>-...`
* PR target: `develop`; conventional commits with Jira key; never self-merge
* Default branch: `main`; integration branch: `develop`
* Phase 0 exception: governance/planning Tasks need no Git branch/PR

## 10. Epic quality-gate policy

* Publish `docs/test-reports/<epic-key>-test-report.md` for each Epic QG.
* No Epic promotion with open Blocker/Critical defects.
* Medical-safety checks required for med/sync/emergency-impacting Epics.

## 11. Internal-release policy

* Internal release planning is HAP-139 (remains To Do in Phase 0).
* Cut `release/internal-vX.Y.Z` only after HAP-138 passes and human approval.
* Completion Report remains Template — Not Yet Completed until a real release.

## 12. Open decisions

1. Final localization language set for MVP
2. Premium entitlement matrix vs ads removal
3. Exact caregiver role permission matrix
4. Emergency Pass default fields and offline retention window
5. Whether lock-screen widgets are MVP or later

## 13. Authoritative links

* GitHub: https://github.com/efrshuvo-bd/happypaws
* Jira board: https://auramislab.atlassian.net/jira/software/projects/HAP/boards/72
* Confluence parent: https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628
* SRS: https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996592
* RTM: https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996570
* Figma: https://www.figma.com/design/64SUV67cE0iWkNh41LLMKH/HappyPawsContinue-the-existing-%E2%80%9CPawMinder-%E2%80%94-Pet-Routine---Medication-Tracker
* Application ID: `com.auramislab.happypaws`

## 14. Phase 0 completion evidence

* HAP-140 Done (Phase 0 initialization).
* Inventory: 12 Epics, 128 Tasks, 12 Quality Gates, 140 total issues.
* Approved four-status workflow preserved.
* No Android/Git/Firebase/production configuration performed in Phase 0 governance cleanup.

## Document control

| Version | Date | Notes |
| --- | --- | --- |
| 0.1.1 | 2026-08-08 | Earlier condensed Phase 0 note |
| 0.2.0 | 2026-08-08 | Restored workflow, exact queue, QG keys, dependency/design/branch policies |
