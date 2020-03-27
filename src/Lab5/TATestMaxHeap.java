package Lab5;

import java.util.Objects;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;

/**
 * @author TA
 */
public class TATestMaxHeap {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        /* We assume that we have the following accessor public methods
          getSize() -- returns the number of elements in the array
          getCapacity() -- return the size of the array (maximum number of elements that we can have)
          toString() separates the numbers by a comma (e.g., 45,21,13)
		  heapsort() returns the numbers in the descending order
         */

        MaxHeap heap1;
        Random random = new Random();

        int fails = 0;
        int each_fail = 0;

        // Testing constructor 1: public MaxHeap(int size) and methods: public int getSize(), public int getSize()

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("Testing constructor 1");
        for (int power = 1; power <= 6; power++) {
            try {
                int capacity = (int) Math.pow(10, power);
                heap1 = new MaxHeap(capacity);

                if (heap1.getCapacity() != capacity) {
                    System.out
                        .println("Test failed for constructor 1: public MaxHeap(int capacity) and methods: public int getCapacity(), " +
                            "public int getNumElement()");
                    System.out.println("Expected output: capacity = " + capacity);
                    System.out.println("Current output: capacity= " + heap1.getCapacity());
                    System.out.println();
                    fails++;
                    each_fail++;
                }

                if (heap1.getSize() != 0) {
                    System.out
                        .println("Test failed for constructor 1: public MaxHeap(int size) and methods: public int getCapacity(), public " +
                            "int getNumElement()");
                    System.out.println("Expected output: num_elements = 0");
                    System.out.println("Current output: size= " + heap1.getSize());
                    System.out.println();
                    fails++;
                    each_fail++;
                }
            } catch (Exception e) {
                System.out.println("Invalid size");
                fails++;
                each_fail++;
            }
        }
        if (each_fail == 0) {
            System.out.println("PASS");
        }
        each_fail = 0;

        System.out.println();
        System.out.println();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        // Testing constructor 2: public MaxHeap(int[] someArray) and method: public String toString()

        System.out.println("Testing constructor 2 and toString()");

