package Lab5;

import java.util.Arrays;
import java.util.Objects;

public class MaxHeap {
    private Integer[] heap;
    private int size;
    private int capacity;

    /**
     * Create an empty heap with the given size
     * <p>
     * O(1) time and O(n) space complexity, where n = size
     *
     * @param size the size
     */
    public MaxHeap(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size cannot be less than 1");
        }

        this.heap = new Integer[size];
        this.size = 0;
        this.capacity = heap.length;
    }

    /**
     * Create a new max heap from the given array
     * <p>
     * Worst case: O(n log n) time complexity<br> Average case: O(n) time complexity<br> O(n) space complexity
     *
     * @param someArray the array
     */
    public MaxHeap(Integer[] someArray) {
        if (someArray == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        if (someArray.length == 0) {
            throw new IllegalArgumentException("array cannot be empty");
        }

        this.heap = new Integer[someArray.length];
        this.size = 0;
        this.capacity = heap.length;

        // From the input array, filter out null elements and insert the rest into the heap
        Arrays.stream(someArray).filter(Objects::nonNull).forEach(this::insert);
    }

    // O(1) time and space complexity
    private int getParentIdx(int pos) {
        return (pos - 1) / 2;
    }

    // O(1) time and space complexity
    private int getLeftIdx(int pos) {
        return 2 * pos + 1;
    }

    // O(1) time and space complexity
    private int getRightIdx(int pos) {
        return 2 * pos + 2;
    }

    // O(1) time and space complexity
    private void swap(int idx1, int idx2) {
        int temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;
    }

    /**
     * Inserts the given value into the heap in the correct spot
     * <p>
     * Worst case: O(log n) amortized time complexity<br> Average case: O(1) amortized time complexity<br> O(1) amortized space complexity
     *
     * @param n the value to insert
     */
    public void insert(int n) {
        if (size == capacity) {
            Integer[] grow = new Integer[capacity *= 2];
            System.arraycopy(heap, 0, grow, 0, heap.length);
            heap = grow;
        }

        // Insert new element
        heap[size] = n;

        // Move element to correct spot
        int currPos = size++, parentPos;
        while (heap[currPos] > heap[parentPos = getParentIdx(currPos)]) {
            swap(currPos, parentPos);
            currPos = parentPos;
        }
    }

    /**
     * O(log n) time and O(1) space complexity
     *
     * @return the largest value in the heap
     */
    private int deleteMax() {
        int ret = heap[0];
        heap[0] = heap[--size];

        // Move root back down
        int pos = 0;
        while (pos < size / 2) {
            int left = getLeftIdx(pos), right = getRightIdx(pos);

            // Compare left and right child elements and swap accordingly
            if (heap[pos] < heap[left] || heap[pos] < heap[right]) {
                if (heap[left] > heap[right]) {
                    swap(pos, left);
                    pos = left;
                } else {
                    swap(pos, right);
                    pos = right;
                }
            } else {
                break;
            }
        }

        return ret;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < size; i++) {
            s.append(heap[i]).append(",");
        }

        return s.toString();
    }

    /**
     * Sorts the given array from largest to smallest
     * <p>
     * O(n log n) time and O(n) space complexity
     *
     * @param arrayToSort the array to sort
     */
    public static void heapsort(Integer[] arrayToSort) {
        MaxHeap m = new MaxHeap(arrayToSort);
        Arrays.setAll(arrayToSort, i -> m.deleteMax());
    }

    public Integer[] getHeap() {
        return heap;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}
