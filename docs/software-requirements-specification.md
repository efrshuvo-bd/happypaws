# HappyPaws Software Requirements Specification

| Field | Value |
| --- | --- |
| Document owner | Product / Engineering (AuraMis Lab) |
| Version | 0.1.0 |
| Status | Pre-Implementation |
| Last updated | 2026-08-08 |
| Parent | [HappyPaws — Pet Routine & Medication Tracker](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628) |
| Confluence | [5996592](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996592) |

> **Repository mirror** of Confluence planning authority (space HA). If this file and Confluence diverge, treat Confluence as authoritative until an explicit alignment Task updates both. Status remains **Pre-Implementation** — requirements are not marked implemented.

**Related repository docs:** [Project Plan](project-plan.md) · [Architecture](architecture.md) · [Test Guidelines](test-guidelines-and-quality-strategy.md) · [RTM](requirements-traceability-matrix.md) · [Completion Report](completion-report.md)

## 1. Introduction

HappyPaws (com.auramislab.happypaws) is an Android pet routine and medication tracker. It records caregiver-managed information and does **not** diagnose, prescribe, calculate, round, or alter dosages. Phase 0: **Pre-Implementation**. Design references: **Pending Handoff**. No secrets/Firebase IDs in this document.

### 1.1–1.4 Scope summary

**In scope:** auth, account, pets/household, routines, medication (incl. explicit tapering), duplicate-dose, inventory/refill, reactions, timezone-safe scheduling, metrics, diet, caregivers, offline/sync, Emergency Vet Pass, documents, secure share/QR, PDF/CSV, widgets, notifications, settings, ads+Premium, privacy/deletion, NFRs.    
**Out of scope:** diagnosis/treatment engines, telemedicine, non-Android MVP, Phase 0 Firebase/production credential configuration.    
**References:** Parent hub, Project Plan (5898656), Jira HAP, GitHub efrshuvo-bd/happypaws, Figma Pending Handoff.

## 2. Personas, roles, journeys

**Personas:** Primary Owner (Maya), Co-caregiver (Alex), Traveler (Sam), Vet-visit Preparer (Jordan).    
**Roles:** Owner; Caregiver Editor; Caregiver Viewer (matrix open); Signed-out visitor.    
**Journeys:** Onboard; Daily care; Tapering meds; Co-care; Travel TZ; Emergency; Vet prep export; Privacy exit.

## 3. System context

Android Compose app → Room/DataStore/WorkManager → Firebase Auth/Firestore/Storage/FCM/App Check; optional justified Functions; isolated Play Billing/Ads.

## 4. Data requirements

Entities: User, Household, Pet, Routine, Medication (verbatim dose text), DoseEvent, Inventory, Reaction, Metric/Diet, EmergencyPass, Document, ShareLink, SyncOp, Entitlement. Dosage fields never silently altered.

## 5. Cross-cutting rules

Medical-safety: record not diagnose; never alter doses; explicit tapering; non-silent TZ; serious reaction → seek vet care; duplicate-dose warn; no ads on critical surfaces.    
Also: accessibility, localization, offline/sync, notifications, security/privacy, monetization, compliance posture.    
**Open decisions:** MVP locales; premium vs ads matrix; caregiver permissions; emergency defaults/retention; lock-screen widgets; Jira status model.

## 6. Requirement format

Each requirement includes ID, Title, Description, Actor, Preconditions, Trigger, Main behavior, Alternate/error, Acceptance criteria, Priority, Related Epic, Design reference=Pending Handoff, Verification method.

## 7–8. Requirements catalog

### FR-AUTH

#### FR-AUTH-001 — Email/password registration

ID=FR-AUTH-001; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=UI + integration tests against Auth emulator/dev; negative cases    
Desc: New users can create an account with email and password meeting defined strength rules.    
Actor: Prospective user | Pre: App installed; network available; user not signed in | Trigger: User submits registration form    
Main: Validate inputs; create auth account; establish session; route to profile/onboarding    
Alt/Error: Invalid email, weak password, existing account, network failure → clear recoverable errors; no partial signed-in state with incomplete auth    
AC: Valid registration yields authenticated session; duplicate email blocked; errors are user-visible and non-leaky

#### FR-AUTH-002 — Google Sign-In

ID=FR-AUTH-002; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=Instrumented UI + Auth integration    
Desc: Users can authenticate using Google Sign-In.    
Actor: Prospective or returning user | Pre: Google Play Services available; network available | Trigger: User selects Google Sign-In    
Main: Complete Google auth; link/create HappyPaws account; establish session    
Alt/Error: Cancelled flow, Play Services missing, token failure → return to auth with message    
AC: Successful Google auth lands in app authenticated; cancel leaves user signed out

#### FR-AUTH-003 — Email/password sign-in

ID=FR-AUTH-003; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=UI + integration    
Desc: Existing users can sign in with email and password.    
Actor: Returning user | Pre: Account exists | Trigger: User submits credentials    
Main: Authenticate; restore session; load authorized households/pets    
Alt/Error: Wrong credentials, disabled account, network error → generic auth failure messaging    
AC: Correct credentials sign in; incorrect credentials do not reveal whether email exists beyond platform norms

#### FR-AUTH-004 — Password reset

ID=FR-AUTH-004; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=Integration + manual Auth provider check    
Desc: Users can request a password reset for email/password accounts.    
Actor: User | Pre: Email/password account exists or unknown | Trigger: User requests reset    
Main: Initiate platform reset email flow; confirm request accepted UI    
Alt/Error: Network failure → retry guidance; do not leak account existence beyond Auth provider behavior    
AC: Reset request completes without crash; user sees next-step guidance

