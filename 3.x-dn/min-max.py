# Find a description of the min-max heap on a web. How does the min-max heap differ to the original binary heap,
# what operations are supported and what is the time complexity? Using only arithmetic
# operators (+, -, *, /, %, <<, >>), comparisons (<, >, ==), and control statements (if, while) implement the
# function boolean getLayer(int i) in the programming language of your choice, which for a given index i returns
# whether the element is on a min (0) or max (1) level. What is the time complexity of your function in terms of
# the value of i.

def getLayer(index):
    r = 0
    while index > 0:
        index = index >> 1
        r = 1 ^ r

    return r


import unittest

class TestIsMin(unittest.TestCase):

    def test_1(self):
        self.assertTrue(getLayer(1))

    def test_2(self):
        self.assertFalse(getLayer(2))
        self.assertFalse(getLayer(3))

    def test_3(self):
        self.assertTrue(getLayer(4))
        self.assertTrue(getLayer(5))
        self.assertTrue(getLayer(6))
        self.assertTrue(getLayer(7))

    def test_4(self):
        self.assertFalse(getLayer(8))
        self.assertFalse(getLayer(9))
        self.assertFalse(getLayer(10))
        self.assertFalse(getLayer(11))
        self.assertFalse(getLayer(12))
        self.assertFalse(getLayer(13))
        self.assertFalse(getLayer(14))
        self.assertFalse(getLayer(15))

    def test_5(self):
        self.assertTrue(getLayer(16))
        self.assertTrue(getLayer(18))
        self.assertTrue(getLayer(29))




if __name__ == '__main__':
    unittest.main()


