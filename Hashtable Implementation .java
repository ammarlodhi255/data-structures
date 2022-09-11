public class Hashtable {
    public static void main(String[] args) {
        HashTableWithSeparateChaining hashtable = new HashTableWithSeparateChaining(40);
        hashtable.add("Ammar");
        hashtable.add("Muzamil");
        hashtable.add("Aziz");
        hashtable.add("Haseeb");
        hashtable.add("Adnan");
        hashtable.add("Erum");
        hashtable.add("Sundar");
        hashtable.add("Elon");
        hashtable.add("Salman");
        hashtable.add("Sehrish");
        hashtable.add("Aijaz");
        hashtable.add("Ahmed");
        hashtable.add("Abdul");
        hashtable.add("Hayi");
        hashtable.add("Suraksha");
        hashtable.add("Mohsin");
        hashtable.add("Hafeez");
        hashtable.add("Mohammad");
        hashtable.add("Ali");
        hashtable.add("Saif");
        hashtable.add("Khalil");
        hashtable.add("Kamran");
        hashtable.add("Edward");
        hashtable.add("Mathew");
        hashtable.add("Husnain");
        hashtable.add("Raza");
        hashtable.add("Tariq");
        hashtable.add("Hussain");
        hashtable.add("Nayab");
        hashtable.add("Zubair");
        hashtable.add("Liaqat");
        hashtable.add("Sami");
        hashtable.add("Saifullah");
        hashtable.add("Razaq");
        hashtable.add("Ravi");

        System.out.println(hashtable); // printing all names

        HashTableWithLinearProbing hashtable0 = new HashTableWithLinearProbing(40);
        hashtable0.add("Ammar");
        hashtable0.add("Muzamil");
        hashtable0.add("Aziz");
        hashtable0.add("Haseeb");
        hashtable0.add("Adnan");
        hashtable0.add("Erum");
        hashtable0.add("Sundar");
        hashtable0.add("Elon");
        hashtable0.add("Salman");
        hashtable0.add("Sehrish");
        hashtable0.add("Aijaz");
        hashtable0.add("Ahmed");
        hashtable0.add("Abdul");
        hashtable0.add("Hayi");
        hashtable0.add("Suraksha");
        hashtable0.add("Mohsin");
        hashtable0.add("Hafeez");
        hashtable0.add("Mohammad");
        hashtable0.add("Ali");
        hashtable0.add("Saif");
        hashtable0.add("Khalil");
        hashtable0.add("Kamran");
        hashtable0.add("Edward");
        hashtable0.add("Mathew");
        hashtable0.add("Husnain");
        hashtable0.add("Raza");
        hashtable0.add("Tariq");
        hashtable0.add("Hussain");
        hashtable0.add("Nayab");
        hashtable0.add("Zubair");
        hashtable0.add("Liaqat");
        hashtable0.add("Sami");
        hashtable0.add("Saifullah");
        hashtable0.add("Razaq");
        hashtable0.add("Ravi");

        System.out.println(hashtable0); // printing all names

        System.out.println(hashtable.contains("Ammar"));
        System.out.println(hashtable0.contains("Salman"));
    }
}

class HashTableWithLinearProbing {
    private final String[] entries;
    private final int capacity;
    private int size;

    HashTableWithLinearProbing(final int capacity) {
        this.entries = new String[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public void add(String key) {
        if(size == capacity)
            System.out.println("Error: capacity exceeded");
        else {
            int index = Math.abs(key.hashCode() % capacity);
            if(entries[index] == null)
                entries[index] = key;
            else {
                index = findNext(index);
                entries[index] = key;
            }
            size++;
        }
    }

    // Finds index of the next empty place inside the array.
    public int findNext(int index) {
        int i = (index+1) % capacity;
        while(i != index) {
            if(entries[i] == null) return i;
            i = (i+1) % capacity;
        }
        return -1;
    }

    public boolean contains(final String key) {
        int index = Math.abs(key.hashCode() % capacity);
        if(entries[index] == null) return false;
        return entries[index].equals(key);
    }

    public String toString() {
        String str = "";
        for(int i = 0; i < capacity; i++)
            if(entries[i] != null) str += entries[i] + " ";
        return str;
    }
}

class HashTableWithSeparateChaining {
    private final LinkedList[] entries;
    private final int capacity;

    HashTableWithSeparateChaining(final int capacity) {
        entries = new LinkedList[capacity];
        this.capacity = capacity;

        for(int i = 0; i < entries.length; i++)
            entries[i] = new LinkedList();
    }

    public void add(final String key) {
        int index = Math.abs(key.hashCode() % capacity);
        entries[index].add(key);
    }

    public boolean contains(final String name) {
        int hashCode = Math.abs(name.hashCode() % capacity);
        if(entries[hashCode] != null) {
            LinkedList.Node trav = entries[hashCode].getHead();
            while(trav != null) {
                if(trav.entry.equals(name)) return true;
                trav = trav.next;
            }
        }
        return false;
    }

    public String toString() {
        String str = "";
        for(int i = 0; i < entries.length; i++) {
            LinkedList.Node trav = entries[i].getHead();
            while(trav != null) {
                str += trav.entry + " ";
                trav = trav.next;
            }
        }
        return str;
    }

    private static class LinkedList {
        private Node head = null;
        private Node tail = null;

        static class Node {
            Node next;
            String entry;

            Node(final String entry) {
                this.entry = entry;
                this.next = null;
            }
        }

        public boolean isEmpty() { return head == null; }
        public Node getHead() { return head; }

        public void add(final String entry) {
            Node newest = new Node(entry);
            if(isEmpty()) head = tail = newest;
            else {
                tail.next = newest;
                tail = newest;
            }
        }
    }
}

