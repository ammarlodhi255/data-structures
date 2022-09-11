/*
*   Name: Ammar Ahmed
*   CMS: 023-19-0107
*   Sec: 'A'
*   Lab 08
*   Course: Data Structures and Applications
*/
package lab08_dsa;

public class CompleteBinaryTree <T> {
    private TreeNode root;
    private T[] elements;
    private int index;

    private static class TreeNode <T> {
        TreeNode left = null;
        TreeNode right = null;
        T data;

        TreeNode(T data) { this.data = data; }
    }

    CompleteBinaryTree() {
        root = null;
        index = -1;
        // Initially the capacity is 10
        elements = (T[]) new Object[10];
    }

    public boolean isEmpty() { return root == null; }

    public void insert(T data) {
        // Double the capacity if the space runs out.
        if(index+1 == elements.length) {
            T[] new_elements = (T[]) new Object[elements.length * 2];
            for(int i = 0; i < elements.length; i++)
                new_elements[i] = elements[i];
            elements = new_elements;
        }
        elements[++index] = data;
    }

    public void createCBT() {
        root = insert(elements, root, 0);
    }

    public TreeNode insert(T[] arr, TreeNode root, int i) {
        if (i <= index) {
            TreeNode temp = new TreeNode(arr[i]);
            root = temp;
            root.left = insert(arr, root.left, 2*i + 1);
            root.right = insert(arr, root.right, 2*i + 2);
        }
        return root;
    }

    public boolean search(T val) {
        return search(root, val);
    }

    private boolean search(TreeNode node, T val) {
        if(node == null) return false;
        else if(node.data.equals(val))
            return true;

        boolean foundInLeftSubtree =
            search(node.left, val);

        if(foundInLeftSubtree) return true;

        return search(node.right, val);
    }

    // Alternate method for searching:
    public boolean searchFromArray(T val) {
        if(isEmpty()) return false;
        for(int i = 0; i < index; i++) {
            if(elements[i].equals(val)) return true;
        }
        return false;
    }

    public boolean changeValue(T data, T val) {
        if(isEmpty() || !search(data))
            return false;
        return changeValue(root, data, val);
    }

    private boolean changeValue(TreeNode node, T data, T val) {
        if(node == null) return false;
        if(node.data.equals(data)) {
            node.data = val;
            return true;
        }
        boolean dataModified = changeValue(node.left, data, val);
        if(dataModified) return true;
        return changeValue(node.right, data, val);
    }

    public boolean remove(T val) {
        return remove(root, val);
    }

    /*
    *  In order to remove a tree node and still maintain the completeness of a binary tree,
    *  we first find the node to remove, then replace its value with last node that was inserted
    *  in the tree and then remove the last node.
    */
    private boolean remove(TreeNode node, T val) {
        if(node == null) return false;
        if(node.data.equals(val)) {
            int parent_idx = index % 2 == 0 ? (index/2) - 1 : (index/2);
            TreeNode parent_node = find(elements[parent_idx]);

            if(parent_idx == 0 && node == root)
                root = null;
            else if(parent_node.right == null) {
                node.data = parent_node.left.data;
                parent_node.left = null;
            } else {
                node.data = parent_node.right.data;
                parent_node.right = null;
            }
            return true;
        }

        boolean isOnLeft = remove(node.left, val);
        if(isOnLeft) return true;

        return remove(node.right, val);
    }

    public TreeNode find(T val) {
        return find(root, val);
    }

    private TreeNode find(TreeNode node, T val) {
        if(node == null) return null;
        else if(node.data.equals(val))
            return node;
        TreeNode node_found = find(node.left, val);
        if(node_found != null) return node_found;
        return find(node.right, val);
    }

    public void levelOrderTraversal() {
        if(isEmpty())
            System.out.println("Error: <BT> is empty");
        else {
            java.util.Queue<TreeNode> q = new java.util.LinkedList();
            q.offer(root);
            TreeNode trav;

            while(!q.isEmpty()) {
                trav = q.poll();
                if(trav != null) {
                    q.offer(trav.left);
                    q.offer(trav.right);
                    System.out.println(trav.data);
                }
            }
        }
    }

    public boolean isCompleteBinaryTree() {
        java.util.Queue<TreeNode> q = new java.util.LinkedList();
        q.offer(root);
        boolean nullFound = false;
        TreeNode trav;

        while(!q.isEmpty()) {
            trav = q.poll();
            if(trav == null) nullFound = true;
            else {
                if(nullFound) return false;
                q.offer(trav.left);
                q.offer(trav.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CompleteBinaryTree<String> cbt = new CompleteBinaryTree<>();

        cbt.insert("A");
        cbt.insert("B");
        cbt.insert("C");
        cbt.insert("D");
        cbt.insert("E");
        cbt.insert("F");
        cbt.insert("N");

        cbt.createCBT();
        cbt.levelOrderTraversal();
        System.out.println(cbt.isCompleteBinaryTree());
        System.out.println(cbt.search("G"));
        System.out.println(cbt.search("F"));
        System.out.println(cbt.searchFromArray("K"));

        cbt.changeValue("D", "K");

        cbt.remove("N");
        cbt.levelOrderTraversal();
    }
}
