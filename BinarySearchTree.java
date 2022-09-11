/*
*   Name: Ammar Ahmed
*   Course: Data Structures and Algorithms
*   Binary Search Tree Implementation with BST invariant without same elements.
*/
public class BinarySearchTree {
    private Node root;

    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    BinarySearchTree() {
        root = null;
    }

    BinarySearchTree(int data) {
        root = new Node(data);
    }

    public boolean isEmpty() { return root == null; }
    public void deleteTree() { root = null; }

    public void add(int data) {
        if (contains(data))
            System.out.println("Error: " + data + " already exists");
        else
            root = add(data, root);
    }

    // O(n), Avg ~ O(log(n))
    private Node add(int data, Node node) {
        if (node == null)
            node = new Node(data);
        else if (data > node.data)
            node.right = add(data, node.right);
        else
            node.left = add(data, node.left);
        return node;
    }

    // O(n), Avg ~ O(log(n))
    public void addIteratively(int data) {
        if(isEmpty()) root = new Node(data);
        else if(contains(data))
            System.out.println("Error: cannot add " + data + " already exists in <BST>");
        else {
            Node trav = root, n = new Node(data);
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

    public boolean contains(int data) {
        return contains(data, root);
    }

    // O(n), Avg ~ O(log(n))
    private boolean contains(int data, Node node) {
        if (node == null) return false;
        else if (node.data == data) return true;
        else if (data > node.data)
            return contains(data, node.right);
        else
            return contains(data, node.left);
    }

    public Node find(int data) {
        if (!contains(data))
            throw new RuntimeException("Error: " + data + " does not exist");
        return find(data, root);
    }

    // O(n), Avg ~ O(log(n))
    private Node find(int data, Node node) {
        if (node == null) return null;
        else if (data == node.data) return node;
        else if (data > node.data)
            return find(data, node.right);
        else
            return find(data, node.left);
    }

    public Node digLeft() {
        if (isEmpty())
            throw new RuntimeException("Error: empty <BST>");
        return digLeft(root);
    }

    // OO(n), Avg ~ O(log(n))
    private Node digLeft(Node node) {
        if (node.left == null) return node;
        return digLeft(node.left);
    }

    public Node digRight() {
        if (isEmpty())
            throw new RuntimeException("Error: empty <BST>");
        return digRight(root);
    }

    // O(n), Avg ~ O(log(n))
    private Node digRight(Node node) {
        if (node.right == null) return node;
        return digRight(node.right);
    }

    public void remove(int data) {
        if(!contains(data))
            throw new RuntimeException("Error: " + data + " does not exists in the <BST>");
        root = remove(data, root);
    }

    private Node remove(int data, Node node) {
        if(node.data == data && node.right == null)
            node = node.left;
        else if(node.data == data && node.left == null)
            node = node.right;
        else if(node.data == data) {
            int val = digRight(node.left).data;
            remove(val);
            node.data = val;
        }
        else if(data > node.data)
            node.right = remove(data, node.right);
        else
            node.left = remove(data, node.left);
        return node;
    }

    public int getMax() {
        return digRight().data;
    }

    public int getMin() {
        return digLeft().data;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root);
    }

    private boolean isBinarySearchTree(Node node) {
        if(node == null) return true;
        if(isSubtreeLesser(node.left, node.data)
            && isSubtreeGreater(node.right, node.data)
            && isBinarySearchTree(node.left)
            && isBinarySearchTree(node.right))
            return true;
        return false;
    }

    private boolean isSubtreeLesser(Node node, int value) {
        if(node == null) return true;
        if(node.data < value && isSubtreeLesser(node.left, value)
            && isSubtreeLesser(node.right, value))
            return true;
        return false;
    }

    private boolean isSubtreeGreater(Node node, int value) {
        if(node == null) return true;
        if(node.data > value && isSubtreeGreater(node.left, value)
                && isSubtreeGreater(node.right, value))
            return true;
        else
            return false;
    }

    // Traversals:
    public void preOrder() { preOrder(root); }
    public void postOrder() { postOrder(root); }
    public void inOrder() { inOrder(root); }
    public void upOrder() { upOrder(root); }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void levelOrder() {
        if (isEmpty())
            System.out.println("Error: empty <BST>");
        else {
            java.util.Queue<Node> q = new java.util.LinkedList<Node>();
            Node trav;
            q.offer(root);
            while(!q.isEmpty()) {
                trav = q.poll();
                if(trav != null) {
                    System.out.print(trav.data + " ");
                    q.offer(trav.left);
                    q.offer(trav.right);
                }
            }
        }
    }

    /* Custom method for printing data in descending order:
    *  Opposite of inOrder traversal
    */
    private void upOrder(Node node) {
        if(node == null) return;
        upOrder(node.right);
        System.out.print(node.data + " ");
        upOrder(node.left);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(7);
        bst.add(5);
        bst.add(20);
        bst.add(18);
        bst.add(25);
        bst.add(11);
        bst.add(19);
        bst.add(33);
        bst.add(28);
        bst.add(31);
        bst.add(14);
        bst.add(12);
        bst.add(15);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(3);

        bst.inOrder();
       // bst.remove(100);
        System.out.println("\nIn Order: ");
        bst.inOrder();
        System.out.println("\nPre Order: ");
        bst.preOrder();
        System.out.println("\nPost Order: ");
        bst.postOrder();
        System.out.println("\nLevel Order: ");
        bst.levelOrder();
        System.out.println("\nUp Order: ");
        bst.upOrder();

        System.out.println("\nMax value in BST: " + bst.getMax());
        bst.remove(33);
        System.out.println("\nMax value in BST: " + bst.getMax());

        System.out.println("IsBinarySearchTree(): " + bst.isBinarySearchTree());
    }
}
