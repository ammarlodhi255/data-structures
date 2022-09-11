/*
    Name: Ammar Ahmed
    CMS: 023-19-0107
    SEC: 'A'
    Course: Data Structures
*/

public class SimpleRecursion {

    public static void getAscendingNums(int n) {
        if(n > 0) {
            getAscendingNums(--n);
            System.out.println(n + 1);
        }
    }

    public static void getDescendingNums(int n) {
        if(n > 0) {
            System.out.println(n);
            getDescendingNums(--n);
        }
    }

    public static void getAscDesc(int n) {
        if(n > 0) {
            System.out.println(n);
            getAscDesc(--n);
            System.out.println(n + 1);
        }
    }

    public static void printCharArrayForward(char[] c, int i) {
        if(i <= c.length-1) {
            System.out.println(c[i]);
            printCharArrayForward(c, ++i);
        }
    }

    public static void printCharArrayBackwards(char[] c, int i) {
        if(i <= c.length - 1) {
            printCharArrayBackwards(c, ++i);
            System.out.println(c[i - 1]);
        }
    }

    public static void main(String[] args) {
        getAscendingNums(20);
        getDescendingNums(20);
        getAscDesc(20);

        char[] c = {'A', 'b', 'C', 'v'};
        printCharArrayForward(c, 0);
        printCharArrayBackwards(c, 0);
    }
}
