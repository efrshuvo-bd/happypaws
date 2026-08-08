# HappyPaws Test Guidelines and Quality Strategy

| Field | Value |
| --- | --- |
| Document owner | QA Lead / Engineering (AuraMis Lab) |
| Version | 0.1.1 |
| Status | Pre-Implementation |
| Last updated | 2026-08-08 |
| Parent | [HappyPaws — Pet Routine & Medication Tracker](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628) |
| Confluence | [5898707](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898707) |

> **Repository mirror** of Confluence planning authority (space HA). If this file and Confluence diverge, treat Confluence as authoritative until an explicit alignment Task updates both. Status remains **Pre-Implementation** — requirements are not marked implemented.

**Related repository docs:** [Project Plan](project-plan.md) · [SRS](software-requirements-specification.md) · [Architecture](architecture.md) · [RTM](requirements-traceability-matrix.md) · [Completion Report](completion-report.md)

## 1. Purpose

Define how HappyPaws verifies requirements safely and auditable across Epics HAP-1–HAP-12. Evidence must be real; **never fabricate** test results, coverage numbers, or device logs.

## 1.1 Workflow and testing gates

Jira statuses: `To Do → In Progress → In Review → Done`.

Testing is a quality gate inside this workflow, not a separate status:

1. Run required tests while the Task is **In Progress** (pre-PR).
2. Open the PR only after required pre-PR validation passes; move to **In Review**.
3. After human merge to `develop`, complete post-merge validation before **Done**.

## 2. Quality objectives

1. Protect medical-safety invariants (dose fidelity, duplicate-dose, timezone non-silent shifts).
2. Prove offline/sync and caregiver conflict behavior.
3. Ensure accessibility, RTL, and adaptive UI readiness.
4. Validate privacy/security for shares, widgets, deletion, and logging redaction.
5. Gate merges and Epic completion on documented evidence.

## 3. Test levels

| Level | Intent | Typical owners |
| --- | --- | --- |
| Unit | Domain rules, scheduling, conflicts, mappers | Eng |
| Integration | Room, repositories, fake Firebase, workers | Eng |
| UI instrumentation | Compose journeys, a11y hooks | Eng/QA |
| Manual exploratory | UX, edge devices, judgment calls | QA |
| Security/privacy review | Rules, shares, redaction | Security + Eng |
| Performance lab | Start, scroll, sync backlog | Eng/QA |
| Acceptance | SRS acceptance criteria mapping | QA + Product |

## 4. Environments

| Environment | Use |
| --- | --- |
| Local unit | JVM tests, no device |
| Emulator lab | API matrix smoke |
| Physical devices | Notifications, widgets, OEM quirks |
| Firebase emulators / dev project | Auth/Firestore/Storage/FCM (post Phase 0 config) |
| Play Billing test tracks | Subscriptions |
| Ads test units | Non-prod ad rendering |

Phase 0: no production Firebase/AdMob/Billing credentials configured in docs or repo.

## 5. Test data policy

* Use synthetic pets/accounts only in shared environments
* Never copy real veterinary documents into test clouds
* Prefer deterministic fixtures for medical-safety suites
* Mask any accidental PII in attached evidence
* Reset emulator data between conflict scenarios

## 6. Entry and exit criteria

### Task entry (DoR alignment)

* SRS IDs linked
* Acceptance criteria testable
* Design reference available for UI Tasks (or explicit waiver)
* Dependencies noted

### Task exit

* Acceptance criteria evidenced
* Automated tests added/updated where applicable
* No open blocker/critical defects for the Task scope
* Traceability fields updated (story/test pending→real IDs when created)

### Epic gate entry

* All Epic Tasks Done or explicitly deferred with Product approval
* Regression suite for Epic scope executed

### Epic gate exit

* `docs/test-reports/<epic-key>-test-report.md` published
* Confluence/Jira evidence linked
* No unresolved Blocker/Critical defects
* Medical-safety checks pass for med/sync/emergency-impacting Epics

## 7. Pass / fail rules

