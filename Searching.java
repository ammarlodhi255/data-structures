public class Searching {

    static int searchVal_iteratively(int[] arr, int n) {
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == n) return 1;
        return 0;
    }

    static int searchVal_recursively(int i, int[] arr, int n) {
        if(i < arr.length && i >= 0) {
            if(arr[i] == n) return 1;
            return searchVal_recursively(i+1, arr, n);
        } else
            return 0;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[15];
        java.util.Random rand = new java.util.Random();
        for(int i = 0; i < 15; i++)
            arr[i] = rand.nextInt(125);
        
        System.out.print("Enter value to find: ");
        int toFind = new java.util.Scanner(System.in).nextInt();

        long startTime = System.nanoTime();

        System.out.println(toFind + " is found in the array: " + 
        (searchVal_iteratively(arr, toFind) == 1 ? "true" : "false"));
        
        long endTime = System.nanoTime();
        double totalTime = (endTime - startTime) * (1e-9);
        
        System.out.println("searchVal_iteratively() took: " + 
        String.format("%.5f", totalTime) + " seconds.");

        startTime = System.nanoTime();
        searchVal_recursively(0, arr, toFind);
        endTime = System.nanoTime();
        double rTotalTime = (endTime - startTime) * (1e-9);

        System.out.println("searchVal_recursively() took: " + 
        String.format("%.5f", rTotalTime) + " seconds.");

        System.out.println(rTotalTime > totalTime ? "searchVal_recursively() took more time" :
        "searchVal_iteratively() took more time");
    }
}