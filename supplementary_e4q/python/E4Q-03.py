"""E4Q-03: source-derived Java semantics; development version 1."""
def translate(value):
    if type(value) is not bool:
        raise TypeError("expected Java primitive boolean")
    return 1 if value else 0