#### FR-AUTH-005 — Session persistence and sign-out

ID=FR-AUTH-005; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=Instrumented lifecycle tests    
Desc: Authenticated sessions persist across app restarts until sign-out or revocation.    
Actor: Authenticated user / System | Pre: User signed in | Trigger: App restart or user selects sign-out    
Main: Restore secure session on launch; sign-out clears local session tokens and private caches per policy    
Alt/Error: Revoked/expired session → force re-auth    
AC: Cold start keeps valid session; sign-out requires re-auth for pet data

### FR-ACCOUNT

#### FR-ACCOUNT-001 — Create and edit profile

ID=FR-ACCOUNT-001; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=UI + data layer tests    
Desc: Users can set display name and basic profile fields after auth.    
Actor: Authenticated user | Pre: Signed in | Trigger: Open profile settings; save    
Main: Persist profile fields; reflect in UI    
Alt/Error: Validation/network failure → retain prior values; show error    
AC: Saved profile reloads after restart/sync

#### FR-ACCOUNT-002 — Account deletion request

ID=FR-ACCOUNT-002; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=Integration + privacy test checklist    
Desc: Users can request deletion of account and associated personal data.    
Actor: Owner | Pre: Signed in; confirmation challenge completed | Trigger: User confirms delete account    
Main: Initiate deletion workflow; revoke sessions; remove/queue removal of user-owned data per privacy design    
Alt/Error: Active shared household ownership conflicts → guided transfer/leave flow before delete    
AC: Post-delete, prior credentials cannot access pet data; deletion status is auditable in logs without PII dump

#### FR-ACCOUNT-003 — Preferred timezone and locale

ID=FR-ACCOUNT-003; Priority=Must; Epic=HAP-2; Design=Pending Handoff; Verify=Unit + UI    
Desc: Users can set preferred timezone and locale influencing formatting and schedule UX.    
Actor: Authenticated user | Pre: Signed in | Trigger: Change timezone/locale preference    
Main: Store preference; apply formatting; if timezone change affects schedules, invoke timezone policy UX (see FR-TIMEZONE)    
Alt/Error: Unsupported locale falls back safely    
AC: Preference persists; schedule-impacting TZ changes are not silent

### FR-PET

#### FR-PET-001 — Create pet profile

ID=FR-PET-001; Priority=Must; Epic=HAP-3; Design=Pending Handoff; Verify=UI + Room/Firestore repository tests    
Desc: Users can create a pet with species (cat/dog MVP), name, and core attributes.    
Actor: Owner / authorized caregiver | Pre: Authenticated; household available or created | Trigger: Submit create-pet form    
Main: Validate; persist pet; appear in pet switcher    
Alt/Error: Missing required fields / storage failure → block save with errors    
AC: Created pet is selectable and survives restart

#### FR-PET-002 — Edit pet profile

ID=FR-PET-002; Priority=Must; Epic=HAP-3; Design=Pending Handoff; Verify=UI + security rules tests    
Desc: Authorized users can update pet attributes and photo reference.    
Actor: Owner / Editor | Pre: Pet exists; user authorized | Trigger: Save edits    
Main: Update fields; sync when online    
Alt/Error: Unauthorized → deny; conflict → surface sync conflict    
AC: Edits visible to household after sync; unauthorized users cannot edit

#### FR-PET-003 — Archive or delete pet

ID=FR-PET-003; Priority=Should; Epic=HAP-3; Design=Pending Handoff; Verify=UI + data policy tests    
Desc: Owners can archive or delete a pet with confirmation and data-retention policy.    
Actor: Owner | Pre: Pet exists | Trigger: Confirm archive/delete    
Main: Soft-archive or delete per policy; remove from active switcher; retain legal/audit minimum only as designed    
Alt/Error: Cancel confirmation → no change    
AC: Archived/deleted pet no longer appears in active care flows

#### FR-PET-004 — Multi-pet switching

ID=FR-PET-004; Priority=Must; Epic=HAP-3; Design=Pending Handoff; Verify=UI tests    
Desc: Users with multiple pets can switch active pet context across care screens.    
Actor: Caregiver | Pre: ≥2 active pets | Trigger: Select pet in switcher    
Main: Update active pet context; reload routines/meds/metrics for that pet    
Alt/Error: Pet unavailable → fallback message    
AC: Context switch updates dependent screens without cross-pet data bleed

### FR-HOUSEHOLD

#### FR-HOUSEHOLD-001 — Create household

ID=FR-HOUSEHOLD-001; Priority=Must; Epic=HAP-3; Design=Pending Handoff; Verify=Integration    
Desc: Owner can create a household as the collaboration boundary for pets.    
Actor: Owner | Pre: Signed in | Trigger: Complete household setup    
Main: Create household; assign owner role; attach created pets    
Alt/Error: Failure → retry; no orphaned inconsistent state    
AC: Household ID associated to owner and pets

#### FR-HOUSEHOLD-002 — Invite caregiver

ID=FR-HOUSEHOLD-002; Priority=Must; Epic=HAP-7; Design=Pending Handoff; Verify=Integration + UI    
Desc: Owner can invite a caregiver with a role.    
Actor: Owner | Pre: Household exists | Trigger: Send invite    
Main: Create invite; deliver link/code; pending until accepted    
Alt/Error: Expired/invalid invite → rejection message    
AC: Invite can be accepted once; role stored

