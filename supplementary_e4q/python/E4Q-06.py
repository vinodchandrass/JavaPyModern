"""E4Q-06: source-derived Java semantics; development version 1."""
def translate(left, right):
    for operand in (left, right):
        if type(operand) is not int or not -(1 << 63) <= operand < (1 << 63):
            raise ValueError("expected signed 64-bit Java operand")
    return (left > right) - (left < right)
