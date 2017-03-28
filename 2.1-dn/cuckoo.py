import math


def cuckoo(k, m=5):
    h1 = k % m
    h2 = math.floor(k/m) % m
    return (h1, h2)

for k in [7, 9, 12, 11, 3, 17, 1, 23, 10, 8]:
    print(cuckoo(k), k)