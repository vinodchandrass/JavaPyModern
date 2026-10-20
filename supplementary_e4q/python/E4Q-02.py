"""E4Q-02: source-derived Java semantics; development version 1."""
def translate(value):
    if value is not None and type(value) is not bool:
        raise TypeError("expected Java Boolean" if value is None else "expected bool")
    return value is False
