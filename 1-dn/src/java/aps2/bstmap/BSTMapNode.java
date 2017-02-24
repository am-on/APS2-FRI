package aps2.bstmap;

import java.util.LinkedList;
import java.util.List;


/**
 * Implementation of the (unbalanced) Binary Search Tree set node.
 */
public class BSTMapNode {
	private static int counter;
	private BSTMapNode left, right, parent;
	private int key;
	private String value;

	public BSTMapNode(BSTMapNode l, BSTMapNode r, BSTMapNode p,
					  int key, String value) {
		super();
		this.left = l;
		this.right = r;
		this.parent = p;
		this.key = key;
		this.value = value;
	}

	public BSTMapNode getLeft() {
		return left;
	}

	public void setLeft(BSTMapNode left) {
		this.left = left;
	}

	public BSTMapNode getRight() {
		return right;
	}

	public void setRight(BSTMapNode right) {
		this.right = right;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int compare(BSTMapNode node) {
		counter++;
		return node.key - this.key;
	}

	public int getCounter() {
		return counter;
	}

	public void resetCounter() {
		counter = 0;
	}

	/**
	 * If the element doesn't exist yet, adds the given element to the subtree.
	 *
	 * @param element Given key/value wrapped inside an empty BSTNode instance
	 * @return true, if the element was added; false otherwise.
	 */
	public boolean add(BSTMapNode element) {
		element.parent = this;
		if (this.compare(element) > 0) {
			if (this.getRight() == null) {
				this.right = element;
				return true;
			} else {
				return this.getRight().add(element);
			}
		} else {
			if (this.getLeft() == null) {
				this.left = element;
				return true;
			} else {
				return this.getLeft().add(element);
			}
		}
	}

	/**
	 * Finds and removes the element with the given key from the subtree.
	 *
	 * @param element Given key wrapped inside an empty BSTNode instance
	 * @return true, if the element was found and removed; false otherwise.
	 */
	 public boolean remove(BSTMapNode element) {
		int cmp = this.compare(element);

		// search for element to remove
		if (cmp > 0) {
			if (this.getRight() != null){
				return this.getRight().remove(element);
			}
		} else if (cmp < 0) {
			if (this.getLeft() != null) {
				return this.getLeft().remove(element);
			}
		}

		// remove element
		if (cmp == 0) {
			if (this.getRight() != null && this.getLeft() != null) { // find replacement in right subtree
				BSTMapNode replacement = this.getRight().findMin();

				if (replacement.getRight() != null && this.compare(replacement.parent) != 0) {
					replacement.getRight().parent = replacement.parent;
					replacement.parent.setLeft(replacement.getRight());
				} else if (this.compare(replacement.parent) == 0) {
					this.setRight(replacement.getRight());
				} else{
					replacement.parent.setLeft(null);
				}

				this.setKey(replacement.getKey());
				this.setValue(replacement.getValue());

			} else if (this.getLeft() != null) {
				this.setKey(this.getLeft().getKey());
				this.setValue(this.getLeft().getValue());
				this.setRight(this.getLeft().getRight());
				this.setLeft(this.getLeft().getLeft());

				if(this.getLeft() != null) {
					this.getLeft().parent = this;
				}
				if(this.getRight() != null) {
					this.getRight().parent = this;
				}
			} else if (this.getLeft() == null && this.getRight() != null) {
				this.setKey(this.getRight().getKey());
				this.setValue(this.getRight().getValue());
				this.setLeft(this.getRight().getLeft());
				this.setRight(this.getRight().getRight());
			} else {
				if (this.compare(this.parent) > 0) { // if deleted node is left child
					this.parent.setLeft(null);
				} else {
					this.parent.setRight(null);
				}
			}

			return true;
		}

			return false;
	}


	/**
	 * Checks whether the element with the given key exists in the subtree.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return true, if an element with the given key is contained in the subtree; false otherwise.
	 */
	public boolean contains(BSTMapNode element) {
		int cmp = this.compare(element);
		return cmp == 0
				|| (cmp < 0 && this.getLeft() != null && this.getLeft().contains(element))
				|| (this.getRight() != null && this.getRight().contains(element));
	}
	
	/**
	 * Maps the given key to its value.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return String value of the given key; null, if an element with the given key does not exist in the subtree.
	 */
	public String get(BSTMapNode element) {
		if (this.compare(element) == 0) {
			return this.getValue();
		}
		String str = null;
		if (this.getLeft() != null) {
			str = this.getLeft().get(element);
		}
		if (str == null && this.getRight() != null) {
			str = this.getRight().get(element);
		}
		return str;
	}

	/**
	 * Finds the smallest element in the subtree.
	 * 
	 * @return Smallest element in the subtree
	 */
	public BSTMapNode findMin() {
		if (this.getLeft() == null) {
		    return this;
        }
        return this.getLeft().findMin();
	}
	
	/**
	 * Depth-first pre-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by pre-order traversing the tree.
	 */
	List<Integer> traversePreOrder() {
		List<Integer> list = new LinkedList<>();
		list.add(this.getKey());
		if (this.getLeft() != null) {
			list.addAll(this.getLeft().traversePreOrder());
		}
		if (this.getRight() != null) {
			list.addAll(this.getRight().traversePreOrder());
		}
		return list;
	}

	/**
	 * Depth-first in-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by in-order traversing the tree.
	 */
	List<Integer> traverseInOrder() {
		List<Integer> list = new LinkedList<>();
		if (this.getLeft() != null) {
			list.addAll(this.getLeft().traverseInOrder());
		}
		list.add(this.getKey());
		if (this.getRight() != null) {
			list.addAll(this.getRight().traverseInOrder());
		}
		return list;
	}

	/**
	 * Depth-first post-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by post-order traversing the tree.
	 */
	List<Integer> traversePostOrder() {
		List<Integer> list = new LinkedList<>();
		if (this.getLeft() != null) {
			list.addAll(this.getLeft().traversePostOrder());
		}
		if (this.getRight() != null) {
			list.addAll(this.getRight().traversePostOrder());
		}
		list.add(this.getKey());
		return list;
	}

	/**
	 * Breadth-first (or level-order) traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by breadth-first traversal of the tree.
	 */
	List<Integer> traverseLevelOrder() {
        List<Integer> list = new LinkedList<>();
        List<BSTMapNode> q = new LinkedList<>();
        q.add(this);
        while (!q.isEmpty()) {
            BSTMapNode e = q.get(0);
            q.remove(0);
            list.add(e.getKey());
            if (e.getLeft() != null) {
                q.add(e.getLeft());
            }
            if (e.getRight() != null) {
                q.add(e.getRight());
            }
        }
        return list;
	}

}
