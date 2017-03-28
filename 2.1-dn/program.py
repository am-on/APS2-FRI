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
    p = 1
    m = 7
    while not noCollision(m, p):    
        if (p % 1000) == 0:
            p = 0
            m += 1
        p += 1
minM()
