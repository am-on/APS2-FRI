n = 16


mergeList = [2,]
print("merge({},{})".format(1, 2))
for i in range(1,4):
    for j in range(1, (2**i)+1, 2):
        index = 2**i + j

        print("merge({},{})".format(index, index+1))
        mergeList.append(index+1)

    print("je")

    while len(mergeList) > 2:
        nList = mergeList[0:1]
        for x in range(1,len(mergeList),2):
            print("  merge({},{})".format(mergeList[x], mergeList[x+1]))
            nList.append(mergeList[x+1])
        mergeList = nList

    if len(mergeList) == 2:
        print("      merge({},{})".format(mergeList[0], mergeList[1]))
        mergeList[0] = mergeList[1]
        mergeList.pop(1)

