package sorting;

public class QuickSort {

	public static <AnyType> void swapReferences(AnyType [] a, int index1, int index2) {
		AnyType tmp = a[ index1 ];
		a[ index1 ] = a[ index2 ];
		a[ index2 ] = tmp;
	}

	public static <AnyType extends Comparable<? super AnyType>>
	void quicksort(AnyType [] a) {
		quicksort( a, 0, a.length - 1 );
	}

	private static <AnyType extends Comparable<? super AnyType>>
	void quicksort( AnyType [ ] a, int left, int right ) {
		if (left >= right) return;

		int half = (left + right) / 2;
		AnyType pivot = a[half];
		swapReferences(a,half,right);

		int i = left-1, j = right;
		for( ; ; ) {
			while( i<right && a[ ++i ].compareTo( pivot ) < 0 ) { }
			while( j>left && a[ --j ].compareTo( pivot ) > 0) { }
			if( i < j )
				swapReferences(a,i,j);
			else
				break;
		}

		swapReferences(a,right,i);

		quicksort( a, left, i - 1 );    // Sort small elements
		quicksort( a, i + 1, right );   // Sort large elements
	}
}

