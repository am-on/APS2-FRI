# Peter Puzzle is solving the problem of rod cutting at the steelwork in Jesenice. A rod of integer length n dm is
# possible to cut on integer number of segments. He heard from his nephew, that the problem can be solved using
# dynamic programming.
#
# a) Assume the following prices for various rod lengths:
#
# length (dm)
# 	1 	2 	3 	4 	 5 	 6 	 7 	 8 	 9
# price (EUR)
# 	2   4   7   8   10  11  12 	19 	22
#
# The steelworks began producing rods of length n=7 and they do not know whether they sell rods of the original
# length or they should cut them into smaller segments and sell those separately in order to maximize
# their profit. Compute the optimal segment lengths and the highest possible income.
#
# Compute the optimal segment lengths and incomes, if the steelworks produced rods of length n=8. What about n=9?

price = [0,2,4,7,9,10,11,12,19,22]
optimalCuts = [str(i) for i, p in enumerate(price)]
calculatedOptimal = [False for i in price]

def optimal(n):
    if n <= 0: return 0
    if n == 1: return price[n]

    # best known price is price without cutting
    best = price[n]

    for i in range(n//2,n):
        # get best price of each part
        part1 = price[i] if calculatedOptimal[i] else optimal(i)
        part2 = price[n-i] if calculatedOptimal[n - i] else optimal(n - i)
        nB = part1 + part2
        # nB = part1 + part2 - 2
        # nB = part1 + part2 - max(i,n-i)/min(i,n-i)

        # update cutting combination if better price was found
        if nB > best:
            price[n] = nB
            optimalCuts[n] = str(optimalCuts[i]) + " in " + str(optimalCuts[n - i])
            best = nB

    # optimal price was found, remember that
    calculatedOptimal[n] = True

    return best

optimal(len(price)-1)

for i, d in enumerate(zip(price, optimalCuts)):
    cena, kombinacija, = d
    print("{} za ceno: {}, razrežemo na: {}".format(i,cena,kombinacija))

print(price)
print(optimalCuts)