        int[] sizes = {1, 5, 10, 15, 20, 100, (int) 1e2, (int) 1e3, (int) 1e4, (int) 1e5, (int) 1e6};
        for (int loc = 1; loc < sizes.length - 1; loc++) {

            int size = sizes[loc];
            Integer[] someArray = new Integer[size];
            for (int j = 0; j < size; j++) {
                someArray[j] = random.nextInt((int) 1e4);
                if (random.nextInt(10) > 5) {
                    someArray[j] = -someArray[j];
                }
            }

            heap1 = new MaxHeap(someArray);

            String maxheap1 = heap1.toString();
            if (sizes[loc] <= 20) {
                System.out.println("Heap (size " + sizes[loc] + ")");
                System.out.println(maxheap1);
                System.out.println();
            }

            try {
                int[] heaparray1 = stringToArray(maxheap1);
                boolean is_max_heap = isMaxHeap(heaparray1);
                if (!is_max_heap) {
                    System.out.println("Max heap test failed: Test for size " + sizes[loc]);
                    System.out.println();
                    fails++;
                    each_fail++;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid character/ Wrong format of string for size " + sizes[loc]);
                System.out.println();
                fails++;
                each_fail++;
            }
        }
        if (each_fail == 0) {
            System.out.println("PASS");
        }
        each_fail = 0;

        System.out.println();
        System.out.println();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        // Testing public void insert() method
        System.out.println("Testing insert() method");
        for (int loc = 1; loc < sizes.length - 1; loc++) {

            int size = sizes[loc];
            try {
                heap1 = new MaxHeap(size / 2);
                for (int j = 0; j < size; j++) {
                    int k = random.nextInt((int) 1e4);

                    if (random.nextInt(10) > 5) {
                        k = -k;
                    }
                    heap1.insert(k);
                }
                String maxheap1 = heap1.toString();
                if (sizes[loc] <= 20) {
                    System.out.println("Heap (size " + sizes[loc] + ")");
                    System.out.println(maxheap1);
                    System.out.println();
                }

                try {
                    int[] heaparray1 = stringToArray(maxheap1);
                    boolean is_max_heap = isMaxHeap(heaparray1);
                    if (!is_max_heap) {
                        System.out.println("Max heap test failed: Test for size " + sizes[loc]);
                        System.out.println();
                        fails++;
                        each_fail++;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid character/ Wrong format of string for size " + sizes[loc]);
                    System.out.println();
                    fails++;
                    each_fail++;
                }

            } catch (Exception e) {
                System.out.println("Invalid size");
                each_fail++;
                fails++;
            }
        }
        if (each_fail == 0) {
            System.out.println("PASS");
        }
        each_fail = 0;

        System.out.println();
        System.out.println();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        // Testing public static void heapsort() method

        System.out.println("Testing heapsort method- ");

        for (int loc = 1; loc < sizes.length; loc++) {

            int size = sizes[loc];
            Integer[] someArray1 = new Integer[size];
            Integer[] someArray2 = new Integer[size];
            for (int j = 0; j < size; j++) {
                someArray1[j] = random.nextInt((int) 1e6);
                if (random.nextInt(10) < 5) {
                    someArray1[j] = -someArray1[j];
                }
                someArray2[j] = someArray1[j];
            }

            MaxHeap.heapsort(someArray1);
            Arrays.sort(someArray2, Collections.reverseOrder());
            if (sizes[loc] <= 20) {
                System.out.println("Sorted array (size " + sizes[loc] + ")");
                for (int j = 0; j < someArray1.length - 1; j++) {
                    System.out.print(someArray1[j] + ",");
                }
                System.out.println(someArray1[someArray1.length - 1]);
                System.out.println();
            }

            for (int j = 0; j < someArray1.length; j++) {
                if (!Objects.equals(someArray1[j], someArray2[j])) {
                    System.out.println("Heapsort method() test failed");
                    System.out.println("Error at index - " + j);
                    System.out.println("Expected - " + someArray2[j] + " Current- " + someArray1[j]);
                    each_fail++;
                    fails++;
                    break;
                }
            }
        }
        if (each_fail == 0) {
            System.out.println("PASS");
        }

        System.out.println();
        System.out.println();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        if (fails == 0) {
            System.out.println("PASS: ALL");
        } else {
            System.out.println("FAILED some test cases");
        }


    }

    public static int[] stringToArray(String maxheap) throws IllegalArgumentException {
        String[] strArray = maxheap.split(", |,");
        int[] intArray = new int[strArray.length];
        try {
            for (int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }
        } catch (Exception e) {
            System.out.println("Error in toString() method");
            throw new IllegalArgumentException();
        }
        return intArray;
    }

    public static boolean isMaxHeap(int[] heaparray) {
        boolean is_max_heap = true;
        for (int root_index = 0; root_index <= heaparray.length / 2; root_index++) {

            int left_index = 2 * root_index + 1;
            int right_index = 2 * root_index + 2;

            if (left_index < heaparray.length) {
                if (!(heaparray[root_index] >= heaparray[left_index])) {
                    is_max_heap = false;
                    System.out.println("Max Heap condition violated");
                    System.out.println("Root index - " + root_index + "\t Root value- " + heaparray[root_index]);
                    System.out.println("Left child - " + left_index + "\t Left child value- " + heaparray[left_index]);
                    break;
                }
            }

            if (right_index < heaparray.length) {
                if (!(heaparray[root_index] >= heaparray[right_index])) {
                    is_max_heap = false;
                    System.out.println("Max Heap condition violated");
                    System.out.println("Root index - " + root_index + "\t Root value- " + heaparray[root_index]);
                    System.out.println("Right child - " + right_index + "\t Right child value- " + heaparray[right_index]);
                    break;
                }
            }
        }
        return is_max_heap;
    }
}
