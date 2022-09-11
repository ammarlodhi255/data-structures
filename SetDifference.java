public class SetDifference {

    public static int[] setDifference(int[] a, int[] b) {
        if(a.length == 0)
            throw new RuntimeException("Error: A[] is empty");
        int[] c = new int[a.length];
        boolean isThere;
        int count = 0;
        for(int i = 0; i < a.length; i++) {
            isThere = false;
            for(int j = 0; j < b.length; j++) {
                if(a[i] == b[j]) {
                    isThere = true;
                    break;
                }
            }
            if(!isThere)
                c[count++] = a[i];
        }
        int[] newC = new int[count];
        for(int i = 0; i < count; i++)
            newC[i] = c[i];
        return newC;
    }

    public static void main(String[] args) {
        int[] a = {7, 12, 45, 56, 23, 9};
        int[] b = {45, 4, 7, 23, 9, 6};

        int[] c = setDifference(a, b);

        for(int x : c)
            System.out.print(x + " ");
    }
}