#### FR-HOUSEHOLD-003 — Remove or leave household

ID=FR-HOUSEHOLD-003; Priority=Must; Epic=HAP-7; Design=Pending Handoff; Verify=Security + integration    
Desc: Owner can remove a caregiver; caregiver can leave; owner leave requires transfer/dissolve rules.    
Actor: Owner / Caregiver | Pre: Membership exists | Trigger: Remove/leave action confirmed    
Main: Update membership; revoke access tokens/shares as needed    
Alt/Error: Sole owner leave blocked until transfer/dissolve    
AC: Removed user loses pet data access on next sync/session refresh

### FR-ROUTINE

#### FR-ROUTINE-001 — Create routine

ID=FR-ROUTINE-001; Priority=Must; Epic=HAP-4; Design=Pending Handoff; Verify=Unit scheduling + UI    
Desc: Users can create recurring care routines for a pet (feed, walk, groom, custom).    
Actor: Editor+ | Pre: Pet selected | Trigger: Save routine    
Main: Persist schedule; schedule reminders if enabled    
Alt/Error: Invalid recurrence → validation errors    
AC: Routine appears on calendar/today list for future occurrences

#### FR-ROUTINE-002 — Edit or pause routine

ID=FR-ROUTINE-002; Priority=Must; Epic=HAP-4; Design=Pending Handoff; Verify=UI + unit    
Desc: Users can edit routine details or pause/resume without deleting history.    
Actor: Editor+ | Pre: Routine exists | Trigger: Save edit / toggle pause    
Main: Update definition; adjust future reminders; preserve past completions    
Alt/Error: Concurrent edit conflict → sync conflict UX    
AC: Paused routines do not notify; history retained

#### FR-ROUTINE-003 — Complete routine occurrence

ID=FR-ROUTINE-003; Priority=Must; Epic=HAP-4; Design=Pending Handoff; Verify=UI + offline tests    
Desc: Users can mark a routine occurrence complete with timestamp and actor.    
Actor: Caregiver | Pre: Occurrence due/visible | Trigger: Mark complete    
Main: Record completion; update today list; sync    
Alt/Error: Offline → queue; conflict → surface    
AC: Completion visible to household after sync; offline completion not lost

#### FR-ROUTINE-004 — Routine calendar view

ID=FR-ROUTINE-004; Priority=Should; Epic=HAP-4; Design=Pending Handoff; Verify=UI    
Desc: Users can view routines on a calendar/agenda for a pet or household.    
Actor: Caregiver | Pre: Routines exist | Trigger: Open calendar/agenda    
Main: Show occurrences by day; navigate dates    
Alt/Error: Empty state guidance    
AC: Occurrences match schedule engine for selected range

### FR-MED

#### FR-MED-001 — Create medication schedule

ID=FR-MED-001; Priority=Must; Epic=HAP-5; Design=Pending Handoff; Verify=Unit + UI medical-safety tests    
Desc: Users can create a medication with name, dose text (verbatim), schedule, and pet linkage.    
Actor: Editor+ | Pre: Pet selected | Trigger: Save medication    
Main: Persist medication exactly as entered; schedule reminders    
Alt/Error: Missing required fields → block save    
AC: Dose text stored/displayed unchanged from input

#### FR-MED-002 — Explicit tapering plan

ID=FR-MED-002; Priority=Must; Epic=HAP-5; Design=Pending Handoff; Verify=Unit + UI    
Desc: Users can define explicit taper steps (dose text + dates/times); system never invents steps.    
Actor: Editor+ | Pre: Medication exists or being created | Trigger: Save taper plan    
Main: Persist ordered steps; schedule per steps only    
Alt/Error: Incomplete step → validation error; no auto-fill of doses    
AC: No generated intermediate doses; reminders match explicit steps

#### FR-MED-003 — Log medication dose

ID=FR-MED-003; Priority=Must; Epic=HAP-5; Design=Pending Handoff; Verify=UI + repository + sync    
Desc: Users can log a dose event (given/skipped/missed) with timestamp and actor.    
Actor: Caregiver | Pre: Medication schedule exists | Trigger: Log dose action    
Main: Create DoseEvent; run duplicate-dose checks; sync    
Alt/Error: Offline queue; validation failures    
AC: Dose event persisted with actor/time; appears in history

#### FR-MED-004 — Duplicate-dose warning

ID=FR-MED-004; Priority=Must; Epic=HAP-5; Design=Pending Handoff; Verify=Unit conflict engine + UI + multi-user sync tests    
Desc: System warns when a new dose log conflicts with an existing dose in the same safety window (including caregiver concurrent logs).    
Actor: Caregiver / System | Pre: Prior dose event exists in window | Trigger: Attempt to log conflicting dose    
Main: Present warning with prior dose summary; require explicit confirm or cancel    
Alt/Error: User cancels → no new dose; user confirms → log with conflict acknowledgment flag    
AC: Warning shown before commit; cancel leaves single dose; multi-caregiver offline cases covered by sync conflict rules

#### FR-MED-005 — Dosage values never altered

ID=FR-MED-005; Priority=Must; Epic=HAP-5; Design=Pending Handoff; Verify=Unit medical-safety suite    
Desc: System must not infer, round, smooth, unit-coerce, or otherwise alter caregiver-entered dosage values.    
Actor: System | Pre: Dose text entered | Trigger: Any display, reminder, export, or sync path    
Main: Transmit/store/display dose text faithfully    
Alt/Error: If unit conversion offered, it is optional, explicit, and never overwrites original without confirmation    
AC: Automated tests prove round-trip equality of dose strings across local and sync layers

