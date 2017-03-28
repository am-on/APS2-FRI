aa

def find(node, pathLen=0,):
    if node.root == node:
        # če še vedno hranimo število elementov,
        # lahko hranimo še "oceno" o število elementov, ki
        # ne kažejo direktno na predstavnika množice (n2),
        # in s tem posodobimo dolžino najdaljšega iskanja

        if pathLen > 1:
            # če je pot do predstavnika bila večja od 1,
            # potem bo prišlo do stiskanja poti, število
            # elementov, ki niso vezani direktno na predstavnika
            # pa se bo zmanjšalo
            node.n2 -= pathLen - 1

            # izračunaj novo najdaljšo pot,
            # možno je, da najdaljše poti nismo
            # skrajšali, kljub krčenju na nekem mestu,
            # zato oceni prištej 1
            node.h = math.floor(math.log((node.n2),2)) + 1
        return node

    # update reference
    node.root = find(node.root, pathLen+1)
    node.h = 0
    return node.root