"""Independent developer-authored cases; not oracle or holdout fixtures."""
import unittest, pathlib, sys, importlib.util
CAND=pathlib.Path(__file__).resolve().parents[1]/'candidates';sys.path.insert(0,str(CAND))
from state_adapter import Description,Result

def task(n):
    p=CAND/f'E4Q-{n:02}.py'; spec=importlib.util.spec_from_file_location(f'e4q_{n}',p)
    module=importlib.util.module_from_spec(spec);spec.loader.exec_module(module);return module.translate
class DevelopmentCases(unittest.TestCase):
    def test_nullable_boolean(self):
        for value,tru,fal in [(True,True,False),(False,False,True),(None,False,False)]:
            self.assertEqual(task(1)(value),tru);self.assertEqual(task(2)(value),fal)
    def test_primitive_boolean(self):
        self.assertEqual(task(3)(True),1);self.assertEqual(task(3)(False),0)
        with self.assertRaises(TypeError):task(3)(None)
    def test_integer_comparison(self):
        for n,bits in [(4,8),(5,16),(6,64)]:
            hi=(1<<(bits-1))-1;lo=-(1<<(bits-1))
            for x,y,expected in [(lo,hi,-1),(hi,lo,1),(hi,hi,0),(lo,lo,0)]:
                self.assertEqual(task(n)(x,y),expected)
            with self.assertRaises(ValueError):task(n)(hi+1,0)
    def test_description(self):
        leaf=Description('leaf');empty=Description('empty');suite=Description('suite',[leaf,empty])
        for obj,name,is_suite,is_test,count in [(leaf,'leaf',False,True,1),(empty,'empty',False,True,1),(suite,'suite',True,False,2)]:
            self.assertEqual(task(7)(obj),name);self.assertEqual(task(8)(obj),is_suite)
            self.assertEqual(task(9)(obj),is_test);self.assertEqual(task(10)(obj),count)
    def test_result(self):
        for result,count,success in [(Result(),0,True),(Result(3,[]),3,True),(Result(0,['failure']),0,False)]:
            self.assertEqual(task(11)(result),count);self.assertEqual(task(12)(result),success)
if __name__=='__main__':unittest.main()
