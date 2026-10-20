"""E4Q-07: source-derived Java semantics; development version 1."""
from state_adapter import Description

def translate(description):
    if not isinstance(description, Description):
        raise TypeError("expected Description adapter")
    return description.display_name
