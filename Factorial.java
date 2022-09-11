public class Factorial {
    static int factorial_loop(int n) {
        int m = 1;
        for(int i = 1; i <= n; i++) {
            m = m*i;
        }
        return m;
    }

    static int factorial_recursive(int n) {
        if(n == 1)
            return 1;
        return n * factorial_recursive(--n);
    }

    public static void main(String[] args) {
        System.out.println("6! using iterative method: ");
        long startTime = System.nanoTime();
        System.out.println(factorial_loop(6));
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total runtime of iterative method: " + String.format("%.4f", (totalTime * 1e-9)) + "seconds.\n");

        System.out.println("6! using recursive method: ");
        startTime = System.nanoTime();
        System.out.println(factorial_recursive(6));
        endTime = System.nanoTime();
        long rTotalTime = endTime - startTime;
        System.out.println("Total runtime of recursive method: " + String.format("%.4f", (rTotalTime * 1e-9)) + "seconds.\n");

        if(totalTime < rTotalTime)
            System.out.println("Iterative method took less time.");
        else
            System.out.println("Recursive method took less time.");
    }
}
