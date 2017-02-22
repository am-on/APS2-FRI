package aps2.reversestringfindmax;

public class ReverseStringFindMax {
	/**
	 * This function takes the string argument and reverses it.
	 *
	 * @param str Input string.
	 * @return Reverse version of the string or null, if str is null.
	 */
	public String reverseString(String str) {
		if (str==null) {
			return null;
		}
		String revStr = "";
		for (int i=0; i < str.length(); i++) {
			revStr = str.charAt(i) + revStr;
		}
		return revStr;
	}

	/**
	 * This function finds and returns the maximum element in the given array.
	 *
	 * @param arr Initialized input array.
	 * @return The maximum element of the given array, or the minimum Integer value, if array is empty.
	 */
	public int findMax(int[] arr){
		if (arr.length <= 0) {
			return Integer.MIN_VALUE;
		}
		int max = Integer.MIN_VALUE;
		for (int e:arr) {
			if (e > max) {
				max = e;
			}
		}
		return max;
	}
}
