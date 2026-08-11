## Jira

* Issue: https://auramislab.atlassian.net/browse/HAP-
* Parent Epic: https://auramislab.atlassian.net/browse/HAP-

## Confluence

* Parent hub: https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898628
* Project Plan: https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898656
* SRS: https://auramislab.atlassian.net/wiki/spaces/HA/pages/5996592
* Architecture: https://auramislab.atlassian.net/wiki/spaces/HA/pages/5898685
* Additional (if relevant):

## Summary

<!-- One or two sentences: what this Task delivers -->

## Scope

*
*

## Out of scope

*
*

## Validation evidence

| Command | Result |
| --- | --- |
| `./gradlew :app:lintDebug` | |
| `./gradlew :app:testDebugUnitTest` | |
| `./gradlew :app:assembleDebug` | |
| Other (if any) | |

## Test plan

*
*

## Checklists

### Process

- [ ] Jira key in branch / PR title / commits as required by CONTRIBUTING
- [ ] PR targets `develop` (not `main` for Task work)
- [ ] No secrets, keystores, `google-services.json`, or production Firebase IDs
- [ ] Do not merge automatically — human approval required

### Medical safety (required when Task touches meds/health/dose/tapering)

- [ ] N/A — Task does not touch medication/health surfaces
- [ ] Does not diagnose, prescribe, calculate, round, or silently alter dose text
- [ ] Tapering (if any) uses explicit steps only
- [ ] Duplicate-dose / conflict evidence retention considered where relevant

### Privacy / security (required when Task touches auth, sync, documents, shares, widgets)

- [ ] N/A — Task does not touch privacy-sensitive surfaces
- [ ] No PII or dose text in logs/analytics samples
- [ ] Secrets stay in local/CI secret stores only

### Accessibility (required for UI PRs)

- [ ] N/A — no UI changes
- [ ] TalkBack labels / focus order considered for primary flows
- [ ] Critical state is not conveyed by color alone
- [ ] Dose / important text remains readable at large font sizes

### Design handoff (HAP-19)

- [ ] N/A — no design-dependent UI
- [ ] HAP-19 Done before implementing final Figma/design assets

## Risks

*
*

## Follow-up

*
*

Do not merge automatically. Human approval is required.
