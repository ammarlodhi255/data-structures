/*
Name: Ammar Ahmed
CMS: 023-19-0107
Sec: 'A'
Course: Data Structures
*/
package lab07_dsa;

public class BinaryTree {
    protected Node root;

    BinaryTree() {}
    BinaryTree(int key) {
        root = new Node(key);
    }

    public void addData(int data) {
        if(search(data))
            System.err.println("Error: <" + data + " already exists in the binary tree>");
        else
            root = add(data, root);
    }

    private Node add(int data, Node node) {
        if(node == null)
            node = new Node (data);
        else if(data > node.data)
            node.right = add(data, node.right);
        else if(data < node.data)
            node.left = add(data, node.left);
        return node;
    }

    // Optional method
    public void addIteratively(int data) {
        if(search(data))
            System.err.println("Error: <" + data + " already exists in the binary tree>");
        else {
            Node n = new Node(data);
            if(root == null) root = n;
            else {
                Node trav = root;
                while(trav.data != data) {
                    if(data > trav.data) {
                        trav.right = trav.right == null ? n : trav.right;
                        trav = trav.right;
                    } else {
                        trav.left = trav.left == null ? n : trav.left;
                        trav = trav.left;
                    }
                }
            }

        }
    }

    public boolean search(int data) {
        return search(data, root);
    }

    private boolean search(int data, Node node) {
        if(node == null) return false;
        else if(data == node.data) return true;
        else if(data > node.data)
            return search(data, node.right);
        else
            return search(data, node.left);
    }

    public static void main(String[] args) {
        BinaryTree bst = new BinaryTree();
        Hackerrank traversals = new Hackerrank(bst);

        bst.addData(56);
        bst.addData(67);
        bst.addData(12);
        bst.addData(5);
        bst.addData(198);
        bst.addData(68);
        bst.addData(0);
        bst.addData(45);
        bst.addData(467);
        bst.addData(900);
        bst.addData(900);
        traversals.inOrderTraversal(bst.root);
        System.out.println("\nHeight of the tree: " + traversals.height());
        System.out.println("is 0 in the tree: " + bst.search(0));
    }
}
