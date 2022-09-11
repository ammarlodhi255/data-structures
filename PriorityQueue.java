/*
*   Name: Ammar Ahmed
*   Course: Data Structures and Algorithms
*   Priority Queue Implementation Using unsorted arrays
*/
public class PriorityQueue {
    private int[] arr;
    private int current = -1;

    PriorityQueue() { arr = new int[10]; }

    public int capacity() { return arr.length; }
    public int size() { return current + 1; }
    public boolean isEmpty() { return size() == 0; }

    // Worst case: O(n), Avg ~O(1)
    public void insert(int priority) {
        if(size() == capacity()) {
            int[] newArr = new int[capacity() * 2];
            for(int i = 0; i < size(); i++)
                newArr[i] = arr[i];
            arr = newArr;
        }
        arr[++current] = priority;
    }

    // Worst case: O(n)
    public int extractMax() {
        int max = getMax();
        removeAt(getIndex(max));
        return max;
    }

    // Worst case: O(n)
    public int getMax() {
        if(isEmpty())
            throw new RuntimeException("Error: <priority queue empty>");
        int max = arr[0];
        for(int i = 1; i < size(); i++) {
            if(arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    // Worst case: O(n)
    private int getIndex(int elem) {
        if(!contains(elem))
            throw new RuntimeException("Error: <" + elem + " no such data in priority queue>");
        for(int i = 0; i < size(); i++)
            if(arr[i] == elem) return i;
        return -1;
    }

    // Worst case: O(n)
    public boolean contains(int elem) {
        if(isEmpty())
            throw new RuntimeException("Error: <empty priority queue>");
        for(int e : arr)
            if(e == elem) return true;
        return false;
    }

    // Worst case: O(n), Avg ~O(log(n))
    public void removeAt(int index) {
        if(isEmpty())
            throw new RuntimeException("Error: <empty priority queue>");
        for(int i = index; i < size(); i++) {
            if(i != size() - 1) 
                arr[i] = arr[i+1];
        }
        --current;
    }

    // Worst case: O(1)
    public void changePriority(int index, int priority) {
        if(index >= size() || index < 0)
            throw new RuntimeException("Error: <invalid index>");
        arr[index] = priority;
    }

    // Worst case: O(1)
    public int getPriority(int index) {
        if(index >= size() || index < 0)
            throw new RuntimeException("Error: <invalid index>");
        return arr[index];
    }
    
    // Worst case: O(n)
    public String toString() {
        if(isEmpty())
            throw new RuntimeException("Error: <empty priority queue>");
        String str = "[";
        for(int i = 0; i < size(); i++) 
            str += i == size() - 1 ? arr[i] + "]" : arr[i] + ", ";
        return str;
    }

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.insert(90);
        pq.insert(23);
        pq.insert(78);
        pq.insert(97);
        pq.insert(12);
        pq.insert(123);
        pq.insert(11);
        pq.insert(5);
        pq.insert(89);
        pq.insert(102);
        pq.insert(280);
        pq.insert(63);
        pq.insert(57);

        System.out.println(pq);
        System.out.println(pq.extractMax());
        System.out.println(pq);
        System.out.println(pq.extractMax());
        System.out.println(pq);
        System.out.println(pq.extractMax());
        System.out.println(pq);

        pq.changePriority(0, 1000);
        System.out.println(pq);
        System.out.println(pq.extractMax());
        System.out.println(pq);
    }
}