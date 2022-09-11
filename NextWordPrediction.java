/*
    CMS: 023-19-0107
    Name: Ammar Ahmed
    Mid1 Lab Exam (A)
    Data Structures
    Instructor: Mr. Saif Hassan
*/
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class NextWordPrediction {

    // stores all the text from a file into single string variable and returns it.
    public static String getData(File f0) {
        String str, text = new String();
        try {
            FileReader fr = new FileReader(f0);
            BufferedReader bufin = new BufferedReader(fr);

            while((str = bufin.readLine()) != null) {
                text += str;
            }
            bufin.close();
            fr.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return text;
    }

    // removing all the required symbols from a given string.
    public static String[] pre_process_string(String data) {
        return data.split("[,\\.:!@#\\$%\\^&\\*\\(\\)_\\+-=<>/\\?;\"\'\\{\\}\\[\\]\\\\|`~ ]+");
    }

    // creates linked list from the given string, storing each word of string in a node seperately.
    public static Node createLLFromData(String data) {
        String[] arr = pre_process_string(data);
        SinglyLinkedList list = new SinglyLinkedList();
        for(int i = 0; i < arr.length; i++) {
            list.addAtEnd(arr[i]);
        }
        return list.head;
    }   

    // returns all possible next two words after given two words.
    public static String[] predictNextWord(String word, Node head) {
        if(head == null)
            throw new RuntimeException("Error: Head is null");
        String[] words = word.split(" +");
        if(words.length != 2)
            throw new RuntimeException("Error: 2 words required, found " + words.length);

        Node trav = head;
        boolean found = false;
        while(trav.next != null) {
            if(trav.data.equals(words[0]) && trav.next.data.equals(words[1])) {
                found = true;
                break;
            }
            trav = trav.next;
        }
        
        ArrayList<String> arrlist = new ArrayList<>();
        if(found) {
            if(trav.next.next != null)
                trav = trav.next.next;
            else 
                throw new RuntimeException("Error: There are no other words to predict in the list after " + word);
            
            while(trav.next != null) {
                if(!trav.data.equals(words[0]) || !trav.next.data.equals(words[1]))
                    arrlist.add(trav.data + " " + trav.next.data);
                if(trav.next.next == null) 
                    break;
                else    
                    trav = trav.next.next;
            }
        } else 
            throw new RuntimeException("Error: " + "\"" + word + "\"" +  " not found in the list.");

        String[] allNextWords = new String[arrlist.size()];
        for(int i = 0; i < arrlist.size(); i++)
            allNextWords[i] = arrlist.get(i);
        return allNextWords;
    }

        // Main method:
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            File f = new File("D:\\data.txt.rtf");
            SinglyLinkedList list = new SinglyLinkedList(); 
            list.head = createLLFromData(getData(f));

            System.out.print("Enter two words: ");
            String input = scan.nextLine();

            String[] allWords = predictNextWord(input, list.head);
            
            System.out.print("[");
            for(int i = 0; i < allWords.length; i++) {
                if(i != allWords.length - 1)
                    System.out.print(allWords[i] + ", ");
                else    
                	System.out.println(allWords[i] + "]");
            }
        }   
}

class Node {
    String data;
    Node next;

    Node(String data) { 
        this.data = data; 
        next = null;
    }

    public String toString() { return data; }
}

class SinglyLinkedList {
    protected Node head, tail;

    SinglyLinkedList() { head = tail = null; }

    public boolean isEmpty() { return head == null; }

    public void addAtEnd(String data) {
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