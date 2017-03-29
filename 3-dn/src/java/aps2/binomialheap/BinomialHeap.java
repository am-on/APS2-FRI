package aps2.binomialheap;

import java.util.Vector;

/**
 * This class is an implementation of the Binomial min-heap.
 */
public class BinomialHeap {
	Vector<BinomialNode> data; // list of root nodes
	int n;                     // number of elements
	
	BinomialHeap(){
		data = new Vector<BinomialNode>();
	}
	
	/**
	 * Inserts a new key to the binomial heap and consolidates the heap.
	 * Duplicates are allowed.
	 * 
	 * @param key Key to be inserted
	 * @return True, if the insertion was successful; False otherwise.
	 */
	public boolean insert(int key) {
	    data.add(new BinomialNode(key));
	    consolidate();
	    n++;
	    return true;
	}
	
	/**
	 * Returns the minimum element in the binomial heap. If the heap is empty,
	 * return the maximum integer value.
	 * 
	 * @return The minimum element in the heap or the maximum integer value, if the heap is empty.
	 */
	public int getMin() {
	    int min = Integer.MAX_VALUE;
        for (int i = 0; i < data.size(); i++) {
            int key = data.elementAt(i).getKey();
            if (key < min) {
                min = key;
            }
        }
        return min;
    }
	
	/**
	 * Find and removes the minimum element in the binomial heap and
	 * consolidates the heap.
	 * 
	 * @return True, if the element was deleted; False otherwise.
	 */
	public boolean delMin() {
	    if (n <= 0) return false;
        int delIndex = 0;
        BinomialNode min = data.elementAt(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.elementAt(i).getKey() < min.getKey()) {
                min = data.elementAt(i);
                delIndex = i;
            }
        }

        data.remove(delIndex);

        Vector<BinomialNode> childs = min.getChildren();

        for (int i = 0; i < childs.size(); i++) {
            data.addElement(childs.elementAt(i));
        }

        n--;

        consolidate();

        return true;
	}
	
	/**
	 * Merges two binomial trees.
	 * 
	 * @param t1 The first tree
	 * @param t2 The second tree
	 * @return Returns the new parent tree
	 */
	public static BinomialNode mergeTrees(BinomialNode t1, BinomialNode t2) {

        if (t1.compare(t2) >= 0) {
            t1.addChild(t2);
            return t1;
        }
        t2.addChild(t1);
        return t2;
	}
	
	/**
	 * This function consolidates the binomial heap ie. merges the binomial
	 * trees with the same degree into a single one.
	 * 
	 * @return True, if changes were made to the list of root nodes; False otherwise.
	 */
	private boolean consolidate() {

        for (int i = 0; i < data.size()-1; i++) {
            BinomialNode t1 = data.get(i);
            BinomialNode t2 = data.get(i+1);

            if (t1.getDegree() == t2.getDegree()) {
                BinomialNode t3 = mergeTrees(t1, t2);
                data.remove(i+1);
                data.set(i, t3);
                consolidate();
                return true;
            }
        }

        return false;
    }
}

