package Lab3;

public class BSTSet {
    private TNode root;

    public BSTSet() {
        root = null;
    }

    public BSTSet(int[] input) {

    }

    public boolean isIn(int v) {
        return false;
    }

    public void add(int v) {

    }

    public boolean remove(int v) {
        return false;
    }

    public BSTSet union(BSTSet s) {
        return null;
    }

    public BSTSet intersection(BSTSet s) {
        return null;
    }

    public BSTSet difference(BSTSet s) {
        return null;
    }

    public int size() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public void printBSTSet() {
        if (root == null) {
            System.out.println("The set is empty");
        } else {
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
    }

    private void printBSTSet(TNode t) {
        if (t != null) {
            printBSTSet(t.left);
            System.out.println(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }

    public void printNonRec() {

    }

    public class TNode {
        int element;
        TNode left;
        TNode right;

        TNode(int i, TNode l, TNode r) {
            element = i;
            left = l;
            right = r;
        }
    }
}
