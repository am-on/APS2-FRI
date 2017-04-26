package aps2.suffixarray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class PublicTests extends TestCase {
	SuffixArrayIndex sa;
	
	final String text = "mississippi";
	
	protected void setUp() throws Exception {
		sa = new SuffixArrayIndex(text);
	}
	
	public void testSuffixArray() {
		int[] resultingSA = new int[]{10,7,4,1,0,9,8,6,3,5,2};
		assertTrue(Arrays.equals(resultingSA, sa.getSuffixArray()));
	}

	public void testLocate() {
		String query = "s";
		Set<Integer> locations = new HashSet(Arrays.asList(2,3,5,6));

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateEmpty() {
		String query = "";
		Set<Integer> locations = new HashSet(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateNonExistingA() {
		String query = "a";
		Set<Integer> locations = new HashSet(Arrays.asList());

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateNonExisting() {
		String query = "z";
		Set<Integer> locations = new HashSet(Arrays.asList());

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateNonExistingN() {
		String query = "0";
		Set<Integer> locations = new HashSet(Arrays.asList());

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateFirst() {
		String query = "z";
		sa = new SuffixArrayIndex("zmissisipiq");
		Set<Integer> locations = new HashSet(Arrays.asList(0));

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateFirst2() {
		String query = "a";
		sa = new SuffixArrayIndex("amissisipiq");
		Set<Integer> locations = new HashSet(Arrays.asList(0));

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateLast() {
		String query = "z";
		sa = new SuffixArrayIndex("missisipiz");
		Set<Integer> locations = new HashSet(Arrays.asList(9));

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateLast2() {
		String query = "a";
		sa = new SuffixArrayIndex("zmissisipia");
		Set<Integer> locations = new HashSet(Arrays.asList(10));

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateLast3() {
		String query = "a";
		sa = new SuffixArrayIndex("azzzza");
		Set<Integer> locations = new HashSet(Arrays.asList(0, 5));

		assertEquals(locations, sa.locate(query));
	}

	public void testLocateLast4() {
		String query = "z";
		sa = new SuffixArrayIndex("zaaaaz");
		Set<Integer> locations = new HashSet(Arrays.asList(0, 5));

		assertEquals(locations, sa.locate(query));
	}

	public void testSpeed() {
		String x = "Wewillne'lltestthewholeapplication,coveringsecuritye'lltestthewholeapplication,coveringsectye'llt" +
				"estthewholeapplication,coveringsecuritye'lltestthewholeapplication,coveringsecuritye'lltestthewahole" +
				"application,coveringsecuritye'lltestthewholeapplication,coveringsecuritye'lltestthewholeapplication," +
				"coveringsecuritye'lltestthewholeapplication,coveringsecuritye'lltestthewholeapplication,coveringsecu" +
				"ritye'lltestthewholeapplication,coveringsecuritye'lltestthewholeapplication,coveringsecurityezrlltes" +
				"tthewholeapplication,coveringsecuritye'lltestthewholeapplication,coveringsecuritye'lltestthewholeapa" +
				"plication,coveringsecuritye'lltestthewholeapplication,coveringsecuritye'lltestthewholeapplication,co" +
				"eringsecuritye'lltestthewholeapplication,coveringsecuritye'lltestthewholeapplication,coveringsecurit" +
				"ye'lltestthewholeapplication,coveringsecuritye'lltestthewholeapplication,coveringsecuritye'lltestthew";
		sa = new SuffixArrayIndex(x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x);

	}




	public void testLongestRepeatedSubstring() {
		assertEquals("issi", sa.longestRepeatedSubstring());
	}
	
	public void testLongestCommonPrefixLen() {
		assertEquals(0, sa.longestCommonPrefixLen(0, 1)); // none
		assertEquals(1, sa.longestCommonPrefixLen(4, 7)); // i
		assertEquals(4, sa.longestCommonPrefixLen(1, 4)); // issi
	}
}
