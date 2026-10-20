# E4Q Independent Evaluator Handoff and Intake

**Status:** Prepared for evaluator recruitment and intake; no evaluator appointment or holdout run is claimed.  
**Protocol:** `supplementary_e4q/holdout/INDEPENDENT_HOLDOUT_PROTOCOL_v1.md`  
**Development checkpoint:** `647cbfe`

## Purpose

This handoff lets an independent evaluator assess readiness and establish a genuinely independent candidate cohort without receiving holdout outcomes prematurely. It is not authorization to execute the holdout.

## Required evaluator declaration

The proposed evaluator should complete and sign a separate record containing:

- Full name and institutional/organizational affiliation.
- Role and relevant Java/Python testing or software-modernization expertise.
- Declaration of no involvement in developing, tuning, or selecting the E4Q candidates, gates, development cases, or expected outcomes.
- Any conflicts of interest, prior access to candidate code or case outcomes, or collaboration with the development team.
- Confirmation of ability to keep holdout inputs and Java oracle outputs under separate access control.
- Date, signature, and a contact route for audit.

If independence cannot be substantiated, record the limitation and do not describe the work as fully independent.

## Intake checklist

Before any holdout execution, the evaluator and custodian must confirm:

- [ ] Primary candidate cohort was independently sourced and was not used in Stage 1B or gate tuning.
- [ ] Candidate provenance and source hashes are recorded.
- [ ] Frozen G1/G2/G3 implementation and policy hashes match the declared release.
- [ ] Java oracle source/version, build procedure, and dependency hashes are documented and independently checked.
- [ ] Case sampling, inclusion/exclusion, equivalence criterion, and analysis script are frozen and hashed.
- [ ] Holdout expected outputs are stored separately; development team has no access.
- [ ] Gate decisions are recorded and timestamped before outcomes are released.
- [ ] Authorization is signed and timestamped by the responsible parties.
- [ ] Access logs and deviation logs are enabled.
- [ ] A clean, isolated execution environment is available.
- [ ] A release plan excludes restricted/licensed test inputs unless publication is permitted.

## Files the evaluator should return before execution

1. Signed evaluator declaration/conflict-of-interest record.
2. Independent cohort provenance statement and candidate manifest (no oracle outcomes).
3. Frozen protocol and analysis-plan hashes.
4. Signed execution authorization.
5. Custody/access-control plan.
6. Oracle and dependency lineage report.

These documents must be reviewed before execution is authorized. Do not put hidden expected outputs, holdout labels, credentials, or confidential source code in this public-facing handoff file.

## Decision gate

**GO** only when evaluator independence, cohort independence, frozen artifacts, oracle lineage, and signed authorization are all verified.

**NO-GO** if the candidates are development-exposed, outcomes were disclosed early, required hashes disagree, oracle lineage is unresolved, or the authorization is missing. Record the reason and resolve it before any execution.

## Current status

- Stage 1B development evidence is committed in `supplementary_e4q/results/stage1b_development/`.
- The holdout protocol is committed.
- Independent evaluator: **not yet appointed/verified**.
- Independent primary cohort: **not yet verified**.
- Holdout execution: **not performed**.
