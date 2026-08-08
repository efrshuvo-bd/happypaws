# HappyPaws

## Pet Routine & Medication Tracker

**HappyPaws** (`com.auramislab.happypaws`) helps pet caregivers manage multi-pet routines, medication schedules (including irregular and tapering plans), health metrics, diet tracking, caregiver collaboration, and emergency veterinary information.

HappyPaws **records** caregiver-managed care data. It does **not** diagnose, prescribe, calculate, round, or alter medication dosages.

| Field | Value |
| --- | --- |
| Status | **Pre-implementation / documentation initialization** (HAP-13). Android application code is not complete. |
| App ID | `com.auramislab.happypaws` |
| Platform | Android (target stack documented; app not bootstrapped in this Task) |
| Backend posture | Firebase-first (Auth, Firestore, Storage, FCM, App Check) — configuration is out of scope for HAP-13 |
| Auth (planned) | Google Sign-In + Email/Password |
| Monetization (planned) | Ads + Premium (Play Billing), isolated from care domain |

### Planned SDK / build targets

| Item | Value |
| --- | --- |
| minSdk | 26 |
| compileSdk | 36 |
| targetSdk | 36 |
| Gradle JDK | 21 |
| JVM target | 17 |

These targets are planning constraints for upcoming foundation Tasks (for example HAP-14). This repository does **not** yet contain an Android/Gradle project from HAP-13.

## Medical-safety disclaimer

HappyPaws is a **care logging** product, not a veterinary diagnostic or prescribing system.

* Never invent, auto-calculate, round, or silently alter dose text.
* Tapering plans must use **explicit** step definitions entered by caregivers.
* Duplicate-dose risks must warn the user; never silently discard conflicting Given evidence.
* Serious reaction flows should direct caregivers to seek veterinary care.
* Ads must not appear on critical medical-safety surfaces (for example emergency pass, serious reaction, duplicate-dose confirmation).

## Documentation

Confluence space **HA** is the planning authority. Repository docs under `docs/` are mirrors initialized by HAP-13.

| Document | Repository | Confluence |
| --- | --- | --- |
| Parent hub | — | [HappyPaws — Pet Routine & Medication Tracker](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628) |
| Project Plan | [docs/project-plan.md](docs/project-plan.md) | [5898656](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898656) |
| Software Requirements Specification | [docs/software-requirements-specification.md](docs/software-requirements-specification.md) | [5996592](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996592) |
| Architecture | [docs/architecture.md](docs/architecture.md) | [5898685](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898685) |
| Test Guidelines and Quality Strategy | [docs/test-guidelines-and-quality-strategy.md](docs/test-guidelines-and-quality-strategy.md) | [5898707](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898707) |
| Requirements Traceability Matrix | [docs/requirements-traceability-matrix.md](docs/requirements-traceability-matrix.md) | [5996570](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996570) |
| Completion Report | [docs/completion-report.md](docs/completion-report.md) | [5996547](https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996547) |
| ADR stubs | [docs/adrs/](docs/adrs/) | (see Architecture §37) |
| Test report stubs | [docs/test-reports/](docs/test-reports/) | — |
| Security stubs | [docs/security/](docs/security/) | — |
| Contributing | [CONTRIBUTING.md](CONTRIBUTING.md) | — |

SRS and RTM each catalog **111** unique requirement IDs. None are marked implemented by HAP-13.

## Project links

* **GitHub:** https://github.com/efrshuvo-bd/happypaws
* **Jira project:** https://auramislab.atlassian.net/browse/HAP
* **Jira board:** https://auramislab.atlassian.net/jira/software/projects/HAP/boards/72
* **Confluence parent:** https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628
* **Figma (Pending Handoff):** [HappyPaws / historical PawMinder-titled file](https://www.figma.com/design/64SUV67cE0iWkNh41LLMKH/HappyPawsContinue-the-existing-%E2%80%9CPawMinder-%E2%80%94-Pet-Routine---Medication-Tracker) — filename metadata only; product name is HappyPaws. Do not modify Figma in HAP-13.

## Approved workflow

Authoritative Jira statuses (do not invent others):

`To Do → In Progress → In Review → Done`

* Run required validation while **In Progress**.
* Open a PR only after pre-PR validation passes; then move the Task to **In Review**.
* After human merge, validate on `origin/develop`, then move to **Done**.
* Agents must **not** self-merge and must **not** move to Done without post-merge validation evidence.

## Branch and PR rules

* Default branch: `main`
* Integration branch: `develop` (all Task PRs target `develop`)
* Implementation Task branches: `task/HAP-<n>-kebab-summary`
* Documentation Task branches may use `docs/HAP-<n>-kebab-summary` when specified by the Task executor instructions
* Never commit directly to `main` or `develop`
* One active implementation Task / one branch at a time unless human explicitly directs otherwise
* Human approval required to merge; do not merge automatically

See [CONTRIBUTING.md](CONTRIBUTING.md) for commit and PR conventions.

## Design handoff gate (HAP-19)

**HAP-19** (Validate Figma/design-handoff) gates Tasks that require final approved UI/design assets. Do not treat Figma as approved for UI implementation until HAP-19 is Done. Foundation documentation (this Task) may proceed without that gate.

## Setup placeholders

Android/Gradle/Firebase bootstrap is **out of scope for HAP-13** and will be introduced by later Tasks (starting with HAP-14). Until then:

* No `google-services.json`, keystores, API keys, or production Firebase project identifiers belong in this repository.
* Cursor project rules for agents live in [`.cursor/rules/happypaws-project.mdc`](.cursor/rules/happypaws-project.mdc).

## License

See [LICENSE](LICENSE). All rights reserved unless otherwise stated in writing by AuraMis Lab.
