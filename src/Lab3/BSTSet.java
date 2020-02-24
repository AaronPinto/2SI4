package Lab3;

import java.util.Arrays;

public class BSTSet {
    private TNode root;

    public BSTSet() {
        root = null;
    }

    /*
     To guarantee min height binary search tree, you have to sort the input array, remove the duplicates and then essentially do a binary
     search-like operation for inserting the nodes, where you recursively assign the middle value in each list to the parent node's left
     or right node, respectively. Time complexity is O(n log n), space complexity is O(n) both because of merge sort
    */
    public BSTSet(int[] input) {
        if (input == null || input.length == 0) {
            root = null;
            return;
        }
        input = mergeSort(input);
        // Arrays.sort(input);
        input = deduplicateSorted(input);
        // input = Arrays.stream(input).distinct().toArray();
        int mid = input.length / 2;

        root = new TNode(input[mid], null, null);
        handleLeft(root, Arrays.copyOfRange(input, 0, mid));
        handleRight(root, Arrays.copyOfRange(input, mid + 1, input.length));
    }

    // O(n) time and space complexity
    private int[] deduplicateSorted(int[] input) {
        int[] result = new int[input.length];
        int dupVal = result[0] = input[0];
        int resIdx = 1;

        for (int i = 1; i < input.length; i++) {
            if (dupVal != input[i]) {
                dupVal = result[resIdx++] = input[i];
            }
        }

        if (resIdx != input.length) {
            return Arrays.copyOfRange(result, 0, resIdx);
        }

        return result;
    }

    // Merge sort has an O(n log n) time complexity and O(n) space complexity
    private static int[] mergeSort(int[] list) {
        if (list.length <= 1) {
            return list;
        }
        int[] left, right;

        // Check if even number of elements, if so create 2 equal size arrays to store left and right hand sides respectively, else make
        // right 1 bigger than left
        if (list.length % 2 == 0) {
            left = new int[list.length / 2];
            right = new int[list.length / 2];
        } else {
            left = new int[list.length / 2];
            right = new int[list.length / 2 + 1];
        }

        // Split original array into left and right hand sides
        for (int i = 0; i < list.length; i++) {
            if (i < list.length / 2) {
                left[i] = list[i];
            } else {
                right[i - list.length / 2] = list[i];
            }
        }

        // Recursively split and then merge the arrays
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, index = 0;

        // Add elements to result in order
        while (i < left.length && j < right.length) {
            result[index++] = left[i] < right[j] ? left[i++] : right[j++];
        }

        // Add any remaining elements
        while (i < left.length) {
            result[index++] = left[i++];
        }

        while (j < right.length) {
            result[index++] = right[j++];
        }

        return result;
    }

    // handleLeft() and handleRight() have O(n) time and space complexities because they go through each node
    private void handleLeft(TNode parent, int[] left) {
        if (left.length == 0) {
            return;
        }
        int mid = left.length / 2;
        parent.left = new TNode(left[mid], null, null);
        handleLeft(parent.left, Arrays.copyOfRange(left, 0, mid));
        handleRight(parent.left, Arrays.copyOfRange(left, mid + 1, left.length));
    }

    private void handleRight(TNode parent, int[] right) {
        if (right.length == 0) {
            return;
        }
        int mid = right.length / 2;
        parent.right = new TNode(right[mid], null, null);
        handleLeft(parent.right, Arrays.copyOfRange(right, 0, mid));
        handleRight(parent.right, Arrays.copyOfRange(right, mid + 1, right.length));
    }

    // Time complexity is O(log n) avg and O(n) worst because this is a binary search tree, space complexity is O(1)
    public boolean isIn(int v) {
        if (root == null) {
            return false;
        }
        if (root.element == v) {
            return true;
        }

        TNode temp = root;

        while (temp != null) {
            if (v < temp.element) {
                temp = temp.left;
            } else if (v > temp.element) {
                temp = temp.right;
            } else {
                return true;
            }
        }

        return false;
    }

    // Time complexity is O(log n) avg and O(n) worst case, space complexity is O(1)
    public void add(int v) {
        if (root == null) {
            root = new TNode(v, null, null);
            return;
        }
        if (root.element == v) {
            return;
        }

        TNode temp = root, prev = null;

        while (temp != null) {
            if (v < temp.element) {
                prev = temp;
                temp = temp.left;
            } else if (v > temp.element) {
                prev = temp;
                temp = temp.right;
            } else {
                return;
            }
        }

        if (v < prev.element) {
            prev.left = new TNode(v, null, null);
        } else {
            prev.right = new TNode(v, null, null);
        }
    }

