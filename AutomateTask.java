public class AutomateTask {

    public static void automateTask(SinglyLinkedList even, SinglyLinkedList odd) {
        if(even.isEmpty() && odd.isEmpty()) 
            throw new RuntimeException("Error: Linked Lists are empty.");
            
        Node trav = null;
        if(!even.isEmpty()) {
            if(even.head.data % 2 != 0) {
                while(even.head != null && even.head.data % 2 != 0) {
                    odd.addAtEnd(even.head.data);
                    even.head = even.head.next;
                }
            }

            if(even.head != null) {
                trav = even.head;
                while(trav.next != null) {
                    if(trav.next.data % 2 != 0) {
                        odd.addAtEnd(trav.next.data);
                        trav.next = trav.next.next;
                    }
                    else
                        trav = trav.next;
                }
            }
        }

        if(!odd.isEmpty()) {
            if(odd.head.data % 2 == 0) {
                while(odd.head != null && odd.head.data % 2 == 0) {
                    even.addAtEnd(odd.head.data);
                    odd.head = odd.head.next;
                }
            }

            if(odd.head != null) {
                trav = odd.head;
                while(trav.next != null) {
                    if(trav.next.data % 2 == 0) {
                        even.addAtEnd(trav.next.data);
                        trav.next = trav.next.next;
                    }
                    else
                        trav = trav.next;
                }
            }
        }
    } 

    public static void main(String[] args) {
        SinglyLinkedList even = new SinglyLinkedList();
        SinglyLinkedList odd = new SinglyLinkedList();

        // Adding elements to even list:
        even.addAtEnd(38);
        even.addAtEnd(34);
        even.addAtEnd(53);
        even.addAtEnd(33);
        even.addAtEnd(37);
        even.addAtEnd(97);
        even.addAtEnd(66);
        even.addAtEnd(89);
        even.addAtEnd(90);
    
        // Adding elements to odd list:
        odd.addAtEnd(6);
        odd.addAtEnd(3);
        odd.addAtEnd(67);
        odd.addAtEnd(89);
        odd.addAtEnd(67);
        odd.addAtEnd(5);
        odd.addAtEnd(4);
        odd.addAtEnd(44);
        odd.addAtEnd(54);
        odd.addAtEnd(90);

        System.out.println("Before: ");
        System.out.println("Even list contains: " + even);
        System.out.println("Odd list contains: " + odd);

        automateTask(even, odd);
        
        System.out.println("\n\nAfter: ");
        System.out.println("Even list contains: " + even);
        System.out.println("Odd list contains: " + odd);

    }
}

class Node {
    int data;
    Node next;

    Node(int data) { 
        this.data = data; 
        next = null;
    }
}

class SinglyLinkedList {
    protected Node head, tail;

    SinglyLinkedList() { head = tail = null; }

    public boolean isEmpty() { return head == null; }

    public void addAtEnd(int data) {
        Node newest = new Node(data);
        if(isEmpty()) head = tail = newest;
        else {
            tail.next = newest;
            tail = newest;
        }
    }

    public void addFirst(int data) {
        Node newest = new Node(data);
        if(isEmpty()) head = tail = newest;
        else {
            newest.next = head;
            head = newest;
        }
    }

    public String toString() {
        if(isEmpty()) return "[null]";
        String str = "[";
        Node trav = head;

        while(trav != null) {
            if(trav.next != null)
                str += trav.data + ", ";
            else 
                str += trav.data + "]";
            trav = trav.next;
        }
        return str;
    }
}