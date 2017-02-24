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

	public void testBSTRemoveTreeTraverse() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.remove(4000));
		assertFalse(bst.contains(4000));
		assertEquals(Arrays.asList(1000, 2000, 3000, 5000, 6000), bst.traverseLevelOrder());
	}

	public void testBSTRemoveLeft() {
		bst.add(8000, "Ljubljana");
		bst.add(7000, "Maribor");
		bst.add(6000, "Celje");
		bst.add(5000, "Kranj");
		bst.add(4000, "Novo Mesto");
		bst.add(3000, "Koper");
		assertTrue(bst.remove(7000));
		assertFalse(bst.contains(7000));
	}

	public void testBSTRemoveLeftTreeTraverse() {
		bst.add(8000, "Ljubljana");
		bst.add(7000, "Maribor");
		bst.add(6000, "Celje");
		bst.add(5000, "Kranj");
		bst.add(4000, "Novo Mesto");
		bst.add(3000, "Koper");
		assertTrue(bst.remove(7000));
		assertFalse(bst.contains(7000));
		assertEquals(Arrays.asList(8000, 6000, 5000, 4000, 3000), bst.traverseLevelOrder());
	}

	public void testBSTRemoveNonExistent() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertFalse(bst.remove(8000));
		assertFalse(bst.contains(8000));
	}

	public void testBSTRemoveLast() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.remove(6000));
		assertFalse(bst.contains(6000));
	}

	public void testBSTRemoveLastTreeTraverse() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertTrue(bst.remove(6000));
		assertFalse(bst.contains(6000));
		assertEquals(Arrays.asList(1000, 2000, 3000, 4000, 5000), bst.traverseLevelOrder());
	}

	public void testBSTRemoveComplex() {
		bst.add(10, "Ljubljana");
		bst.add(6, "Maribor");
		bst.add(15, "Celje");
		bst.add(13, "Kranj");
		bst.add(12, "Novo Mesto");
		bst.add(14, "Koper");
		bst.add(18, "Ljubljana");
		bst.add(16, "Maribor");
		bst.add(26, "Celje");
		bst.add(19, "Kranj");
		bst.add(25, "Novo Mesto");
		bst.add(23, "Koper");
		bst.add(24, "Ljubljana");
		bst.remove(18);
		assertEquals(Arrays.asList(10, 6, 15, 13, 19, 12, 14, 16, 26, 25, 23, 24), bst.traverseLevelOrder());
		assertEquals(12, bst.traverseLevelOrder().size());
	}

	public void testBSTRemoveComplex2() {
		bst.add(10, "Ljubljana");
		bst.add(6, "Maribor");
		bst.add(15, "Celje");
		bst.add(13, "Kranj");
		bst.add(12, "Novo Mesto");
		bst.add(14, "Koper");
		bst.add(18, "Ljubljana");
		bst.add(16, "Maribor");
		bst.add(26, "Celje");
		bst.add(20, "Kranj");
		bst.add(19, "Kranj");
		bst.add(25, "Novo Mesto");
		bst.add(23, "Koper");
		bst.add(24, "Ljubljana");
		bst.remove(20);
		//assertEquals(12, bst.traverseLevelOrder().size());
		assertEquals(Arrays.asList(10, 6, 15, 13, 18, 12, 14, 16, 26, 23, 19, 25, 24), bst.traverseLevelOrder());

	}

	public void testBSTRemoveComplex3() {
		bst.add(10, "Ljubljana");
		bst.add(6, "Maribor");
		bst.add(15, "Celje");
		bst.add(13, "Kranj");
		bst.add(12, "Novo Mesto");
		bst.add(14, "Koper");
		bst.add(18, "Ljubljana");
		bst.add(16, "Maribor");
		bst.add(26, "Celje");
		bst.add(19, "Kranj");
		bst.add(25, "Novo Mesto");
		bst.add(23, "Koper");
		bst.add(24, "Ljubljana");
		bst.remove(19);
		assertEquals(Arrays.asList(10, 6, 15, 13, 18, 12, 14, 16, 26, 25, 23, 24), bst.traverseLevelOrder());
		assertEquals(12, bst.traverseLevelOrder().size());
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

	public void testBSTTraverseLevel() {
		bst.add(4000, "Kranj");
		bst.add(2000, "Maribor");
		bst.add(6000, "Koper");
		bst.add(1000, "Ljubljana");
		bst.add(3000, "Celje");
		bst.add(5000, "Novo Mesto");
		assertEquals(Arrays.asList(4000, 2000, 6000, 1000, 3000, 5000), bst.traverseLevelOrder());
	}

	public void testBSTMin() {
		bst.add(1000, "Ljubljana");
		bst.add(2000, "Maribor");
		bst.add(3000, "Celje");
		bst.add(4000, "Kranj");
		bst.add(600, "Min");
		bst.add(5000, "Novo Mesto");
		bst.add(6000, "Koper");
		assertEquals(bst.findMin().getValue(), "Min");
	}
}
