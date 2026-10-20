# E4Q Independent Holdout Protocol v1.0

**Status:** PROTOCOL FROZEN FOR PLANNING — NOT EXECUTED  
**Repository:** `vinodchandrass/JavaPyModern`  
**Scope:** Independent validation only; development evidence remains in `supplementary_e4q/results/stage1b_development/`.

## 1. Research question

Do the frozen quality gates reduce escaped semantic defects compared with ungated acceptance, without an unacceptable increase in false rejection, on independently sourced Java-to-Python modernization candidates?

The 12 E4Q candidates evaluated in Stage 1B are development-exposed. They are not the primary independent cohort. They may be reported only as exploratory/repeated evidence and must not be relabelled as independent holdout candidates.

## 2. Freeze requirements before any holdout outcome is exposed

An independent custodian/evaluator must record and hash:
1. Candidate source files, provenance, and candidate IDs, drawn from a genuinely independent cohort not used to design, tune, or revise G1/G2/G3.
2. The exact G1/G2/G3 policy files and implementation hashes.
3. Java reference source/version, build instructions, dependency coordinates and SHA-256 hashes.
4. The holdout case-generation/sampling procedure, inclusion/exclusion rules, expected outputs, and test-case provenance.
5. The evaluator script, environment versions, random seeds where applicable, and analysis script.
6. A signed, timestamped authorization and a manifest of SHA-256 hashes.

The custodian must retain the holdout inputs and oracle outcomes separately. The development team must not receive expected outputs, per-case labels, or interim metrics before gate decisions are frozen and deposited.

## 3. Separation of roles

- **Development team:** provides frozen gate implementations and receives only the predeclared protocol and eventual approved aggregate report.
- **Independent custodian/evaluator:** obtains or creates the independent cohort, runs the Java oracle, stores the holdout outcomes, applies the frozen gates, and maintains access logs.
- **Auditor:** verifies hashes, authorization, protocol adherence, and aggregate calculations without disclosing individual holdout labels before the freeze.

The same person should not both tune the gates and administer the primary holdout. If staffing requires one person to perform multiple roles, disclose this as a limitation and do not call the evaluation fully independent.

## 4. Execution sequence

1. Register and hash all protocol, candidate, gate, oracle, evaluator, and analysis artifacts.
2. Verify that primary-cohort provenance demonstrates no overlap with development cases or gate tuning.
3. Freeze and timestamp gate decisions before releasing oracle outcomes.
4. Run the independent Java oracle and frozen Python candidates in a clean, recorded environment.
5. Validate row counts, candidate IDs, case IDs, exit status, missingness, duplicate IDs, and hashes.
6. Unlock oracle outcomes only after decision files and their hashes are deposited with the custodian.
7. Compute metrics using the predeclared definitions below; retain per-case data under custodian control until release is approved.

No candidate, gate, threshold, case, expected output, or analysis rule may be changed after holdout access. Any protocol deviation must be logged and the affected analysis labelled exploratory.

## 5. Outcomes and estimands

The primary unit is the **candidate-method cluster**, not the individual test case. Test cases within a candidate are not treated as independent observations.

- **Escaped-defect candidate:** gate ACCEPT and the independent oracle identifies at least one predeclared semantic defect for that candidate.
- **False-rejected candidate:** gate REJECT and the independent oracle establishes that the candidate satisfies the predeclared correctness criterion.
- **Abstention:** gate returns ABSTAIN; report separately, not silently recoded as ACCEPT or REJECT.
- **Acceptance coverage:** number of candidates accepted divided by all eligible evaluated candidates.
- **Defect escape rate:** defective candidates accepted divided by all oracle-confirmed defective candidates. Also report the denominator and confidence interval.
- **False-rejection rate:** oracle-correct candidates rejected divided by all oracle-confirmed correct candidates. Also report the denominator and confidence interval.
- **Gate comparisons:** paired comparisons on the same independent candidates; report absolute differences and uncertainty intervals. Use candidate-cluster-aware intervals or exact methods appropriate to the sample size. Do not use case-level binomial intervals as if all cases were independent.

Report exact output agreement separately from semantic-contract agreement. The equivalence criterion must be stated before outcomes are revealed; do not normalize differing return values after seeing results.

## 6. Analysis and reporting rules

- Report the full confusion table (ACCEPT/REJECT/ABSTAIN versus oracle-correct/oracle-defective), denominators, coverage, and uncertainty.
- Compare G1, G2, and G3 only using paired candidate-level outcomes.
- Do not claim one gate is superior unless the prespecified comparison and uncertainty support that claim.
- If the primary independent cohort is too small or lacks both correct and defective candidates, report the evaluation as inconclusive; do not supplement it post hoc with development-exposed candidates and call it independent.
- Report all protocol deviations, exclusions, missing results, failed oracle runs, and dependency lineage.
- Release reproducibility artifacts only after custodian approval; never publish confidential or licensed holdout material without permission.

## 7. Stop conditions

Do not execute the primary holdout if any of the following applies:
- no independent custodian/evaluator is appointed;
- primary candidates or expected outputs overlap with development evidence or were used to tune the gates;
- gate-policy or evaluator hashes do not match the frozen manifest;
- Java oracle lineage or dependency hashes are unresolved;
- authorization is missing or incomplete;
- the holdout specification or analysis plan has not been timestamped before outcome access.

## 8. Current state and claims boundary

Stage 1B development evidence: 108 cases, 91 exact matches, 17 mismatches, 12 candidate clusters; G3 accepted 7 and rejected 5. These are development-only descriptive results.

**No independent holdout execution is authorized or claimed by this document.** At protocol creation, no independent primary cohort, appointed external custodian, or completed signed holdout authorization has been verified. The next action is to appoint the custodian and register the independent cohort and artifacts. Holdout outcomes must remain unopened until the required freezes are complete.