    // Time complexity is O(log n) avg and O(n) worst case, space complexity is O(1)
    public boolean remove(int v) {
        if (root == null) {
            return false;
        }
        if (root.element == v) {
            root = null;
            return true;
        }

        TNode temp = root, prev = root;

        while (temp != null) {
            if (v < temp.element) {
                prev = temp;
                temp = temp.left;
            } else if (v > temp.element) {
                prev = temp;
                temp = temp.right;
            } else {
                if (v < prev.element) {
                    prev.left = null;
                } else {
                    prev.right = null;
                }
                return true;
            }
        }

        return false;
    }

    // Time complexity is O(x log x) where x = n + m because of the constructor, space complexity is O(x)
    public BSTSet union(BSTSet s) {
        if (root == null) {
            return s;
        } else if (s.root == null) {
            return this;
        }

        int[] x = new int[size()], y = new int[s.size()];
        flatten(root, x, 0);
        flatten(s.root, y, 0);

        int[] merged = new int[x.length + y.length];
        System.arraycopy(x, 0, merged, 0, x.length);
        System.arraycopy(y, 0, merged, x.length, y.length);

        return new BSTSet(merged);
    }

    // Time complexity is O(n) because it goes to every node, space complexity is O(1)
    private static int flatten(TNode t, int[] array, int idx) {
        if (t == null) {
            return idx;
        }

        idx = flatten(t.left, array, idx);
        array[idx++] = t.element;
        idx = flatten(t.right, array, idx);

        return idx;
    }

    // Time complexity is O(x) where x = n * m because of the nexted for-each loops, space complexity is O(n + m)
    public BSTSet intersection(BSTSet s) {
        if (root == null || s.root == null) {
            return new BSTSet();
        }

        int[] x = new int[size()], y = new int[s.size()];
        flatten(root, x, 0);
        flatten(s.root, y, 0);

        int len = x.length, sLen = y.length, idx = 0;
        int[] temp = new int[Math.min(len, sLen)];

        for (int item : x) {
            for (int value : y) {
                if (item == value) {
                    temp[idx++] = item;
                    break;
                }
            }
        }

        if (idx != temp.length) {
            return new BSTSet(Arrays.copyOfRange(temp, 0, idx));
        }

        return new BSTSet(temp);
    }

    // Time complexity is O(x) where x = n * m because of the nexted for-each loops, space complexity is O(n + m)
    public BSTSet difference(BSTSet s) {
        if (root == null) {
            return new BSTSet();
        } else if (s.root == null) {
            return this;
        }

        int[] x = new int[size()], y = new int[s.size()];
        flatten(root, x, 0);
        flatten(s.root, y, 0);

        int idx = 0;
        int[] temp = new int[x.length];

        loop:
        for (int item : x) {
            for (int value : y) {
                if (item == value) {
                    continue loop;
                }
            }

            temp[idx++] = item;
        }

        if (idx != temp.length) {
            return new BSTSet(Arrays.copyOfRange(temp, 0, idx));
        }

        return new BSTSet(temp);
    }

    // Time complexity is O(n) because you have to visit every node, space complexity is O(height)
    public int size() {
        return size(root);
    }

    private int size(TNode t) {
        if (t == null) {
            return 0;
        }

        return size(t.left) + 1 + size(t.right);
    }

    // Time complexity is O(n) because you have to visit every node, space complexity is O(height)
    public int height() {
        if (root == null) {
            return -1;
        }

        return height(root);
    }

    private int height(TNode t) {
        if (t == null) {
            return -1; // Subtract 1 to offset adding one in the caller function
        }

        return 1 + Math.max(height(t.left), height(t.right));
    }

    // Time complexity is O(n) because you have to visit every node, space complexity is O(height)
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
            System.out.print(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }

    public void printNonRec() {
        if (root == null) {
            System.out.println("The set is empty");
            return;
        }

        MyStack<TNode> s = new MyStack<>();
        TNode temp = root;

        System.out.print("The set elements are: ");

        while (temp != null || !s.isEmpty()) {
            // Iterate through all the left nodes first, then go to the next right node
            while (temp != null) {
                s.push(temp);
                temp = temp.left;
            }

            temp = s.pop();
            System.out.print(temp.element + " ");
            temp = temp.right;
        }

        System.out.println();
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
