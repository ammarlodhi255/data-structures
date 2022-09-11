public class PrintingLinkedList {
    static void printNodesUsingLoop(Node head) {
        if(head == null) {
            System.out.println("[null]");
            return;
        } 
        Node trav = head;
        System.out.print("[");
        while(trav != null) {
            if(trav.next != null)
                System.out.print(trav.data + ", ");
            else    
                System.out.println(trav.data + "]");
            trav = trav.next;
        }
    }

    static void printNodesRecursively(Node head) {
        if(head.next != null) {
            System.out.print(head + ", ");
            printNodesRecursively(head.next);
        } else 
            System.out.println(head);
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast("Node0");
        list.addLast("Node1");
        list.addLast("Node2");
        list.addLast("Node3");
        list.addLast("Node4");

        System.out.println("Printing LinkedList using iterative method: ");
        long startTime = System.nanoTime();
        printNodesUsingLoop(list.head);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total runtime of iterative method: " + String.format("%.4f", (totalTime * 1e-9)) + "seconds.\n");
        
        System.out.println("Printing LinkedList using recursive method: ");
        startTime = System.nanoTime();
        printNodesRecursively(list.head);
        endTime = System.nanoTime();
        long rTotalTime = endTime - startTime;
        System.out.println("Total runtime of recursive method: " + String.format("%.4f", (rTotalTime * 1e-9)) + "seconds.\n");

        if(totalTime < rTotalTime)
            System.out.println("Iterative method took less time.");
        else
            System.out.println("Recursive method took less time.");
    }
}

class Node <T> {
    Node next;
    T data;

    Node(T data) { this.data = data; }

    public String toString() { return data.toString(); }
}

class LinkedList <T> {
    protected Node head, tail;

    LinkedList() { head = null; }

    public boolean isEmpty() { return head == null; }

    public void addFirst(T data) {
        Node newest = new Node(data);
        if(isEmpty())
            head = tail = newest;
        else {
            newest.next = head;
            head = newest;
        }
    }

    public void addLast(T data) {
        Node newest = new Node(data);
        if(isEmpty())
            head = tail = newest;
        else {
            tail.next = newest;
            tail = newest;
        }
    }

    public String toString() {
        if(isEmpty()) return "[null]";
        String liststr = "[";
        Node trav = head;
        while(trav != null) {
            if(trav.next != null)
                liststr += trav.data + ", ";
            else
                liststr += trav.data + "]";
            trav = trav.next;
        }
        return liststr;
    }
}