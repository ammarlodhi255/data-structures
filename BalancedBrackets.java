/*
    Name: Ammar Ahmed
    CMS: 023-19-0107
    Lab 05 
    Data Structures
    Teacher: Mr. Saif Hassan
*/
public class BalancedBrackets {

    // Task 04: (Balanced Brackets)
    public static boolean checkBrackets(String seq) {
        StackLL s = new StackLL();
        for(int i = 0; i < seq.length(); i++) {
            if(seq.charAt(i) == '[' || seq.charAt(i) == '(' || seq.charAt(i) == '{') {
                s.push(seq.charAt(i));
            } else if(seq.charAt(i) == ']' || seq.charAt(i) == ')'
                    || seq.charAt(i) == '}')
            {
                if(s.isEmpty() || getReversedBracket(seq.charAt(i)) != s.pop())
                    return false;
            }
        }
        return s.isEmpty();
    }

    private static char getReversedBracket(char c) {
        if(c == ')')
            return '(';
        else if(c == '}')
            return '{';
        else
            return '[';
    }

    public static void main(String[] args) {
        String str = "({[a+b]+c}-1)";
        System.out.println(checkBrackets(str));
    }
}

class StackLL {
    private Node top;
    StackLL() { top = null; }

    private class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            next = null;
        }
    }

    public boolean isEmpty() { return top == null; }

    public char peek() {
        if(isEmpty())
            throw new RuntimeException("Stack UnderFlow");
        return top.data;
    }

    public void push(char x) {
        Node newest = new Node(x);
        if(isEmpty()) top = newest;
        else {
            newest.next = top;
            top = newest;
        }
    }

    public char pop() {
        if (isEmpty())
            throw new RuntimeException("Stack Underflow");
        char data = top.data;
        top = top.next;
        return data;
    }
}

