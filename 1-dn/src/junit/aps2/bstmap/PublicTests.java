package aps2.bstmap;

import java.util.Arrays;
import junit.framework.TestCase;

public class PublicTests extends TestCase {
	private BSTMap bst;

	protected void setUp() throws Exception {
		bst = new BSTMap();
	}

	public void testBSTContainsGet() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.contains(4000));
		assertEquals(bst.get(4000), "Kranj");
	}
	
	public void testBSTNotContainsGet() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertFalse(bst.contains(7000));
		assertEquals(bst.get(7000), null);
	}
	
	public void testBSTRemove() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.remove(4000));
		assertFalse(bst.contains(4000));
	}
	
	public void testBSTNumberOfCompares() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		bst.resetCounter();
		bst.contains(5000);
		assertEquals(5, bst.getCounter());
	}
	
	public void testBSTTraversePreOrder() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(6000, "Koper");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		bst.add(5000, "Novo Mesto");
		assertEquals(Arrays.asList(4000, 2000, 1000, 3000, 6000, 5000), bst.traversePreOrder());
	}

	// Added Tests

	public void testBSTRemoveNonExistingNode() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertFalse(bst.remove(0));
		assertFalse(bst.contains(0));
	}

	public void testBSTRemoveLeafNode() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.remove(6000));
		assertFalse(bst.contains(6000));
	}

	public void testBSTRemoveRootNode() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(6000, "Koper");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		bst.add(5000, "Novo Mesto");
		assertTrue(bst.remove(4000));
		assertFalse(bst.contains(4000));
	}

	public void testBSTRemoveRootNode2() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		assertTrue(bst.remove(4000));
		assertFalse(bst.contains(4000));
		assertEquals(Arrays.asList(2000, 1000, 3000), bst.traversePreOrder());
	}

	public void testBSTRemoveNode2() {
		bst.add(5000, "Novo Mesto");
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		assertTrue(bst.remove(4000));
		assertFalse(bst.contains(4000));
		assertEquals(Arrays.asList(5000, 2000, 1000, 3000), bst.traversePreOrder());
	}

	public void testBSTRemoveNode2_1() {
		bst.add(0, "Novo Mesto");
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		assertTrue(bst.remove(4000));
		assertFalse(bst.contains(4000));
		assertEquals(Arrays.asList(0, 2000, 1000, 3000), bst.traversePreOrder());
	}

	public void testBSTRemoveRootNode3() {
		bst.add(4, "a");
		bst.add(8, "c");
		bst.add(5, "d");
		bst.add(6, "e");

		assertTrue(bst.remove(4));
		assertFalse(bst.contains(4));
		assertEquals(Arrays.asList(8, 5, 6), bst.traversePreOrder());
	}

	public void testBSTRemoveNode3() {
		bst.add(1, "R");
		bst.add(4, "a");
		bst.add(8, "c");
		bst.add(5, "d");
		bst.add(6, "e");

		assertTrue(bst.remove(4));
		assertFalse(bst.contains(4));
		assertEquals(Arrays.asList(1, 8, 5, 6), bst.traversePreOrder());
	}

	public void testBSTRemoveNode3_1() {
		bst.add(10, "R");
		bst.add(4, "a");
		bst.add(8, "c");
		bst.add(5, "d");
		bst.add(6, "e");

		assertTrue(bst.remove(4));
		assertFalse(bst.contains(4));
		assertEquals(Arrays.asList(10, 8, 5, 6), bst.traversePreOrder());
	}

	public void testBSTRemoveRootNode4() {
		bst.add(4, "a");
		bst.add(2, "b");
		bst.add(8, "c");
		bst.add(5, "d");
		bst.add(6, "e");

		assertTrue(bst.remove(4));
		assertFalse(bst.contains(4));
		assertEquals(Arrays.asList(5, 2, 8, 6), bst.traversePreOrder());
	}

	public void testBSTRemoveNode4() {
		bst.add(1, "R");
		bst.add(4, "a");
		bst.add(2, "b");
		bst.add(8, "c");
		bst.add(5, "d");
		bst.add(6, "e");

		assertTrue(bst.remove(4));
		assertFalse(bst.contains(4));
		assertEquals(Arrays.asList(1, 5, 2, 8, 6), bst.traversePreOrder());
	}

	public void testBSTRemoveNode4_1() {
		bst.add(10, "R");
		bst.add(4, "a");
		bst.add(2, "b");
		bst.add(8, "c");
		bst.add(5, "d");
		bst.add(6, "e");

		assertTrue(bst.remove(4));
		assertFalse(bst.contains(4));
		assertEquals(Arrays.asList(10, 5, 2, 8, 6), bst.traversePreOrder());
	}

	public void testBSTTraverseInOrder() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(6000, "Koper");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		bst.add(5000, "Novo Mesto");
		assertEquals(Arrays.asList(1000, 2000, 3000, 4000, 5000, 6000), bst.traverseInOrder());
	}

	public void testBSTTraversePostOrder() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(6000, "Koper");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		bst.add(5000, "Novo Mesto");
		assertEquals(Arrays.asList(1000, 3000, 2000, 5000, 6000, 4000), bst.traversePostOrder());
	}

	public void testBSTTraverseLevelOrder() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(6000, "Koper");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		bst.add(5000, "Novo Mesto");
		assertEquals(Arrays.asList(4000, 2000, 6000, 1000, 3000, 5000), bst.traverseLevelOrder());
	}

}
