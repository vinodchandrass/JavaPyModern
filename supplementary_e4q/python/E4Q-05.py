"""E4Q-05: source-derived Java semantics; development version 1."""
def translate(left, right):
    for operand in (left, right):
        if type(operand) is not int or not -(1 << 15) <= operand < (1 << 15):
            raise ValueError("expected signed 16-bit Java operand")
    return (left > right) - (left < right)
