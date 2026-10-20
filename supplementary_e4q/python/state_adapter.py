"""Development-only adapter for JUnit4 Description and Result state."""
from dataclasses import dataclass, field

@dataclass
class Description:
    display_name: str
    children: list = field(default_factory=list)
    # The Java isTest/isSuite implementation determines classification solely
    # from children, including for Description.EMPTY and empty suites.
    def __post_init__(self):
        if not isinstance(self.display_name, str):
            raise TypeError("display_name must be str")
        if not isinstance(self.children, list):
            raise TypeError("children must be list")
        if any(not isinstance(c, Description) for c in self.children):
            raise TypeError("children must contain Description objects")
    def is_test(self): return len(self.children) == 0
    def is_suite(self): return not self.is_test()
    def test_count(self):
        if self.is_test(): return 1
        total = sum(c.test_count() for c in self.children)
        return ((total + 2**31) % 2**32) - 2**31

@dataclass
class Result:
    run_count: int = 0
    failures: list = field(default_factory=list)
    def __post_init__(self):
        if type(self.run_count) is not int or not -(2**31) <= self.run_count < 2**31:
            raise ValueError("run_count must be signed Java int")
        if not isinstance(self.failures, list):
            raise TypeError("failures must be list")
    def was_successful(self): return len(self.failures) == 0
