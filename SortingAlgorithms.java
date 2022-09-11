/**
 * SortingAlgorithms class contains method implementations of several sorting algorithms
 * with their time and space complexity mentioned.
 * Sorting is the process of arranging the comparable elements in a list or collection in an
 * increasing or decreasing manner with respect to some property.
 * @author Ammar Lodhi
 */

public class SortingAlgorithms {

    // Time complexity: O(n^2), Space: In-place memory usage
    public static void selectionSort(int[] arr) {
        int iMin;
        for(int i = 0; i < arr.length - 1; i++) {
            iMin = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[iMin] > arr[j]) iMin = j;
            }
            int temp = arr[i];
            arr[i] = arr[iMin];
            arr[iMin] = temp;
        }
    }

    // Time complexity: O(n^2), Space: In-place memory usage
    public static void bubbleSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    //Time complexity: O(n^2), space: In-place memory usage.
    public static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int spot = i;
            while(spot > 0 && arr[spot-1] > value) {
                arr[spot] = arr[spot-1];
                spot--;
            }
            arr[spot] = value;
        }
    }

    // Time complexity: O(nlog(n)), space complexity: Not in-place
    // the memory usage is proportional to n, O(n)
    public static void mergeSort(int[] arr) {
        if(arr.length <= 1) return;
        int mid = arr.length/2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        int i = 0, j = 0, k = 0;
        while(i < arr.length/2) {
            left[i] = arr[i];
            i++;
        }
        while(i < arr.length) {
            right[i-mid] = arr[i++];
        }

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while(i < left.length && j < right.length) {
            if(left[i] < right[j])
                arr[k++] = left[i++];
            else
                arr[k++] = right[j++];
        }

        while(i < left.length) {
            arr[k++] = left[i++];
        }
        while(j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Time complexity: O(n^2) worst, O(nlogn) on average,
    // space complexity: in-place sorting algorithm.
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        if(start >= end) return;
        int piv = partition(arr, start, end);
        quickSort(arr, start, piv-1);
        quickSort(arr, piv+1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int pIndex = start;
        for(int i = start; i < end; i++) {
            if(arr[i] < pivot) {
                int temp = arr[pIndex];
                arr[pIndex] = arr[i];
                arr[i] = temp;
                pIndex++;
            }
        }
        int temp = arr[end];
        arr[end] = arr[pIndex];
        arr[pIndex] = temp;
        return pIndex;
    }

    public static void main(String[] args) {
        int[] arr = {3, 12, 6, 2, 8, 5, 7};

        for(int i : arr) System.out.print(i + " ");
        System.out.println();

        //selectionSort(arr);
        //bubbleSort(arr);
        //insertionSort(arr);
        //mergeSort(arr);
        quickSort(arr);
        for(int i : arr) System.out.print(i + " ");
        System.out.println();
    }
}
