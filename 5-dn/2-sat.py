for x1 in [True, False]:
    for x2 in [True, False]:
        for x3 in [True, False]:
                if (not x1 or x2 or not x3) and (x1 or x2 or not x3) and (not x1 or not x2 or x3) and (not x1 or not x2 or not x3) and (not x1 or x2 or not x3) and (not x1 or x2 or x3):
                    print("x1 = {}, x2 = {}, x3 = {}".format(x1, x2, x3))