#### FR-MED-006 — Skip or missed dose recording

ID=FR-MED-006; Priority=Must; Epic=HAP-5; Design=Pending Handoff; Verify=UI + unit    
Desc: Users can record skipped/missed doses distinctly from given doses.    
Actor: Caregiver | Pre: Occurrence exists | Trigger: Mark skipped/missed    
Main: Store status; optional note; no dosage invention    
Alt/Error: None beyond validation    
AC: History distinguishes given vs skipped/missed

### FR-REFILL

#### FR-REFILL-001 — Track inventory quantity

ID=FR-REFILL-001; Priority=Should; Epic=HAP-5; Design=Pending Handoff; Verify=Unit + UI    
Desc: Users can set and decrement medication inventory when doses are given (per configuration).    
Actor: Caregiver | Pre: Medication with inventory enabled | Trigger: Dose given / manual adjust    
Main: Update quantity; never auto-calc dosage from inventory    
Alt/Error: Negative blocked    
AC: Quantity updates consistently; dose text unaffected

### Remaining requirements (FR-REFILL-002 … NFR-OBSERVABILITY-003)

All items include mandatory fields below. Design reference = Pending Handoff for every ID.

| ID | Title | Priority | Epic | Verify | Description | Actor / Pre / Trigger | Main | Alt/Error | AC |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| FR-REFILL-002 | Low inventory alert | Should | HAP-5 | Unit + notification tests | Notify when inventory reaches user-defined threshold. | System / Caregiver / Pre: Threshold configured / Trigger: Quantity ≤ threshold | Create in-app/push alert per preferences | Notifications disabled → in-app only | Alert fires once per crossing unless reset by refill |
| FR-REFILL-003 | Log refill | Should | HAP-5 | UI | Users can log a refill that increases inventory. | Caregiver / Pre: Medication exists / Trigger: Save refill | Increase quantity; record refill event | Invalid quantity → error | Inventory reflects refill; history shows refill event |
| FR-REACTION-001 | Log adverse reaction | Must | HAP-5 | UI | Users can log a suspected adverse reaction linked to a pet and optional medication. | Caregiver / Pre: Pet selected / Trigger: Submit reaction form | Store severity, notes, time; no treatment advice | Validation errors | Reaction appears in history/export |
| FR-REACTION-002 | Serious reaction guidance | Must | HAP-8 | UI copy review + tests | For serious severity, app directs user to seek veterinary/emergency care without providing treatment instructions. | Caregiver / System / Pre: Serious severity selected / Trigger: Save or classify serious reaction | Show seek-care messaging; offer Emergency Vet Pass / contacts shortcuts | Offline → still show local guidance + cached emergency data | Copy contains no dosing/treatment instructions; emergency entry points available |
| FR-REACTION-003 | Reaction history for vet prep | Should | HAP-10 | UI + export tests | Users can view reaction history and include it in reports. | Caregiver / Pre: Reactions logged / Trigger: Open history / generate report | List chronological reactions; selectable for export | Empty state | Exported reactions match stored records |
| FR-TIMEZONE-001 | Assign timezone context | Must | HAP-4 | Unit | Schedules have an explicit timezone context (user and/or pet policy). | User / System / Pre: Profile/pet exists / Trigger: Create schedule or set preference | Persist timezone ID with schedules | Missing TZ → default with disclosure | Stored timezone is visible/auditable on schedule objects |
| FR-TIMEZONE-002 | Travel timezone change without silent shift | Must | HAP-4 | Unit + UI | Changing timezone requires explicit user choice: keep local wall times or convert with preview. | User / Pre: Active schedules exist / Trigger: Timezone preference change | Show impact preview; apply only after confirmation | Cancel → no schedule mutation | No silent medication time changes after TZ update |
| FR-TIMEZONE-003 | DST handling | Must | HAP-4 | Unit scheduling suite | DST transitions do not drop or duplicate dose reminders incorrectly; policy is deterministic and tested. | System / Pre: Schedules spanning DST / Trigger: DST transition | Apply documented scheduling rules; avoid double-fire where policy says once | Ambiguous local times resolved per policy and logged | Test vectors around DST pass |
| FR-METRIC-001 | Log health metric | Must | HAP-6 | UI + unit | Users can log metrics such as weight and other MVP metric types. | Caregiver / Pre: Pet selected / Trigger: Save metric entry | Persist value, unit, timestamp, notes | Invalid numbers blocked | Entry appears in history charts/lists |
| FR-METRIC-002 | Log symptoms | Must | HAP-6 | UI | Users can log symptom observations without diagnostic labeling by the app. | Caregiver / Pre: Pet selected / Trigger: Save symptom | Store caregiver-described symptoms/severity/time | Empty required fields blocked | Symptoms exportable; no auto-diagnosis labels |
| FR-METRIC-003 | Metric history visualization | Should | HAP-10 | UI | Users can view metric history for trends. | Caregiver / Pre: Metrics exist / Trigger: Open metric history | Show chronological list/chart | Empty state | Values match stored entries; dosage unrelated fields unchanged |
| FR-DIET-001 | Log diet entry | Must | HAP-6 | UI | Users can log meals/diet entries for a pet. | Caregiver / Pre: Pet selected / Trigger: Save diet entry | Persist food description, amount text, time | Validation errors | Entry in history; amount text not silently normalized in a destructive way |
| FR-DIET-002 | Diet restrictions notes | Should | HAP-6 | UI | Users can maintain diet restriction/allergy notes on the pet profile. | Editor+ / Pre: Pet exists / Trigger: Save diet notes | Persist notes; surface on emergency/export where selected | Unauthorized deny | Notes visible to authorized caregivers after sync |
| FR-DIET-003 | Diet summary for reports | Should | HAP-10 | Export tests | Diet entries/notes can be included in vet reports. | Caregiver / Pre: Diet data exists / Trigger: Generate report with diet section selected | Include selected diet data in PDF/CSV | None selected → omit section | Export content matches selection |
| FR-CAREGIVER-001 | Accept household invite | Must | HAP-7 | Integration | Invited user can accept invite and gain role-scoped access. | Invitee / Pre: Valid invite; authenticated / Trigger: Accept invite | Join household; load shared pets | Expired/revoked invite → failure | Member listed; access matches role |
| FR-CAREGIVER-002 | Role-based permissions | Must | HAP-7 | Security rules + UI | Actions are authorized according to household role matrix. | System / Pre: Membership exists / Trigger: Any privileged action | Enforce allow/deny server-side and client-side | Deny with explanation | Viewer cannot perform editor-only mutations; rules tests cover matrix |
| FR-CAREGIVER-003 | Activity feed | Should | HAP-7 | UI + sync | Household sees recent caregiver actions (dose logs, completions). | Caregiver / Pre: Shared household activity exists / Trigger: Open activity feed | Show actor, action, pet, time | Empty state | New dose log from member A visible to member B after sync |
| FR-CAREGIVER-004 | Remove caregiver access | Must | HAP-7 | Security tests | Owner can revoke caregiver access immediately. | Owner / Pre: Target is member / Trigger: Confirm removal | Remove membership; invalidate access; stop notifications for that membership | Cannot remove self as sole owner via this path | Removed user cannot read pet data after refresh |

