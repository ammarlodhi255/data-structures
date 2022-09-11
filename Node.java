package lab07_dsa;

public class Node {
    Node right;
    Node left;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
