/*
*   Name: Ammar Ahmed
*   CMS: 023-19-0107
*   Sec: 'A'
*   Lab 08
*   Course: Data Structures and Applications
*/
package lab08_dsa;

public class PriorityQueueUsingUnsortedArray {
    private int counter;
    private int[] elements;

    PriorityQueueUsingUnsortedArray(int capacity) {
        counter = -1;
        elements = new int[capacity];
    }

    public boolean isFull() { return size() == capacity(); }
    public boolean isEmpty() { return size() == 0; }
    public int size() { return counter + 1; }
    public int capacity() { return elements.length; }
    
    // Insert into an unsorted array: O(1)
    public void insert(int elem) {
        if(isFull())
            System.out.println("<PQ> is full");
        else 
            elements[++counter] = elem;
    }

    public int getMax() {
        if(isEmpty())
            throw new RuntimeException("<PQ> is empty!");
        int val = elements[0];
        for(int i = 0; i < size(); i++) {
            if(elements[i] > val)
                val = elements[i];
        }
        return val;
    }

    // Remove and return maximum value from an unsorted array: O(n)
    public int extractMax() {
        int val = getMax();
        int index = getIndexOf(val);

        // removal of the maximum value currently in the queue.
        removeAt(index);
        return val;
    }

    public void removeAt(int index) {
        for(int i = index; i < size()-1; i++) 
            elements[i] = elements[i+1];
        counter--;
    }

    public void changePriority(int index, int new_priority) {
        if(index < 0 || index >= counter) 
            throw new RuntimeException("Error: IndexOutOfBounds");
        if(isEmpty()) throw new RuntimeException("Error: IndexOutOfBounds");
        elements[index] = new_priority;
    }

    public boolean searchData(int data) {
        if(isEmpty()) return false;
        for(int i = 0; i < counter; i++)
            if(elements[i] == data) return true;
        return false;
    }

    private int getIndexOf(int elem) {
        int index = -1;
        for(int i = 0; i < size(); i++) {
            if(elem == elements[i]) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        PriorityQueueUsingUnsortedArray pq = 
            new PriorityQueueUsingUnsortedArray(10);

        pq.insert(12);
        pq.insert(13);
        pq.insert(16);
        pq.insert(44);
        pq.insert(67);
        pq.insert(78);
        pq.insert(2);

        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
        System.out.println("extractMax(): " + pq.extractMax());
    }
}