### FR-SYNC through NFR-OBSERVABILITY (compact full fields)

| ID | Title | Priority | Epic | Verify | Description | Actor / Pre / Trigger | Main | Alt/Error | AC |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| FR-SYNC-001 | Offline mutation queue | Must | HAP-7 | Offline instrumentation | Care mutations performed offline are queued durably. | System / Caregiver / Pre: Authenticated; local DB / Trigger: Mutation while offline | Write local; enqueue SyncOp; show pending | Disk full → user-visible failure | Queue survives process death |
| FR-SYNC-002 | Sync on reconnect | Must | HAP-7 | Integration | Queued operations sync when connectivity returns. | System / Pre: Pending SyncOps / Trigger: Network restored | Push/pull with auth; mark synced | Partial failure retries with backoff | Successful sync clears pending; failures remain visible |
| FR-SYNC-003 | Conflict detection and resolution policy | Must | HAP-7 | Sync conflict suite | Conflicting concurrent edits detected; dose events safety-aware. | System / Caregiver / Pre: Concurrent mutations / Trigger: Sync merge | Detect conflicts; apply entity policy; prompt when required | Unresolvable → keep both with flags for dose safety | Dose conflicts never silently discard Given |
| FR-SYNC-004 | Caregiver concurrent dose conflict | Must | HAP-7 | Multi-client sync test | Dual offline dose logs produce surfaced conflict after sync. | Caregivers / System / Pre: Shared occurrence / Trigger: Dual offline logs then sync | Retain evidence of both; mark conflict | Extended partition → conflict on eventual sync | Neither dose silently deleted |
| FR-SYNC-005 | Sync status visibility | Should | HAP-7 | UI | Users can see pending/syncing/error sync status. | Caregiver / Pre: App running / Trigger: Pending ops or sync events | Surface status; allow retry on error | Auth error → re-auth prompt | Pending and error states observable |
| FR-EMERGENCY-001 | Compose Emergency Vet Pass | Must | HAP-8 | UI | Compose Emergency Vet Pass with contacts, conditions, allergies, notes. | Owner / Editor / Pre: Pet exists / Trigger: Save | Persist fields; update offline cache | Validation for required fields | Pass viewable; fields match input |
| FR-EMERGENCY-002 | Offline emergency cache | Must | HAP-8 | Offline UI tests | Latest Emergency Vet Pass available offline. | Caregiver / System / Pre: Pass cached / Trigger: Open pass offline | Render from secure local cache; last-updated stamp | Never cached → offline empty-state | Airplane mode shows last cached pass |
| FR-EMERGENCY-003 | Secure emergency share link | Must | HAP-8 | Integration + security | Create expiring secure link to emergency information. | Owner / authorized / Pre: Pass exists; network / Trigger: Create share | Generate scoped expiring link | Failure → no link | Expired/revoked links deny access |
| FR-EMERGENCY-004 | Emergency QR access | Should | HAP-8 | Security + UI | Present QR resolving to authorized emergency info. | Caregiver / Pre: Active share/QR token / Trigger: Show QR | Display QR; scoped data per auth/expiry | Revoked token → dead-end | Expiry enforced |
| FR-EMERGENCY-005 | Update emergency contacts and vet | Must | HAP-8 | UI + offline | Update emergency contacts and primary vet details. | Editor+ / Pre: Pet/pass exists / Trigger: Save updates | Update records; refresh offline cache | Offline save queues | Updated contacts appear offline after sync/cache refresh |
| FR-DOCUMENT-001 | Upload pet document | Should | HAP-8 | Integration | Upload documents to pet records. | Editor+ / Pre: Auth; storage / Trigger: Upload | Store via Storage with metadata | Too large/unsupported → error | Document listed under pet |
| FR-DOCUMENT-002 | View or download document | Should | HAP-8 | Security + UI | Authorized users view/download documents. | Authorized caregiver / Pre: Document exists / Trigger: Open | Authorized fetch | Unauthorized/404 → error | Viewer role policy enforced |
| FR-DOCUMENT-003 | Delete document | Should | HAP-8 | Integration | Delete documents with confirmation. | Owner / Editor / Pre: Document exists / Trigger: Confirm delete | Remove storage + metadata | Failure → consistent state | Deleted doc not listed/fetchable |
| FR-SHARE-001 | Secure share link with expiry | Must | HAP-8 | Security tests | Share scoped pet information via expiring links. | Authorized user / Pre: Content exists / Trigger: Create share | Tokenized link with scope + expiry | Creation failure | Access denied after expiry |
| FR-SHARE-002 | Revoke share link | Must | HAP-8 | Security tests | Revoke active share links. | Owner / creator / Pre: Active link / Trigger: Revoke | Invalidate immediately | Already revoked → idempotent | Revoked link cannot fetch |
| FR-SHARE-003 | Scope-limited share content | Must | HAP-8 | Security tests | Share payloads include only selected scopes. | System / Pre: Share with scope / Trigger: Recipient access | Filter by scope | Tampering → deny | Emergency-only excludes broader data |
| FR-REPORT-001 | Generate PDF vet summary | Should | HAP-10 | Export tests | Generate PDF with dose texts verbatim. | Caregiver / Pre: Pet data / Trigger: Generate PDF | Build from selected sections | Failure → error | Doses match source strings |
| FR-REPORT-002 | Export CSV data | Should | HAP-10 | Unit export | Export selected datasets as CSV. | Caregiver / Pre: Data exists / Trigger: Export CSV | Stable headers; faithful values | Empty selection blocked | Values match DB |
| FR-REPORT-003 | Insights overview | Could | HAP-10 | UI | Basic insights without diagnostic claims. | Caregiver / Pre: Historical data / Trigger: Open insights | Descriptive stats; disclaimer | Insufficient data empty state | No diagnostic language |
| FR-WIDGET-001 | Today tasks widget | Should | HAP-9 | Widget tests | Widget shows today’s routines/meds summary. | User / Pre: Widget added / Trigger: Refresh | Show due items | Signed out → sign-in prompt | Matches app today list |
| FR-WIDGET-002 | Medication due widget | Should | HAP-9 | Widget tests | Next/due meds with dose text verbatim. | User / Pre: Meds scheduled / Trigger: Refresh | Display next dues | None due → empty | Dose strings match records |
| FR-WIDGET-003 | Widget privacy constraints | Must | HAP-9 | Privacy/UI | Widgets avoid highly sensitive notes/documents. | System / Pre: Widget visible / Trigger: Render | Redaction/minimization | Ambiguous → omit | Sensitive fields not on insecure surfaces |
| FR-NOTIFICATION-001 | Schedule local reminders | Must | HAP-9 | Device notification | Schedule local notifications for routines/meds. | System / Pre: Permission; schedules / Trigger: Create/update | Alarms per platform policy | Permission denied → explanation | Reminder fires in lab |
| FR-NOTIFICATION-002 | Push notification delivery | Should | HAP-9 | FCM tests | FCM for cross-device/caregiver pushes where justified. | System / Pre: FCM registered; prefs allow / Trigger: Push | Deliver; tap opens screen | Invalid token refresh; pref off suppress | Pref off suppresses |
| FR-NOTIFICATION-003 | Notification actions | Should | HAP-9 | Instrumented notification | Mark given / snooze with duplicate-dose checks. | Caregiver / Pre: Notification shown / Trigger: Action | Perform with FR-MED-004 checks | Auth required → open auth | Mark given creates dose event |
| FR-NOTIFICATION-004 | Quiet hours and preferences | Should | HAP-11 | Unit + UI | Configure categories and quiet hours. | User / Pre: Signed in / Trigger: Change prefs | Persist; suppress per rules | Invalid ranges blocked | Quiet hours suppress routines; critical policy documented |
| FR-SETTINGS-001 | App preferences | Must | HAP-11 | UI | Manage general app preferences. | User / Pre: Optional sign-in for some / Trigger: Change | Persist; sync profile prefs where applicable | Invalid rejected | Survive restart |
| FR-SETTINGS-002 | Notification preference center | Must | HAP-11 | UI + unit | Manage notification categories. | User / Pre: Signed in / Trigger: Toggle | Update scheduler prefs | OS permission missing → settings deep link | Disabled category does not notify |
| FR-SETTINGS-003 | Theme and Dynamic Color | Should | HAP-9 | UI a11y | Light/dark and Dynamic Color where available. | User / System / Pre: Capability varies / Trigger: Theme change | Apply theme; fallback | None | Switch without crash; contrast OK |
| FR-ADS-001 | Show ads for free tier | Should | HAP-11 | UI + boundary | Ads on allowed surfaces for non-premium. | System / Pre: Ads enabled; consent OK / Trigger: Allowed screen | Load via ads module | Ad failure → soft fallback | Care UX usable if ads fail |
| FR-ADS-002 | No ads on critical medical/emergency surfaces | Must | HAP-11 | UI denylist | No ads on emergency/reaction/dose-safety surfaces. | System / Pre: Free tier / Trigger: Open critical | Suppress ads | N/A | No ad views on denylist |
| FR-ADS-003 | Ads consent and privacy | Must | HAP-11 | Privacy checklist | Ads respect consent/privacy requirements. | User / System / Pre: Consent framework / Trigger: Ad opportunity | Gate personalized ads | Consent denied → NPA or no ads | No personalized ads without consent |
| FR-BILLING-001 | Subscribe to Premium | Should | HAP-11 | Play Billing tests | Purchase Premium via Play Billing. | User / Pre: Billing available / Trigger: Start subscription | Launch flow; grant entitlements | Cancel/fail → no change | Unlocks Premium per matrix |
| FR-BILLING-002 | Restore purchases | Should | HAP-11 | Billing tests | Restore prior Premium purchases. | User / Pre: Prior purchase / Trigger: Restore | Query; reapply | None found → message | Restores without duplicate charge |
| FR-BILLING-003 | Premium entitlement unlock | Must | HAP-11 | Unit + UI | Entitlements gate Premium/ads removal. | System / Pre: Entitlement known / Trigger: Feature/ad decision | Check entitlement with refresh | Unknown → safe free-tier defaults | Consistent gating |
| FR-PRIVACY-001 | Privacy policy and terms access | Must | HAP-11 | UI | Access privacy policy and terms. | Any user / Pre: URLs configured / Trigger: Open links | Open policy/terms | Offline → cached or error | Reachable from settings/about |
| FR-PRIVACY-002 | Export personal data | Must | HAP-11 | Privacy + export | Export personal and pet care data. | Owner / Pre: Signed in / Trigger: Request export | Generate package | Large export async | Dose texts faithful |
| FR-PRIVACY-003 | Delete account and data | Must | HAP-11 | Privacy integration | Delete account/data with confirmation. | Owner / Pre: Challenges passed / Trigger: Confirm | Delete; revoke shares; clear local | Ownership blockers guided | Post-delete access denied |
| FR-PRIVACY-004 | Consent management | Must | HAP-11 | Privacy checklist | View/change analytics/ads consents. | User / Pre: Consent framework / Trigger: Privacy settings | Update consents | Withdraw → stop optional processing | Withdrawal enforced |
| FR-PRIVACY-005 | Minimize PII in logs and analytics | Must | HAP-11 | Static + instrumentation | Logs/analytics avoid raw PII/health payloads. | System / Pre: Logging enabled / Trigger: Events | Redacted/structured events | No PII to prod sinks | No emails/doses/docs in analytics |
| NFR-SEC-001 | Secure token storage | Must | HAP-1 | Security review/tests | Secure platform storage for tokens. | System / Pre: Sign-in / Trigger: Persist | Keystore-backed storage | Failure → re-auth | No cleartext tokens |
| NFR-SEC-002 | App Check for backend | Must | HAP-1 | Security config review | App Check for deployed backends. | System / Pre: Firebase post Phase 0 / Trigger: Backend requests | Attach tokens; reject abuse | Fail closed in prod | Required for sensitive ops |
| NFR-SEC-003 | TLS transport | Must | HAP-12 | Config review | TLS only; no cleartext. | System / Pre: Network / Trigger: API calls | HTTPS only | Invalid cert → fail | Cleartext disallowed |
| NFR-PRIVACY-001 | Data classification | Must | HAP-11 | Design review | Classify and handle data by class. | Engineering / System / Pre: Architecture / Trigger: Feature design | Controls by class | Unclassified blocked | Classes documented |
| NFR-PRIVACY-002 | Least privilege access | Must | HAP-11 | Rules tests | Access only required data. | System / Pre: AuthZ deployed / Trigger: R/W | Rules + client checks | Deny by default | Cross-household deny |
| NFR-PRIVACY-003 | Share link retention limits | Must | HAP-11 | Integration | Max TTLs and cleanup for shares. | System / Pre: Share created / Trigger: Expiry | Expire/GC tokens | Retry cleanup | Beyond TTL deny |
| NFR-PERF-001 | Cold start target | Should | HAP-12 | Perf lab | Cold start within budget. | System / Pre: Release build / Trigger: Cold launch | Minimize main-thread work | Budget miss fails gate | Reported at Epic 12 |
| NFR-PERF-002 | List scroll performance | Should | HAP-12 | Perf tests | Smooth primary list scroll. | User / Pre: Large data / Trigger: Scroll | Efficient Compose lists | Optimize on miss | No sustained jank |
| NFR-PERF-003 | Sync backlog processing | Should | HAP-12 | Offline stress | Large backlog without ANR. | System / Pre: Many pending / Trigger: Reconnect | Batch with backoff | Surface prolonged failure | No ANR |
| NFR-ACCESS-001 | TalkBack support | Must | HAP-12 | Manual a11y | Primary flows with TalkBack. | Screen-reader user / Pre: TalkBack on / Trigger: Navigate | Labels; focus order | Unlabeled fail gate | Auth/today/dose/emergency pass |
| NFR-ACCESS-002 | Font scaling | Must | HAP-9 | UI a11y | Large fonts; dose text readable. | User / Pre: Large font / Trigger: Dose/emergency screens | Reflow; readable dose | Scroll for overflow | Readable at large scale |
| NFR-ACCESS-003 | Color contrast | Must | HAP-9 | A11y scanner | Contrast meets targets. | User / Pre: Themed UI / Trigger: Visual QA | Theme tokens | Adjust dynamic color edges | Alerts not color-only |
| NFR-RELIABILITY-001 | Crash-free sessions target | Must | HAP-12 | Crash/soak | High crash-free rate. | Eng/QA / Pre: Reporting / Trigger: Cycles | Monitor; fix | Blockers fail gates | No blocker crashes at gate |
| NFR-RELIABILITY-002 | Idempotent dose logging | Must | HAP-5 | Unit + sync | Retries not silent duplicate doses. | System / Pre: Dose submitted / Trigger: Retry | Idempotency/dedupe | Ambiguity → conflict flag | No unacknowledged duplicates |
| NFR-RELIABILITY-003 | Graceful offline degradation | Must | HAP-7 | Offline tests | Graceful offline care actions. | User / Pre: Offline / Trigger: Use app | Cached reads + queued writes | Online-only disabled with reason | No hard crashes |
| NFR-OFFLINE-001 | Critical reads offline | Must | HAP-7 | Offline suite | Today/med due/emergency offline. | Caregiver / Pre: Cached / Trigger: Offline open | Serve local cache | Empty if missing | Airplane checklist passes |
| NFR-OFFLINE-002 | Queue durability | Must | HAP-7 | Instrumentation | Queue survives death/reboot. | System / Pre: Pending / Trigger: Kill/reboot | Restore from Room | Corruption → safe reset | Dose log still queued |
| NFR-OFFLINE-003 | Conflict surfacing UX | Must | HAP-7 | UI + sync | Conflicts user-visible. | Caregiver / Pre: Conflict / Trigger: Sync conflict | Show conflict UI | Auto-resolve only non-safety | Dose conflicts need ack |
| NFR-LOCALIZATION-001 | Externalized strings | Must | HAP-11 | Lint | Strings externalized. | Eng / Pre: Feature UI / Trigger: Impl | Resources/i18n | Lint fails hardcoded | Checks pass |
| NFR-LOCALIZATION-002 | RTL layout support | Must | HAP-11 | UI RTL | RTL mirroring correct. | User / Pre: RTL locale / Trigger: Primary screens | Mirror; dose intact | Fix directional icons | Primary flows pass RTL |
| NFR-LOCALIZATION-003 | Locale-aware dates and numbers | Must | HAP-11 | Unit + UI | Locale format; dose text literal. | System / Pre: Locale set / Trigger: Render | Formatters; dose unchanged | Fallback locale | Dates reformat; doses unchanged |
| NFR-MAINTAINABILITY-001 | Modular architecture | Must | HAP-1 | Architecture review | UI/domain/data boundaries. | Eng / Pre: Structure / Trigger: Features | Inward deps; monetization isolated | Cycles fail checks | Module map enforced |
| NFR-MAINTAINABILITY-002 | Lint and CI quality | Must | HAP-12 | CI evidence | Lint/unit in CI for develop PRs. | Eng/CI / Pre: CI configured / Trigger: PR | Fail on gates | Quarantine flakes | Required checks green |
| NFR-MAINTAINABILITY-003 | Documented ADRs | Should | HAP-1 | Doc review | ADRs for major decisions. | Eng / Pre: Decision / Trigger: Choice | Record ADR | Undocumented blocked | Core ADRs before dependents |
| NFR-OBSERVABILITY-001 | Structured non-PII logging | Must | HAP-12 | Log review | Structured logs without PII/health. | System / Pre: Sink configured / Trigger: Events | Codes/IDs/durations | Logging must not crash care | Redaction checklist passes |
| NFR-OBSERVABILITY-002 | Crash reporting | Must | HAP-12 | Crash drill | Crashes with PII scrubbing. | System / Pre: Reporting enabled / Trigger: Exception | Upload scrubbed stacks | Offline queue | No email/dose in sink |
| NFR-OBSERVABILITY-003 | Sync and error metrics | Should | HAP-12 | Metrics review | Sync/error rates measurable. | Eng / Pre: Hooks present / Trigger: Sync/failures | Counters without PII | Backend down → local-only | Gates cite sync error rate |

