package lab10_dsa;
/**
 * Name: Ammar Ahmed
 * CMS: 023-19-0107
 * Section: 'A'
 * Course: Data Structures
 */
public class InsertAVL {
    private Node root = null;

    private static class Node {
        Node left;
        Node right;
        int key;
        int height;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            height = 0;
        }
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if(node == null)
            return new Node(key);
        else if(key < node.key)
            node.left = insert(node.left, key);
        else
            node.right = insert(node.right, key);
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node node) {
        node.height = height(node);
    }

    public int height(Node node) {
        if(node == null) return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public int getBalanceFactor(Node node) {
        int lh = (node.left == null)  ? -1 : height(node.left);
        int rh = (node.right == null) ? -1 : height(node.right);
        return (lh - rh);
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;

        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;

        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node leftRightRotation(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftRotation(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node balance(Node node) {
        int bf = getBalanceFactor(node);
        if(bf == 2) {
            if(getBalanceFactor(node.left) < 0)
                return leftRightRotation(node);
            else
                return rightRotation(node);
        } else if(bf == -2) {
            if(getBalanceFactor(node.right) > 0)
                return rightLeftRotation(node);
            else
                return leftRotation(node);
        } else
            return node;
    }

    public void levelOrder() {
        java.util.Queue<Node> q = new java.util.LinkedList<Node>();
        q.offer(root);
        Node trav;

        while(!q.isEmpty()) {
            trav = q.poll();
            if(trav != null) {
                System.out.print(trav.key + " ");
                q.offer(trav.left);
                q.offer(trav.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InsertAVL avl = new InsertAVL();
        avl.insert(3);
        avl.insert(2);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(-1);
        avl.insert(4);
        avl.insert(12);
        avl.insert(13);
        avl.insert(100);
        avl.insert(0);

        avl.levelOrder();
    }
}
