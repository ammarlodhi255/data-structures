/*
Name: Ammar Ahmed
CMS: 023-19-0107
Sec: 'A'
Course: Data Structures
*/
package lab07_dsa;

public class Hackerrank {
    private BinaryTree bst;
    Hackerrank(BinaryTree bst) { this.bst = bst; }

    public void postOrderTraversal(Node node) {
        if(node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    public void preOrderTraversal(Node node) {
        if(node == null) return;
        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void inOrderTraversal(Node node) {
        if(node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    public int height() {
        return height(bst.root);
    }

    private int height(Node node) {
        if(node == null) return 0;
        return Math.max( height(node.left), height(node.right) ) + 1;
    }
}
