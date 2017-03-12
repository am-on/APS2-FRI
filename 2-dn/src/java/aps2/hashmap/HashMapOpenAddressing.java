package aps2.hashmap;

/**
 * Hash map with open addressing.
 */
public class HashMapOpenAddressing {
	private Element table[]; // table content, if element is not present, use Integer.MIN_VALUE for Element's key
	private HashFunction.HashingMethod h;
	private CollisionProbeSequence c;
	
	public static enum CollisionProbeSequence {
		LinearProbing,    // new h(k) = (h(k) + i) mod m
		QuadraticProbing, // new h(k) = (h(k) + i^2) mod m
		DoubleHashing     // new h(k) = (h(k) + i*h(k)) mod m
	};
	
	public HashMapOpenAddressing(int m, HashFunction.HashingMethod h, CollisionProbeSequence c) {
		this.table = new Element[m];
		this.h = h;
		this.c = c;
		
		// init empty slot as MIN_VALUE
		for (int i=0; i<m; i++) {
			table[i] = new Element(Integer.MIN_VALUE, "");
		}
	}

	public Element[] getTable() {
		return this.table;
	}
	
	/**
	 * If the element doesn't exist yet, inserts it into the set.
	 * 
	 * @param k Element key
	 * @param v Element value
	 * @return true, if element was added; false otherwise.
	 */
	public boolean add(int k, String v) {
		int i = this.getNewOpenHash(k);

		if (i == -1) return false;

		this.table[i] = new Element(k, v);
		return true;

	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		int i = this.getOpenHash(k);

		if (i == -1) return false;

		table[i] = new Element(Integer.MIN_VALUE, "");
		return true;

	}

	/**
	 * Finds the element.
	 * 
	 * @param k Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		return this.getOpenHash(k) != -1;
	}
	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */
	public String get(int k) {
		int i = this.getOpenHash(k);
		return i != -1 ? this.table[i].value : null;
	}


	private int getOpenHash(int k) {
		int hash = this.getHash(k);
		switch (this.c) {
			case LinearProbing: return this.linearProbing(hash, k);
			case QuadraticProbing: return this.quadraticProbing(hash, k);
			case DoubleHashing: return this.doubleHashing(hash, k);
		}
		return -1;
	}

	private int getNewOpenHash(int k) {
		int hash = this.getHash(k);
		k = Integer.MIN_VALUE;
		switch (this.c) {
			case LinearProbing: return this.linearProbing(hash, k);
			case QuadraticProbing: return this.quadraticProbing(hash, k);
			case DoubleHashing: return this.doubleHashing(hash, k);
		}
		return -1;
	}

	private int linearProbing(int hash, int key) {
		for (int i = 0; i < this.table.length; i++) {
			int l = (hash + i) %  this.table.length;
			if (this.table[l].key == key) {
				return l;
			}
		}
		return -1;
	}

	private int quadraticProbing(int hash, int key) {
		for (int i = 0; i < this.table.length; i++) {
			int l = (int)(hash + Math.pow(i, 2)) % this.table.length;
			if (this.table[l].key == key) {
				return l;
			}
		}
		return -1;
	}

	private int doubleHashing(int hash, int key) {
		for (int i = 0; i < this.table.length; i++) {
			int l = (hash + i * hash) % this.table.length;
			if (this.table[l].key == key) {
				return l;
			}
		}
		return -1;
	}

	private int getHash(int k) {
		switch (this.h) {
			case DivisionMethod:
				return HashFunction.DivisionMethod(k, this.table.length);
			case KnuthMethod:
				return HashFunction.KnuthMethod(k, this.table.length);
			default: return -1;
		}
	}
}

