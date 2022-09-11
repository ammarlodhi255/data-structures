/*
*   Name: Ammar Ahmed
*   CMS: 023-19-0107
*   Sec: 'A'
*   Lab 08
*   Course: Data Structures and Applications
*/
package lab08_dsa;

public class PriorityQueueUsingSortedArray {
    private int counter;
    private int[] sorted_elements;

    PriorityQueueUsingSortedArray (int capacity) {
        counter = -1;
        sorted_elements = new int[capacity];
    }

    public boolean isFull() { return size() == capacity(); }
    public boolean isEmpty() { return size() == 0; }
    public int size() { return counter + 1; }
    public int capacity() { return sorted_elements.length; }

    // Insert an element into a sorted array: O(n)
    public void insert(int elem) {
        if(isFull())
            System.out.println("Error: <PQ> is full!");
        else {
            int index = indexOfNextLarger(elem);
            if(index != -1) {
                int i = size();
                while(i != index) {
                    sorted_elements[i] = sorted_elements[i-1];
                    i--;
                }
                sorted_elements[index] = elem;
                counter++;
            } else
                sorted_elements[++counter] = elem;
        }
    }

    // private method used in insert()
    private int indexOfNextLarger(int elem) {
        int idx = -1;
        for(int i = 0; i < size(); i++) {
            if(sorted_elements[i] >= elem) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    // Removing and returning maximum value from the sorted array: O(1)
    public int extractMax() {
        if(isEmpty())
            throw new RuntimeException("Error: <PQ> is empty");
        int val = getMax();
        counter--;
        return val;
    }

    public int getMax() {
        if(isEmpty())
            throw new RuntimeException("Error: <PQ> is empty");
        return sorted_elements[counter];
    }

    public void changePriority(int index, int new_priority) {
        if(index < 0 || index >= counter)
            throw new RuntimeException("Error: IndexOutOfBounds");
        if(isEmpty()) throw new RuntimeException("Error: IndexOutOfBounds");
        sorted_elements[index] = new_priority;
    }

    public boolean searchData(int data) {
        if(isEmpty()) return false;
        for(int i = 0; i < counter; i++)
            if(sorted_elements[i] == data) return true;
        return false;
    }

    public void printAll() {
        for(int i = 0; i < size(); i++)
            System.out.println(sorted_elements[i]);
    }

    public static void main(String[] args) {
        PriorityQueueUsingSortedArray pq =
            new PriorityQueueUsingSortedArray(10);

        pq.insert(5);
        pq.insert(3);
        pq.insert(55);
        pq.insert(45);
        pq.insert(3);
        pq.insert(1);
        pq.insert(0);

        pq.printAll();

        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
    }
}
