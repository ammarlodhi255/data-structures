/**
*   Implementation of AVL Tree, it is a Binary Search Tree that balances itself
*   for logarithmic operations.
*   @author Ammar
*/
import java.util.*;

interface PrintableNode {
    PrintableNode getLeft();
    PrintableNode getRight();
    String getText();
}

public class AVLTree {
    private Node root;

    private static class Node implements PrintableNode {
        Node left;
        Node right;
        int data;
        int bf; // Balance factor for this node

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
            bf = 0;
        }

        public PrintableNode getLeft() { return left; }
        public PrintableNode getRight() { return right; }
        public String getText() { return Integer.toString(data); }
    }

    AVLTree() { root = null; }
    AVLTree(int data) { root = new Node(data); }

    public boolean isEmpty() { return root == null; }
    public void display() { print(root); }

    public boolean add(int data) {
        if (contains(data)) return false;
        root = add(data, root);
        return true;
    }

    // O(log(n))
    private Node add(int data, Node node) {
        if (node == null)
            return new Node(data);
        else if (data > node.data)
            node.right = add(data, node.right);
        else
            node.left = add(data, node.left);
        updateBF(node);
        return balance(node);
    }

    public void updateBF(Node node) {
        int lh = (node.left  == null) ? -1 : height(node.left);
        int rh = (node.right == null) ? -1 : height(node.right);
        node.bf = (rh - lh);
    }

    public Node balance(Node node) {
        if(node.bf == -2) {
            if(node.left.bf <= 0)
                return rightRotation(node);
            else
                return leftRightRotation(node);
        } else if(node.bf == 2) {
            if(node.right.bf >= 0)
                return leftRotation(node);
            else
                return rightLeftRotation(node);
        }
        return node;
    }

    public Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;

        updateBF(node);
        updateBF(newParent);
        return newParent;
    }

    public Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;

        updateBF(node);
        updateBF(newParent);
        return newParent;
    }

    public Node leftRightRotation(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    public Node rightLeftRotation(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    public int height() { return height(root); }

    private int height(Node node) {
        if(node == null) return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public boolean contains(int data) {
        return contains(data, root);
    }

    private boolean contains(int data, Node node) {
        if (node == null) return false;
        else if (node.data == data) return true;
        else if (data > node.data)
            return contains(data, node.right);
        else
            return contains(data, node.left);
    }

    private Node digLeft(Node node) {
        if (node.left == null) return node;
        return digLeft(node.left);
    }

    private Node digRight(Node node) {
        if (node.right == null) return node;
        return digRight(node.right);
    }

    public boolean remove(int data) {
        if(!contains(data)) return false;
        root = remove(data, root);
        return true;
    }

    private Node remove(int data, Node node) {
        if(node.data == data && node.right == null)
            return node.left;
        else if(node.data == data && node.left == null)
            return node.right;
        else if(node.data == data) {
            int val = digRight(node.left).data;
            remove(val);
            node.data = val;
        }
        else if(data > node.data)
            node.right = remove(data, node.right);
        else
            node.left = remove(data, node.left);
        updateBF(node);
        return balance(node);
    }

    public static void main(String[] args) {
        AVLTree avlt = new AVLTree();

        avlt.add(15);
        avlt.add(18);
        avlt.display();
        avlt.add(9);
        avlt.display();
        avlt.add(10);
        avlt.display();
        avlt.add(14);
        avlt.display();
        /*avlt.display();
        avlt.add(10);
        avlt.display();
        avlt.remove(10);
        avlt.display();
        avlt.remove(18);
        avlt.display();
        avlt.remove(15);
        avlt.display();*/
    }

    // Prints the tree
    private static void print(PrintableNode root) {
        List<List<String>> lines = new ArrayList<List<String>>();
        List<PrintableNode> level = new ArrayList<PrintableNode>();
        List<PrintableNode> next = new ArrayList<PrintableNode>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();
            nn = 0;
            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getText();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;
            lines.add(line);
            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);
                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);
                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            perpiece /= 2;
        }
    }
}


