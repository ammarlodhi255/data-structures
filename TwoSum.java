package sorting;
/**
 * Name: Ammar Ahmed
 * CMS: 023-19-0107
 * Sec: 'A'
 * Course: Data Structures
 * Lab 11: Sorting
 */

public class TwoSum {
	//Worst case: O(nlogn)
	public static boolean twoSum(int[] a, int k) {
		mergeSort(a);
		for(int i = 0; i < a.length; i++) {
			int nearIdx = binarySearch(a, k - a[i]);
			if(nearIdx >= 0) {
				if(nearIdx != i || (i > 0 && a[i-1] == arr[i])
				|| (i < arr.length - 1 && arr[i+1] == arr[i]))
					return true;
			}
		}
		return false;
	}

	// O(logn)
	private static int binarySearch(int[] a, int val) {
		int low = 0;
		int high = a.length - 1;
		int mid = 0;

		while(low <= high) {
			mid = (low + high) / 2;

			if(arr[mid] == val) return mid;
			else if(arr[mid] > val)
				high = mid-1;
			else if(arr[mid] < val)
				low = mid + 1;
		}
		return -1;
	}

	// O(nlogn)
	private static void mergeSort(int[] a) {
		if(a.length <= 1) return;
		int mid = a.length/2;
		int[] left = new int[mid];
		int[] right = new int[a.length - mid];

		int i = 0;
		while(i < mid)
			left[i] = a[i++];
		while(i < a.length)
			right[i-mid] = a[i++];

		mergeSort(left);
		mergeSort(right);

		int j = 0, k = 0;
		i = 0;
		while(i < left.length && j < right.length) {
			if(left[i] < right[j])
				a[k++] = left[i++];
			else
				a[k++] = right[j++];
		}

		while(i < left.length)
			a[k++] = left[i++];
		while(j < right.length)
			a[k++] = right[j++];
	}
}
