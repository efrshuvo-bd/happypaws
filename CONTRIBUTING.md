# Contributing to HappyPaws

Thank you for contributing to **HappyPaws** — Pet Routine & Medication Tracker (`com.auramislab.happypaws`).

Confluence space **HA** is the planning authority. Repository docs under `docs/` mirror that governance. Read [README.md](README.md) before starting work.

## Workflow

Jira statuses (authoritative; do not create/rename/delete):

`To Do → In Progress → In Review → Done`

1. Confirm the Task is next in the approved queue and dependencies are Done.
2. Move **To Do → In Progress** only after preflight passes.
3. Implement on a Task branch created from latest `origin/develop`.
4. Validate while **In Progress** (tests/docs checks required by the Task).
5. Open a PR targeting **`develop`**.
6. Move **In Progress → In Review** only after the PR is open and validation passes.
7. Human reviews and merges. Agents must **not** self-merge.
8. After merge, validate on `origin/develop`, publish evidence, then move to **Done**.

Do not move a Task to Done without post-merge validation. Do not start the next implementation Task until the current one is Done (unless a human explicitly directs otherwise).

## Branches

| Kind | Pattern | Base / target |
| --- | --- | --- |
| Integration | `develop` | receives Task PRs |
| Default | `main` | release/default; do not commit Task work here |
| Implementation Task | `task/HAP-<n>-kebab-summary` | branch from `origin/develop`; PR → `develop` |
| Docs Task (when specified) | `docs/HAP-<n>-kebab-summary` | same |

Rules:

* Never commit directly to `main` or `develop`.
* One Task → one branch → one focused PR.
* Fetch/prune and recreate from latest `origin/develop` before starting.
* Do not reuse stale local `develop` or `main` as the branch point.

## Commits

* Prefer Conventional Commits scoped with the Jira key, for example:
  * `docs(HAP-13): initialize repository documentation`
  * `feat(HAP-14): bootstrap Android Gradle project`
* Keep commits scoped to the Task. Do not mix unrelated work.
* Never commit secrets, keystores, `google-services.json`, API keys, or production Firebase identifiers.
* Do not amend pushed commits or force-push unless a human explicitly requests it.

## Pull requests

* Title should match the primary commit intent and include the Jira key when practical.
* Base branch: **`develop`**
* Head: the Task branch only
* Body should include: summary, scope / out of scope, validation evidence, links to Jira/Confluence, and residual risks.
* Use the repository PR template under [`.github/pull_request_template.md`](.github/pull_request_template.md).
* Include: `Do not merge automatically. Human approval is required.`
* Do not squash/rebase-merge/close without human direction when operating as an agent under HappyPaws rules.

## Continuous integration (HAP-16)

GitHub Actions workflow: [`.github/workflows/ci.yml`](.github/workflows/ci.yml).

Required local / CI checks for implementation PRs:

| Check | Command |
| --- | --- |
| Lint | `./gradlew :app:lintDebug` |
| Unit tests | `./gradlew :app:testDebugUnitTest` |
| Debug assemble | `./gradlew :app:assembleDebug` |

Triggers: `pull_request` and `push` to `develop` / `main`, plus `workflow_dispatch`.

No emulator jobs, Play release pipelines, Firebase App Distribution, or signing secrets in foundation CI. Secrets belong in GitHub Actions secrets / local secret stores only — never in workflow YAML.

### Branch protection (admin-applied)

Repository admins should configure branch protection on `develop` (and later `main`) to:

* Require a pull request before merging
* Require the **CI** workflow to pass
* Disallow force-pushes
* Prefer disallowing self-approve / require human review for merges

Agents must not self-merge even if protection is not yet enabled.

## Testing and quality

* Testing is a gate inside **In Progress** and again post-merge before **Done** — not a separate Jira status.
* Medical-safety, offline/sync, accessibility, and security checks apply when the Task touches those surfaces.
* Epic Quality Gate Tasks publish `docs/test-reports/<epic-key>-test-report.md`. Never fabricate evidence.

## Design handoff (HAP-19)

UI implementation that depends on final approved design assets waits on **HAP-19**. Documentation and non-UI foundation work may proceed when the Project Plan allows.

## Medical safety

HappyPaws records care data only. Contributors must not introduce logic that diagnoses, prescribes, auto-calculates, rounds, or silently alters dosages. Duplicate-dose warnings and conflict evidence retention are mandatory where dose logging is involved.

## Secrets and credentials

Keep secrets out of git and docs. Use local/CI secret stores for Firebase, signing, and API credentials when those Tasks arrive. HAP-13 must not add production configuration.

## Cursor / agent rules

Repository agent rules: [`.cursor/rules/happypaws-project.mdc`](.cursor/rules/happypaws-project.mdc). Do not invent global Cursor rules outside this repository for HappyPaws execution policy.

## Related docs

* [docs/project-plan.md](docs/project-plan.md)
* [docs/architecture.md](docs/architecture.md)
* [docs/test-guidelines-and-quality-strategy.md](docs/test-guidelines-and-quality-strategy.md)
* [docs/requirements-traceability-matrix.md](docs/requirements-traceability-matrix.md)