* **Pass:** All Must acceptance criteria met; no Blocker/Critical opens; required suite green
* **Fail:** Any Blocker/Critical; Must criteria unmet; medical-safety regression; missing mandatory evidence
* **Conditional pass:** Product-approved Could/Should deferrals documented; not allowed for Must medical-safety

## 8. Severity model

| Severity | Definition | Gate impact |
| --- | --- | --- |
| Blocker | Data loss, security breach, double-dose silent accept, crash on primary care path | Fails gate |
| Critical | Major feature broken; timezone silent shift; emergency offline missing when cached | Fails gate |
| Major | Significant UX/functional issue with workaround | May fail Epic if cumulative risk high |
| Minor | Limited impact | Track; do not block unless SLA says so |
| Trivial | Cosmetic | Backlog |

## 9. Blockers

Examples that always block Epic promotion:

* Silent dosage alteration
* Duplicate-dose warning bypass on primary path
* Cross-household data leak
* Share link works after revoke/expiry
* Emergency cache unavailable despite prior successful cache
* Ads on denylisted medical/emergency surfaces
* Account deletion leaving accessible pet data

## 10. Evidence requirements

For each Epic gate report include:

* Scope & builds tested (version/hash when available)
* Suites executed + results
* Device/API matrix coverage
* Defects list with severities
* Medical-safety checklist outcome (if applicable)
* A11y/RTL notes (if applicable)
* Links to PRs (no fabricated SHAs)
* Explicit **Not executed** for skipped areas

Attachments: screenshots, logcats (redacted), traces — only if real.

## 11. Device / API / screen matrices

### API matrix (minimum intent)

| API | Priority |
| --- | --- |
| 26 | Must (minSdk) |
| 29/31 | Should |
| 33/34 | Should |
| 35/36 | Must (target generation) |

### Form factors

| Class | Priority |
| --- | --- |
| Phone compact | Must |
| Phone expanded / large font | Must for med/emergency |
| Tablet / width medium-expanded | Should (adaptive) |
| RTL locale | Must for primary flows |

### Screen priority for regression

Auth, Today, Med detail/log, Duplicate-dose dialog, Timezone change, Caregiver invite, Offline indicators, Emergency Vet Pass, Share/QR, Settings/privacy/delete, Widget config, Ads-allowed vs denylist screens.

## 12. Accessibility testing

* TalkBack: auth, today, dose log, emergency pass
* Focus order and content descriptions
* Font scale large/largest — dose text fully readable
* Contrast and non-color-only status
* Touch target sanity on primary CTAs

## 13. RTL testing

* Pseudo-RTL or real RTL locale
* Mirroring of navigation and lists
* Dose strings remain correct characters/order
* QR/share screens usable

## 14. Offline testing

* Airplane mode: today/med due/emergency cache reads
* Offline dose log + process death + still queued
* Reconnect sync success
* Partial failure retry
* Online-only actions show clear disabled reasons

## 15. Timezone testing

* Change TZ with keep wall-clock vs convert preview
* Cancel leaves schedules unchanged
* DST spring-forward/fall-back vectors in unit suite
* Notification reschedule after confirmed change

## 16. Notifications testing

* Permission denied path
* Local reminder fire for routine/med
* Action: snooze / mark given (with duplicate-dose checks)
* Quiet hours suppression
* FCM path in dev (when configured)

## 17. Widgets testing

* Add/resume/refresh widget
* Matches today/med due within refresh policy
* Signed-out state
* Privacy redaction (no documents/sensitive notes)

## 18. Firebase testing

* Auth sign-in/out/session restore (emulator/dev)
* Rules: cross-household deny
* Storage unauthorized deny
* App Check expected in deployed envs (config review)
* Functions only for justified flows — contract tests when introduced
* **No secrets in evidence dumps**

## 19. Security testing

* Token storage checks
* Cleartext traffic disallowed
* Share expiry/revoke
* Scope filtering
* Log/analytics redaction review
* Widget/share surface review

## 20. Medical-safety testing (mandatory suite)

