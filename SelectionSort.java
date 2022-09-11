public class SelectionSort {

    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String args[]) {
        int[] arr = {2, 5, 1, 0, 10, 3};
        selectionSort(arr);

        for(int i = 0; i < arr.length; i++) {
            if(i != arr.length - 1)
                System.out.print(arr[i] + ", ");
            else 
                System.out.println(arr[i]);
        }
    }
}
