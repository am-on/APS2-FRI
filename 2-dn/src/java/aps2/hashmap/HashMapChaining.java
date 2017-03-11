package aps2.hashmap;

import java.util.LinkedList;

/**
 * Hash map employing chaining on collisions.
 */
public class HashMapChaining {
	private LinkedList<Element> table[];
	private HashFunction.HashingMethod h;
	
	public HashMapChaining(int m, HashFunction.HashingMethod h) {
		this.h = h;
		this.table = new LinkedList[m];
		for (int i=0; i<table.length; i++) {
			table[i] = new LinkedList<Element>();
		}
	}
	
	public LinkedList<Element>[] getTable() {
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
		if(this.contains(k)) {
			return false;
		}
		int index = this.getHash(k);
		this.table[index].add(new Element(k, v));
		return true;
	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		if(!this.contains(k)) {
			return false;
		}
		int index = this.getHash(k);
		this.table[index].remove(new Element(k, ""));
		return true;
	}

	/**
	 * Finds the element.
	 * 
	 * @param k Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		int index = this.getHash(k);
		return this.table[index].contains(new Element(k, ""));
	}
	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */
	public String get(int k) {
		int index = this.getHash(k);
		Element e = new Element(k, "");
		for (int i = 0; i < this.table[index].size(); i++) {
			if (this.table[index].get(i).equals(e)) {
				return this.table[index].get(i).value;
			}
		}
		return null;
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

