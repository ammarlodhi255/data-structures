/*
    Name: Ammar Ahmed
    CMS: 023-19-0107
    Subject: Data Structures
    Instructor: Mr. Saif Hassan
    Final Lab Exam (A)
 */

public class MyHashTable {
    static int capacity = 10;
    static String[] names = new String[capacity];
    static int count = 0;

    public static int getHashCode(String name) {
        int sum = 0;
        for(int i = 0; i < name.length(); i++) {
            sum += name.charAt(i);
        }
        return Math.abs(sum % capacity);
    }

    public static void storeName(int hashCode, String name) {
        if(count == capacity) System.out.println("Capacity is full");
        else {
            if(names[hashCode] == null)
                names[hashCode] = name;
            else {
                int index = findNextSpot(hashCode);
                if(index == -1) {
                    System.out.println("Capacity is full");
                    return;
                } else {
                    names[index] = name;
                }
            }
            count++;
        }
    }

    private static int findNextSpot(int index) {
        int i = (index+1) % capacity;
        while(i != index) {
            if(names[i] == null) return i;
            i = (i + 1) % capacity;
        }
        return -1;
    }

    public static boolean searchValue(String name) {
        int index = getHashCode(name);
        if(names[index] == null) return false;
        // if value is found directly, return true
        if(names[index].equals(name)) return true;
        else { // otherwise, look for it in the next spot.
            int i = (index + 1) % capacity;
            while(i != index) {
                if(names[i] == null) {
                    i = (i+1) % capacity;
                    continue;
                }
                if(names[i].equals(name)) return true;
                i = (i+1) % capacity;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MyHashTable h = new MyHashTable();
        h.storeName(getHashCode("Ammar"), "Ammar");
        h.storeName(getHashCode("Ahmed"), "Ahmed");
        h.storeName(getHashCode("Adnan"), "Adnan");
        h.storeName(getHashCode("Aziz"), "Aziz");
        h.storeName(getHashCode("Mohsin"), "Mohsin");
        h.storeName(getHashCode("Raza"), "Raza");


        System.out.println("Ammar: " + h.searchValue("Ammar"));
        System.out.println("Hassan: " + h.searchValue("Hassan"));
        System.out.println("Hayee: " + h.searchValue("Hayee"));
        System.out.println("Mohsin: " + h.searchValue("Mohsin"));
        System.out.println("Raza: " + h.searchValue("Raza"));

        System.out.println(java.util.Arrays.toString(h.names));
    }
}
