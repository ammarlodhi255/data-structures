/*
    Name: Ammar Ahmed
    CMS: 023-19-0107
    Sec: 'A'
    Course: Data Structures
    Date: 25-9-2020
*/
interface List {
    public boolean isEmpty();
    public int size();
    public void add(Object data);
    public void add(int index, Object data);
    public void remove(int index);
    public void remove(Object data);
    public List duplicate();
    public List duplicateReversed();
}

public class LinkedList implements List {
    private Node head;
    private int size = 0;

    private class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            next = null;
        }

        public String toString() { return data.toString(); }
    }

    public LinkedList() {
        head = new Node(null);
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void add(Object data) {
        if(isEmpty()) head = new Node(data);
        else {
            Node trav = head; 
            while(trav.next != null)
                trav = trav.next;
            trav.next = new Node(data);
        }
        size++;
    }

    public void add(int index, Object data) {
        if(index == 1) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        } else {
            Node trav = head;
            for(int i = 2; i < index; i++)
                trav = trav.next;
            Node tempNode = trav.next;
            trav.next = new Node(data);
            trav.next.next = tempNode;
        }
        size++;
    }

    public void remove(int index) {
        if(index < 1 || index > size)
            throw new IndexOutOfBoundsException();
        else if(index == 1)
            head = head.next;
        else {
            Node trav = head;
            for(int i = 1; i < index-1; i++) trav = trav.next;
            trav.next = trav.next.next;
        }
        size--;
    }

    public void remove(Object data) {
        Node trav = head;
        int i = 1;
        for(; i <= size(); i++) {
            if(trav.data.equals(data)) break;
            trav = trav.next;
        }
        this.remove(i);
    }

    public Node getNodeAt(int index) {
        Node trav = head;
        for(int i = 1; i < index; i++)
            trav = trav.next;
        return trav;
    }

    public List duplicate() {
        List list = new LinkedList();
        Node trav = head;
        while(trav != null) {
            list.add(trav);
            trav = trav.next;
        }
        return list;
    }

    public List duplicateReversed() {
        List list = new LinkedList();
        for(int i = size; i > 0; i--)
            list.add(getNodeAt(i));
        return list;
    }

    public String toString() {
        Node trav = head;
        String str = "[Size: " + size() + " - ";
        while(trav != null) {
            if(trav.next != null)
                str += trav.data + ", ";
            else
                str += trav.data + "]";
            trav = trav.next;
        }
        return str;
    }

    // Testing Linked List:
    public static void main(String[] args) {
        LinkedList lnlist = new LinkedList();
        lnlist.add("Ammar");
        lnlist.add("Ahmed");
        lnlist.add("Structures");
        lnlist.add(3, "String");

        System.out.println(lnlist);
        lnlist.remove("Ahmed");
        lnlist.remove(lnlist.size());
        System.out.println(lnlist);

        lnlist.add("Singly Linked List");
        lnlist.remove("Ammar");

        System.out.println(lnlist);

        lnlist.add("Acceptability");
        lnlist.add("Dependability and Security");
        lnlist.add("Maintainability");
        lnlist.add(3, "Efficiency");
        System.out.println("Original: " + lnlist);
        System.out.println("Duplicate: " + lnlist.duplicate());
        List listRev = lnlist.duplicateReversed();
        System.out.println("Reversed: " + listRev);
    }
}
