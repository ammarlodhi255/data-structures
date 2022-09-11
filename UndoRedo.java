public class UndoRedo {
    private Stack s1, s2;

    UndoRedo() {
        s1 = new Stack();
        s2 = new Stack();
    }

    public void showOutput() {
        if(s1.isEmpty())
            System.out.println("Stack is Empty");
        else
            System.out.println(s1.list);
    }

    public void insert(String data) {
        s1.push(data);
    }

    public void undo() {
        if(s1.isEmpty())
            System.out.println("Undo Unsuccessful");
        else {
            s2.push(s1.pop());
            System.out.println("Undo Successful");
        }
    }

    public void redo() {
        if(s2.isEmpty())
            System.out.println("Redo Unsuccessful");
        else {
            s1.push(s2.pop());
            System.out.println("Redo succesful");
        }
    }

    public static void main(String[] args) {
        UndoRedo undoRedoObj = new UndoRedo();
        char status = '0';
        char exitStatus = '0';
        int countInsert = 0;

        do {
            System.out.printf("\n1. %s\n2. %s\n3. %s\n4. %s\n",
            "Insert", "Undo", "Redo", "Display Stack");
            System.out.print("Enter here: ");

            status = new java.util.Scanner(System.in).next().charAt(0);
            if(status == '1') {
                System.out.printf("%s %d: ", "Input", ++countInsert);
                String toInsert = new java.util.Scanner(System.in).nextLine();
                undoRedoObj.insert(toInsert);
            } else if (status == '2') {
                undoRedoObj.undo();
            } else if(status == '3') {
                undoRedoObj.redo();
            } else if (status == '4') {
                System.out.print("Output: ");
                undoRedoObj.showOutput();
            }

            System.out.print("Show options again? y/n: ");
            exitStatus = new java.util.Scanner(System.in).next().charAt(0);
        } while(exitStatus != 'n');
    }
}

class LinkedList {
    Node head = null;
    Node tail = null;

    private static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }

    public boolean isEmpty() { return head == null; }

    public void addLast(String data) {
        Node newest = new Node(data);
        if(isEmpty()) head = tail = newest;
        else {
            tail.next = newest;
            tail = newest;
        }
    }

    public void addFirst(String data) {
        Node newest = new Node(data);
        if(isEmpty()) head = tail = newest;
        else {
            newest.next = head;
            head = newest;
        }
    }

    public String popFront() {
        if(isEmpty())
            throw new RuntimeException("Error: List is Empty");
        String data = head.data;
        head = head.next;
        return data;
    }

    public String toString() {
        String str = "[";
        Node trav = head;

        while(trav != null) {
            str += trav.next == null ? trav.data + "]" : trav.data + ", ";
            trav = trav.next;
        }
        return str;
    }
}

class Stack {
    protected LinkedList list;

    Stack() { list = new LinkedList(); }

    public boolean isEmpty() { return list.isEmpty(); }
    public void push(String elem) { list.addFirst(elem); }

    public String pop() {
        if(isEmpty())
            throw new RuntimeException("Error: Stack is empty");
        return list.popFront();
    }
}