## 9. Requirements index by namespace

| Namespace | Count |
| --- | --- |
| FR-ACCOUNT | 3 |
| FR-ADS | 3 |
| FR-AUTH | 5 |
| FR-BILLING | 3 |
| FR-CAREGIVER | 4 |
| FR-DIET | 3 |
| FR-DOCUMENT | 3 |
| FR-EMERGENCY | 5 |
| FR-HOUSEHOLD | 3 |
| FR-MED | 6 |
| FR-METRIC | 3 |
| FR-NOTIFICATION | 4 |
| FR-PET | 4 |
| FR-PRIVACY | 5 |
| FR-REACTION | 3 |
| FR-REFILL | 3 |
| FR-REPORT | 3 |
| FR-ROUTINE | 4 |
| FR-SETTINGS | 3 |
| FR-SHARE | 3 |
| FR-SYNC | 5 |
| FR-TIMEZONE | 3 |
| FR-WIDGET | 3 |
| NFR-ACCESS | 3 |
| NFR-LOCALIZATION | 3 |
| NFR-MAINTAINABILITY | 3 |
| NFR-OBSERVABILITY | 3 |
| NFR-OFFLINE | 3 |
| NFR-PERF | 3 |
| NFR-PRIVACY | 3 |
| NFR-RELIABILITY | 3 |
| NFR-SEC | 3 |
| **Total** | **111** |

## 10. Traceability and verification

SRS IDs are authoritative. Story/Task, Figma, tests, PRs, and evidence remain Pending until implementation. Never fabricate evidence. See Traceability Matrix.

## 11. Document control

| Version | Date | Notes |
| --- | --- | --- |
| 0.1.0 | 2026-08-08 | Phase 0 baseline SRS; Pre-Implementation |
