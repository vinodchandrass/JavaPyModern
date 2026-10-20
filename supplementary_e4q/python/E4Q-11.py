"""E4Q-11: source-derived Java semantics; development version 1."""
from state_adapter import Result

def translate(result):
    if not isinstance(result, Result):
        raise TypeError("expected Result adapter")
    return result.run_count
