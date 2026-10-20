"""E4Q-04: source-derived Java semantics; development version 1."""
def translate(left, right):
    for operand in (left, right):
        if type(operand) is not int or not -(1 << 7) <= operand < (1 << 7):
            raise ValueError("expected signed 8-bit Java operand")
    return (left > right) - (left < right)
