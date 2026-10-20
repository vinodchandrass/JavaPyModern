"""E4Q-09: source-derived Java semantics; development version 1."""
from state_adapter import Description

def translate(description):
    if not isinstance(description, Description):
        raise TypeError("expected Description adapter")
    return description.is_test()
