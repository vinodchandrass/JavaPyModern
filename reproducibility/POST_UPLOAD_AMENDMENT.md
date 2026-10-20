# Post-upload reproducibility amendment

This file records a **packaging-only** modification made after the reproducibility-only archive was frozen.

## Changed path
`supplementary_e4q/python/test_development.py`

- Original ZIP SHA-256: `548c9e9b59176d8301ab7e9daaf746f10da6cc3efff6d5fde6ecbac5c0182de9`
- Corrected UTF-8 SHA-256: `bed665543b592be37616a4f5b0b260fb566aa87aeeefcca6471b539b673bb0ae`
- Correction: change the candidate directory from `parents[1]/'candidates'` to `parent` so the runner discovers `E4Q-01.py` through `E4Q-12.py` in `supplementary_e4q/python/`.
- GitHub correction commit: `849c57edf56f471c2a73ccb3453e34ee04d3a06c`.

## Local verification
The original 37-entry ZIP was extracted and only the candidate-path expression in the test runner was modified. Python executed `test_development.py`: **5 tests ran, all passed**. This is development-test execution only; no independent Java oracle comparison, independent holdout evaluation, or framework-wide reproducibility is claimed.

## Manifest policy
`reproducibility/UPLOAD_MANIFEST_SHA256.json` records the original archive baseline and is retained unchanged. This amendment records the known divergence. All frozen candidates and experimental outputs must remain unchanged.

## Dependencies
The Java oracles require external Apache Commons Lang 3 and JUnit 4 dependencies. Compilation and semantic comparison against Python have **not** been verified in this checkpoint.
