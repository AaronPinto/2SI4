package Lab3;

public class TestBSTSet {
    public static void main(String[] args) {
        int[] noRepsSorted = new int[]{0, 1, 2, 3, 4, 5};
        int[] repsUnsorted = new int[]{6, 4, 6, 7, 8, 6, 5, 3, 2, 4, 6, 5, 8, 8, 9, 3, 2, 1, 2, 4, 4, 7, 7, 8, 8, 6, 4, 3, 0};
        int[] noCommon = new int[]{234, 46, 23, 237, 656};

        System.out.println("Test constructor 1");
        BSTSet a = new BSTSet();
        a.printBSTSet();
        System.out.println("\n");

        System.out.println("Test constructor 2");
        BSTSet b = new BSTSet(null); // null
        b.printBSTSet();

        b = new BSTSet(new int[0]); // empty array
        b.printBSTSet();

        b = new BSTSet(noRepsSorted); // normal test
        b.printBSTSet();

        b = new BSTSet(repsUnsorted); // sort and deduplicate test
        b.printBSTSet();
        System.out.println("\n");

        System.out.println("Test isIn");
        System.out.println("Expected value: false, Actual value: " + new BSTSet().isIn(20)); // root is null
        System.out.println("Expected value: false, Actual value: " + b.isIn(20)); // not in the BST
        System.out.println("Expected value: true, Actual value: " + b.isIn(0)); // in the BST
        System.out.println("\n");

        System.out.println("Test add");
        b = new BSTSet();
        b.add(0); // assign to root
        b.printBSTSet();

        b = new BSTSet(noRepsSorted);
        b.add(0); // in the list already
        b.printBSTSet();

        b = new BSTSet(noRepsSorted);
        b.add(20); // not in the list already
        b.printBSTSet();
        System.out.println("\n");

        System.out.println("Test remove");
        b = new BSTSet();
        System.out.println("Expected value: false, Actual value: " + b.remove(0)); // empty BST
        b.printBSTSet();

        b = new BSTSet(noRepsSorted);
        System.out.println("Expected value: true, Actual value: " + b.remove(3)); // root node
        b.printBSTSet();

        b = new BSTSet(noRepsSorted);
        System.out.println("Expected value: true, Actual value: " + b.remove(0)); // is in the list
        b.printBSTSet();

        b = new BSTSet(noRepsSorted);
        System.out.println("Expected value: false, Actual value: " + b.remove(20)); // is not in the list
        b.printBSTSet();
        System.out.println("\n");

        System.out.println("Test union");
        a = new BSTSet(); // null set union with non-null
        b = new BSTSet(noCommon);
        a.union(b).printBSTSet();

        a = new BSTSet(noRepsSorted); // non-null union with null
        b = new BSTSet();
        a.union(b).printBSTSet();

        a = new BSTSet(noRepsSorted); // common elements
        b = new BSTSet(noRepsSorted);
        a.union(b).printBSTSet();

        a = new BSTSet(noRepsSorted); // no common elements
        b = new BSTSet(noCommon);
        a.union(b).printBSTSet();
        System.out.println("\n");

        System.out.println("Test intersection");
        a = new BSTSet(); // null set intersection with non-null
        b = new BSTSet(noCommon);
        a.intersection(b).printBSTSet();

        a = new BSTSet(noRepsSorted); // non-null intersection with null
        b = new BSTSet();
        a.intersection(b).printBSTSet();

        a = new BSTSet(noRepsSorted); // common elements
        b = new BSTSet(noRepsSorted);
        a.intersection(b).printBSTSet();

        a = new BSTSet(noRepsSorted); // no common elements
        b = new BSTSet(noCommon);
        a.intersection(b).printBSTSet();
        System.out.println("\n");

        System.out.println("Test difference");
        a = new BSTSet(); // empty set
        b = new BSTSet(noRepsSorted);
        a.difference(b).printBSTSet(); // difference of empty set and non-empty set

        a = new BSTSet(); // empty set
        b = new BSTSet(noRepsSorted);
        b.difference(a).printBSTSet(); // difference of non-empty set and empty set

        a = new BSTSet(noRepsSorted);
        b = new BSTSet(repsUnsorted);
        a.difference(b).printBSTSet(); // common elements

        a = new BSTSet(noRepsSorted);
        b = new BSTSet(repsUnsorted);
        b.difference(a).printBSTSet(); // common elements, other way

        a = new BSTSet(noRepsSorted);
        b = new BSTSet(noCommon);
        a.difference(b).printBSTSet(); // no common elements

        a = new BSTSet(noRepsSorted);
        b = new BSTSet(noCommon);
        b.difference(a).printBSTSet(); // no common elements, other way
        System.out.println("\n");

        System.out.println("Test size and height");
        b = new BSTSet();
        System.out.println("Expected size value: 0, Actual value: " + b.size());
        System.out.println("Expected height value: -1, Actual value: " + b.height());

        b = new BSTSet(noRepsSorted);
        System.out.println("Expected size value: 6, Actual value: " + b.size());
        System.out.println("Expected height value: 2, Actual value: " + b.height());
        System.out.println("\n");

        System.out.println("Test printNonRec");
        b = new BSTSet(); // empty set
        b.printNonRec();

        b = new BSTSet(noRepsSorted);
        b.printNonRec(); // print in increasing order
        System.out.println("\n");
    }
}
