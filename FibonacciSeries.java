public class FibonacciSeries {
    static void fib_loop(int n) {
        int x = 0;
        int y = 1;
        int z = 0;
        System.out.print("[");
        while(z <= n) {
            System.out.print(z);
            x = y;
            y = z;
            z = x + y;
            if(z < n) System.out.print(", ");
            else if(z == n) {
                System.out.println(", " + z + "]");   
                break;
            } else 
                System.out.println("]");
        }
    }
    
    static void fib_recursive(int n, int z, int y, int x) {
        if(z <= n) {
            if(z == 0) System.out.print("[");
            System.out.print(z);
            x = y;
            y = z;
            z = x+y;
            if(z < n) System.out.print(", ");
            else if(z == n) {
                System.out.println(", " + z + "]");  
                return;
            } else 
                System.out.println("]");
            fib_recursive(n, z, y, x);
        }
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci Series using iterative method: ");
        long startTime = System.nanoTime();
        fib_recursive(53, 0, 1, 0);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total runtime of iterative method: " + String.format("%.4f", (totalTime * 1e-9)) + "seconds.\n");

        System.out.println("Fibonacci Series using recursive method: ");
        startTime = System.nanoTime();
        fib_loop(53);
        endTime = System.nanoTime();
        long rTotalTime = endTime - startTime;
        System.out.println("Total runtime of recursive method: " + String.format("%.4f", (rTotalTime * 1e-9)) + "seconds.\n");
        
        if(totalTime < rTotalTime)
            System.out.println("Iterative method took less time.");
        else
            System.out.println("Recursive method took less time.");
    }
}