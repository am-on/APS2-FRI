def getHash(m,p,k):
    return (p*k) % m

def noCollision(m, p):
    s = set()
    for k in [16, 15, 4, 0, 20, 5, 11]:
        hash = getHash(m,p,k)
        if hash not in s:
            s.add(hash)
        else:
            return False

    for k in [16, 15, 4, 0, 20, 5, 11]:
        hash = getHash(m, p, k)
        print("({} * {}) mod {} = {}".format(p, k, m, hash))

    return True

def minM():
    p = 300000000
    m = 9
    while True:
        if (noCollision(m, p)):
            print(m, p)
            break

        if (p % 100000000) == 0: print(p)

        p += 1

minM()
noCollision(13, 1)
