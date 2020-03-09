package Lab4;

import java.util.concurrent.ThreadLocalRandom;

public class TestHashTable {
    private static final double[] loadFactors = new double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};

    public static void main(String[] args) {
        // Test functionality of hash table classes
        System.out.println("---TEST LINEAR PROBING HASHTABLE---");
        System.out.println("Test constructor");
        HashTableLin lin = new HashTableLin(1, 0.5);
        System.out.println("Expected size: 3, Actual size: " + lin.getSize());
        System.out.println("Expected number of keys: 0, Actual number of keys: " + lin.getNumKeys());
        System.out.println("Expected max load factor: 0.5, Actual max load factor: " + lin.getMaxLoadFactor());
        System.out.println();

        lin = new HashTableLin(24, 0.5);
        System.out.println("Expected size: 53, Actual size: " + lin.getSize());
        System.out.println("Expected number of keys: 0, Actual number of keys: " + lin.getNumKeys());
        System.out.println("Expected max load factor: 0.5, Actual max load factor: " + lin.getMaxLoadFactor());
        System.out.println();

        lin = new HashTableLin(11, 0.25);
        System.out.println("Expected size: 47, Actual size: " + lin.getSize());
        System.out.println("Expected number of keys: 0, Actual number of keys: " + lin.getNumKeys());
        System.out.println("Expected max load factor: 0.25, Actual max load factor: " + lin.getMaxLoadFactor());
        System.out.println();

        System.out.println("Test is in");
        lin = new HashTableLin(4, 0.25);
        lin.insert(3);
        System.out.println("Number should be found in the list, Actual value: " + lin.isIn(3));
        System.out.println("Number should not be found in the list, Actual value: " + lin.isIn(4));
        System.out.println();

        System.out.println("Test insert");
        lin = new HashTableLin(4, 0.25);
        lin.insert(3);
        lin.printKeysAndIndexes();
        System.out.println(lin.getSize());
        lin.insert(4);
        lin.printKeysAndIndexes();
        System.out.println(lin.getSize());
        System.out.println("inserting 20 should go to 3, then 4, then insert at 5");
        lin.insert(20);
        lin.printKeysAndIndexes();
        System.out.println(lin.getSize());
        System.out.println("inserting 21 should go to 4, then 5, then insert at 6");
        lin.insert(21);
        lin.printKeysAndIndexes();
        System.out.println(lin.getSize());
        System.out.println("should rehash and reinsert everything, then add 1");
        System.out.println(lin.getLoadFactor());
        lin.insert(1);
        lin.printKeysAndIndexes();
        System.out.println(lin.getLoadFactor());
        System.out.println(lin.getSize());
        System.out.println();

        System.out.println("Test linear probing averages");
        linProbeAvg();
        System.out.println();

        System.out.println("---TEST QUADRATIC PROBING HASHTABLE---");
        System.out.println("Test constructor");
        HashTableQuad quad = new HashTableQuad(1, 0.5);
        System.out.println("Expected size: 3, Actual size: " + quad.getSize());
        System.out.println("Expected number of keys: 0, Actual number of keys: " + quad.getNumKeys());
        System.out.println("Expected max load factor: 0.5, Actual max load factor: " + quad.getMaxLoadFactor());
        System.out.println();

        quad = new HashTableQuad(24, 0.5);
        System.out.println("Expected size: 53, Actual size: " + quad.getSize());
        System.out.println("Expected number of keys: 0, Actual number of keys: " + quad.getNumKeys());
        System.out.println("Expected max load factor: 0.5, Actual max load factor: " + quad.getMaxLoadFactor());
        System.out.println();

        quad = new HashTableQuad(11, 0.25);
        System.out.println("Expected size: 47, Actual size: " + quad.getSize());
        System.out.println("Expected number of keys: 0, Actual number of keys: " + quad.getNumKeys());
        System.out.println("Expected max load factor: 0.25, Actual max load factor: " + quad.getMaxLoadFactor());
        System.out.println();

        System.out.println("Test is in");
        quad = new HashTableQuad(4, 0.25);
        quad.insert(3);
        System.out.println("Number should be found in the list, Actual value: " + quad.isIn(3));
        System.out.println("Number should not be found in the list, Actual value: " + quad.isIn(4));
        System.out.println();

        System.out.println("Test insert");
        quad = new HashTableQuad(4, 0.25);
        quad.insert(3);
        quad.printKeysAndIndexes();
        System.out.println(quad.getSize());
        quad.insert(4);
        quad.printKeysAndIndexes();
        System.out.println(quad.getSize());
        System.out.println("inserting 20 should go to 3, then 4, then insert at 8");
        quad.insert(20);
        quad.printKeysAndIndexes();
        System.out.println(quad.getSize());
        System.out.println("inserting 21 should go to 4, then insert at 5");
        quad.insert(21);
        quad.printKeysAndIndexes();
        System.out.println(quad.getSize());
        System.out.println("should rehash and reinsert everything, then add 1");
        System.out.println("Load factor before rehashing: " + quad.getLoadFactor());
        quad.insert(1);
        quad.printKeysAndIndexes();
        System.out.println("Load factor after rehashing: " + quad.getLoadFactor());
        System.out.println(quad.getSize());
        System.out.println();

        System.out.println("Test quadratic probing averages");
        quadProbeAvg();
        System.out.println();
    }

    static void linProbeAvg() {
        HashTableLin hash;
        final int numIters = 100;
        final int numKeys = 100000;

        double avgNumProbes;
        int numProbes;

        for (double loadFactor : loadFactors) {
            avgNumProbes = 0;

            for (int i = 0; i < numIters; i++) {
                hash = new HashTableLin(numKeys, loadFactor);

                for (int j = 0; j < numKeys; j++) {
                    numProbes = hash.insertCount(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE));
                    if (numProbes == 0) {
                        j--;
                    }
                    avgNumProbes += numProbes;
                }
            }

            avgNumProbes /= (numIters * numKeys);
            System.out.printf("For load factor %.1f, theoretical avg number of probes to insert: %f, measured avg number of probes to " +
                    "insert: %f\n", loadFactor, 0.5 * (1.0 + 1.0 / (1.0 - loadFactor)), avgNumProbes);
        }
    }

    static void quadProbeAvg() {
        HashTableQuad hash;
        final int numIters = 100;
        final int numKeys = 100000;

        double avgNumProbes;
        int numProbes;

        for (double loadFactor : loadFactors) {
            avgNumProbes = 0;

            for (int i = 0; i < numIters; i++) {
                hash = new HashTableQuad(numKeys, loadFactor);

                for (int j = 0; j < numKeys; j++) {
                    numProbes = hash.insertCount(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE));
                    if (numProbes == 0) {
                        j--;
                    }
                    avgNumProbes += numProbes;
                }
            }

            avgNumProbes /= (numIters * numKeys);
            System.out.printf("For load factor %.1f, theoretical avg number of probes to insert: %f, measured avg number of probes to " +
                "insert: %f\n", loadFactor, 1.0 / loadFactor * Math.log(1.0 / (1.0 - loadFactor)), avgNumProbes);
        }
    }
}
