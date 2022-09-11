import java.util.*;

public class PQueue {
    private int heapSize;
    private List heap;

    PQueue() {
        heap = new ArrayList<Integer>();
        heapSize = 0;
    }

    public boolean isEmpty() { return heapSize == 0; }
    public int size() { return heapSize; }

    public void clear() { 
        for(int i = 0; i < heapSize; i++)
            heap.set(i, null);
        heapSize = 0;
    }

    public int peek() {
        if(isEmpty()) 
            throw new RuntimeException("<Binary Heap> is empty");
        return (Integer) heap.get(0);
    }

    public boolean contains(int elem) {
        for(int i = 0; i < heapSize; i++)
            if(heap.get(i).equals(elem))
                return true;
        return false;
    }

    public void add(int elem) {
        heap.add(elem);
        heapSize++;
        swim(heapSize);
    }

    // Helper method, returns true if node at i <= node at j
    // This method assumes that i and j are valid indices, O(1)
    private boolean less(int i, int j) {
        int node_at_i = (Integer) heap.get(i);
        int node_at_j = (Integer) heap.get(j);
        return node_at_i <= node_at_j;
    }

    public void swim(int k) {
        int parent = (k-1) / 2;

        while(k > 0 && less(k, parent)) {
            swap(parent, k);
            k = parent;
            parent = (k-1) / 2;
        }
    }

    public void sink(int k) {
        while(true) {
            int left = 2*k + 1;
            int right = 2*k + 2;
            int smallest = left;

            if(right < heapSize && less(right, left))
                smallest = right;
            if(left >= heapSize || less(k, smallest)) break;

            swap(k, smallest);
            k = smallest;
        }
    }

    public void swap(int i, int j) {    
        int elem_at_i = (Integer) heap.get(i);
        int elem_at_j = (Integer) heap.get(j);
        heap.set(i, elem_at_j);
        heap.set(j, elem_at_i);
    }

    /* Construct a remove method
    public boolean remove(int elem) {
        if(!contains(elem))
            return false;
        for(int i = 0; i < heapSize; i++) {
            if(heap.get(i).equals(elem))
                
        }
    } 
    */
} 