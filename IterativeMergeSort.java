package sorting;
import java.util.Arrays;
/**
 * Name: Ammar Ahmed
 * CMS: 023-19-0107
 * Sec: 'A'
 * Course: Data Structures
 * Lab 11: Sorting
 */

public class IterativeMergeSort {

	public static <AnyType extends Comparable<? super AnyType>>
	void mergeSort(AnyType[] a) {
		AnyType[] aux = (AnyType[]) new Comparable[a.length];
		aux = Arrays.copyOf(a, a.length);

		for(int i = 1; i < a.length; i = 2*i) {
			for(int j = 0; j < a.length; j += 2*i) {
				int from = j;
				int high = Integer.min(j + 2*i - 1, a.length - 1);
				int mid = j+i-1;

				int k = from, m = from, n = mid + 1;

				while (m <= mid && n <= high) {
					if (a[m].compareTo(a[n]) < 0)
						aux[k++] = a[m++];
					else
						aux[k++] = a[n++];
				}

				while (m < a.length && m <= mid) aux[k++] = a[m++];

				for (m = from; m <= high; m++)
					a[m] = aux[m];
			}
		}
	}

	public static void main(String[] args) {
		Integer[] arr = {45, 3, 32, 23, 2, 0, -1};
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
