package Lab5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class TestMaxHeap {
    public static void main(String[] args) {
        // Testing constructor 1: public MaxHeap(int size) and methods: public int getSize(), public int getCapacity()
        System.out.println("TESTING CONSTRUCTOR 1");
        System.out.println("---------------------");
        MaxHeap heap;
        try {
            System.out.println("Testing invalid size");
            heap = new MaxHeap(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully threw an exception for invalid size");
        }
        System.out.println();

        System.out.println("Testing valid size");
        int capacity = ThreadLocalRandom.current().nextInt(1, 1000000);
        heap = new MaxHeap(capacity);
        System.out.println("Expected size: 0, Actual size: " + heap.getSize());
        System.out.println("Expected capacity: " + capacity + ", Actual capacity: " + heap.getCapacity());

        System.out.println();
        System.out.println();

        // Testing constructor 2: public MaxHeap(int[] someArray) and method: public String toString()
        System.out.println("TESTING CONSTRUCTOR 2 AND TOSTRING()");
        System.out.println("------------------------------------");
        try {
            System.out.println("Testing null array");
            heap = new MaxHeap(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully threw an exception for null array");
        }
        System.out.println();

        try {
            System.out.println("Testing empty array");
            heap = new MaxHeap(new Integer[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully threw an exception for empty array");
        }
        System.out.println();

        System.out.println("Testing valid array with nulls");
        heap = new MaxHeap(new Integer[]{1, 2, 3, 4, 5, null, null, 6, 7, null, 8, 9});
        System.out.println(heap.toString());
        System.out.println();

        System.out.println("Testing valid array without nulls");
        heap = new MaxHeap(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(heap.toString());

        System.out.println();
        System.out.println();

        System.out.println("TESTING INSERT");
        System.out.println("--------------");
        System.out.println("Initial heap is empty");
        capacity = ThreadLocalRandom.current().nextInt(3, 10);
        heap = new MaxHeap(capacity);
        System.out.println();

        System.out.println("Test inserting elements with a duplicate at the end");
        int value = 0;
        for (int i = 0; i < capacity - 1; i++) {
            heap.insert(value = ThreadLocalRandom.current().nextInt());
        }
        System.out.println("Expected size: " + (capacity - 1) + ", Actual size: " + heap.getSize());
        System.out.println(heap.toString());
        heap.insert(value);
        System.out.println("Expected size: " + capacity + ", Actual size: " + heap.getSize());
        System.out.println(heap.toString());
        System.out.println();

        System.out.println("Test capacity growing");
        heap.insert(ThreadLocalRandom.current().nextInt());
        System.out.println("Expected size: " + (capacity + 1) + ", Actual size: " + heap.getSize());
        System.out.println("Expected capacity: " + capacity * 2 + ", Actual capacity: " + heap.getCapacity());
        System.out.println(heap.toString());

        System.out.println();
        System.out.println();

        System.out.println("TESTING HEAPSORT");
        System.out.println("----------------");

        System.out.println("Test random arrays");
        int length = ThreadLocalRandom.current().nextInt(1, 10000);
        Integer[] temp = new Integer[length], temp2 = new Integer[length];
        for (int i = 0; i < length; i++) {
            temp[i] = temp2[i] = ThreadLocalRandom.current().nextInt();
        }

        MaxHeap.heapsort(temp2);
        Arrays.sort(temp, Collections.reverseOrder());

        System.out.println("Arrays.sort(): " + Arrays.toString(temp));
        System.out.println("MaxHeap.heapsort(): " + Arrays.toString(temp2));
        System.out.println("Arrays are equal: " + Arrays.equals(temp, temp2));
    }
}