| Case | Expect |
| --- | --- |
| Dose round-trip local/remote | Exact string equality |
| Taper without auto steps | Only explicit steps schedule |
| Duplicate-dose same caregiver | Warning before commit |
| Duplicate-dose two caregivers offline | Conflict evidence retained |
| Serious reaction copy | Seek care; no treatment instructions |
| Inventory math | Does not rewrite dose text |
| TZ change cancel | No schedule mutation |

## 21. Caregiver conflict testing

* Concurrent profile edits
* Concurrent routine definition edits
* Dual offline completions
* Dual offline dose givens → conflict UX
* Removed caregiver loses access after refresh

## 22. Duplicate-dose suite

Unit + UI + sync multi-client. Covers window edges, skipped vs given, notification mark-given action, acknowledgement flag persistence.

## 23. Emergency cache suite

* Cache after save
* Airplane mode render + last-updated
* Update then refresh cache
* Never-cached offline empty state
* Share/QR independent from local cache validity where applicable

## 24. QR and secure link suite

* Create/expiry/revoke
* Scope emergency-only vs broader
* QR resolve success/fail
* Tampered token deny

## 25. PDF / CSV suite

* Section selection
* Verbatim doses in outputs
* Empty selection blocked
* FileProvider share opens

## 26. Ads / billing suite

* Free tier ads on allowed screens only
* Denylist screens have zero ad views
* Ad load failure does not break care UX
* Premium purchase/restore/entitlement (test tracks)
* Consent denial path

## 27. Deletion suite

* Account delete happy path
* Sole-owner blocker guidance
* Pet archive/delete
* Document delete consistency
* Post-delete access denied + local cache cleared

## 28. Performance suite

* Cold start budget check on reference device
* Large history scroll
* Sync backlog stress without ANR
* Record methods/results in Epic 12 / release reports

## 29. Lint and static quality

* Detekt/Android Lint (as configured in foundation)
* i18n hardcoded string checks where enforced
* Dependency/module boundary checks in review
* CI required checks documented in HAP-1/HAP-12

## 30. Suites list (canonical names)

1. `unit-domain-scheduling`
2. `unit-medical-safety`
3. `unit-timezone-dst`
4. `unit-conflict-engine`
5. `unit-entitlements`
6. `integration-room-repos`
7. `integration-sync-queue`
8. `integration-firebase-rules` (when available)
9. `android-ui-primary-journeys`
10. `android-ui-duplicate-dose`
11. `android-offline-critical`
12. `android-emergency-cache`
13. `android-notifications`
14. `android-widgets`
15. `android-a11y-rtl`
16. `manual-security-privacy`
17. `manual-ads-billing`
18. `manual-perf-lab`
19. `export-pdf-csv`
20. `deletion-privacy`

## 31. Epic gate publishing rule

Each Epic quality-gate Task **must** publish:

`docs/test-reports/<epic-key>-test-report.md`

Examples: `docs/test-reports/HAP-5-test-report.md`, `docs/test-reports/HAP-7-test-report.md`.

Minimum report sections: Summary, Build, Scope, Suites, Matrix, Defects, Medical-safety, A11y, Security/Privacy, Risks/Deferral, Sign-off, Links.

Confluence Completion Report remains a **template** until a real release; Epic reports are the incremental evidence trail.

## 32. Traceability expectations

* Map test cases to SRS IDs in the Traceability Matrix when tests are created
* Until then, Test case/suite column stays `Pending`
* Verification method from SRS guides suite selection
* Do not mark Status Done without evidence links

## 33. Responsibilities

| Role | Responsibility |
| --- | --- |
| Engineering | Automated tests, fixes, CI |
| QA | Plans, exploratory, gate evidence quality |
| Product | Deferral approvals, acceptance |
| Security | Rules/share/privacy reviews |
| Design | Handoff defects affecting testability |

## 34. Open testing decisions

1. Exact cold-start numeric budget
2. Final physical device list for release
3. Whether lock-screen widgets enter MVP regression
4. Firebase emulator vs shared dev project for CI

## Document control

| Version | Date | Notes |
| --- | --- | --- |
| 0.1.0 | 2026-08-08 | Phase 0 test strategy baseline |
| 0.1.1 | 2026-08-08 | Align testing to In Progress gate + post-merge before Done |
