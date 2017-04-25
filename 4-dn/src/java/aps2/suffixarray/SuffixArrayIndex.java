package aps2.suffixarray;

import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class SuffixArrayIndex {
	private String text; // input string
	private int[] SA;    // suffix array
    private Random rn;

	public int[] getSuffixArray() { return SA; }

	SuffixArrayIndex(String text) {
		this.text = text;
		this.SA = new int[text.length()];
		rn = new Random();
		construct();
	}

	/**
	 * Constructs the suffix array corresponding to the text in expected
	 * O(n log n) suffix comparisons.
	 */
	private void construct() {
	    // init SA with index values
        for (int i = 0; i < SA.length; i++) {
            SA[i] = i;
        }

        // sort SA
        quickSort(0, SA.length-1);



    }

    private void quickSort(int left, int right){
        if(left >= right){
            return;
        }

        int r = this.partition(left,right);

        this.quickSort(left,r-1);
        this.quickSort(r+1,right);
    }

    int partition(int left, int right){
        int p = left + rn.nextInt(right - left + 1);
        int pivot = SA[left];
        int l = left;
        int r = right+1;

        while (true){
            do {l++;} while (suffixSuffixCompare(SA[l], pivot) && l < right);
            do {r--;} while (suffixSuffixCompare(pivot, SA[r]));
            if(l >= r){
                break;
            }
            swap(l,r);
        }
        swap(left,r);

        return r;
    }

    private void swap(int i, int j) {
        int c = SA[i];
        SA[i] = SA[j];
        SA[j] = c;
    }



	/**
	 * Returns True, if the suffix at pos1 is lexicographically before
	 * the suffix at pos2.
	 *
	 * @param int pos1
	 * @param int pos2
	 * @return boolean
	 */
	public boolean suffixSuffixCompare(int pos1, int pos2) {
        String s1 = text.substring(pos1);
        String s2 = text.substring(pos2);



        int len = Math.min(s1.length(), s2.length());

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                return true;
            } else if (s1.charAt(i) > s2.charAt(i)) {
                return false;
            }
        }

        return s1.length() < s2.length();

	}

	/**
	 * Return True, if the query string is lexicographically lesser or
	 * equal to the suffix string at pos1.
	 *
	 * @param String query The query string
	 * @param int pos1 Position of the suffix
	 * @return boolean
	 */
	public boolean stringSuffixCompare(String query, int pos1) {
        String s = text.substring(pos1);
        return query.compareTo(s) >= 0;
	}

	/**
	 * Returns the positions of the given substring in the text using binary
	 * search. The empty query is located at all positions in the text.
	 *
	 * @param String query The query substring
	 * @return A set of positions where the query is located in the text
	 */
	public Set<Integer> locate(String query) {
        Set<Integer> s = new HashSet<>();

        int start = 0;
        int end = SA.length - 1;

        // binary search
        while (start <= end) {
            int middle = start + (end-start) / 2;
            if (stringSuffixCompare(query, SA[middle])) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        if (start >= SA.length)
            start = end;

        if(query.length() > 0 && text.charAt(SA[start]) != query.charAt(0)) {
            if (end < SA.length && end >= 0)
                start = end;
            else
                return s;
        }

        if (start < 0 || query.length() > 0 && text.charAt(SA[start]) != query.charAt(0)) {
            return s;
        }


        // ref is first matched prefix, use it for comparison with other prefixes
        int ref = SA[start];


        for (int i = start; i < SA.length; i++) {
            // add indexes to set as long as cpl is larger or equal to query length
            if (longestCommonPrefixLen(ref, SA[i]) >= query.length()) {
                s.add(SA[i]);
            } else {
                break;
            }
        }

        for (int i = start; i >= 0; i--) {
            if (longestCommonPrefixLen(ref, SA[i]) >= query.length()) {
                s.add(SA[i]);
            } else {
                break;
            }
        }

        return s;
	}

	/**
	 * Returns the longest substring in the text which repeats at least 2 times
	 * by examining the suffix array.
	 *
	 * @return The longest repeated substring in the text
	 */
	public String longestRepeatedSubstring() {
		int longest = 0;
        String lSubstr = "";
        for (int i = 0; i < SA.length-1; i++) {
            int lcp = longestCommonPrefixLen(SA[i], SA[i+1]);
            if (lcp > longest) {
                longest = lcp;
                lSubstr = text.substring(SA[i], SA[i]+lcp);
            }
        }
        return lSubstr;
    }

	/**
	 * Calculates the length of the longest common prefix of two suffixes.
	 *
	 * @param int pos1 Position of the first suffix in the text
	 * @param int pos2 Position of the second suffix in the text
	 * @return The number of characters in the common prefix of the first and the second suffix
	 */
	public int longestCommonPrefixLen(int pos1, int pos2) {
        String s1 = text.substring(pos1);
        String s2 = text.substring(pos2);
        int lcp = 0;

        // get length of the shortest word
        int len = Math.min(s1.length(), s2.length());

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                lcp++;
            } else {
                break;
            }
        }
        return lcp;
    }
}
