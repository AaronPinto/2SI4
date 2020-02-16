package Lab1And2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HugeIntTiming {
    public static void main(String[] args) throws InterruptedException {
        final ArrayList<String> addResult = new ArrayList<>(), subResult = new ArrayList<>(), compResult = new ArrayList<>(), multResult
            = new ArrayList<>();

        Thread add = new Thread(() -> {
            HugeInteger huge1, huge2, huge3;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 50000;
            int[] nVals = new int[]{10, 100, 250, 500, 1000, 5000, 10000};

            addResult.add("Addition runtime");
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new HugeInteger(n);
                    huge2 = new HugeInteger(n);
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        huge3 = huge1.add(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                addResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        add.start();

        Thread sub = new Thread(() -> {
            HugeInteger huge1, huge2, huge3;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 50000;
            int[] nVals = new int[]{10, 100, 250, 500, 1000, 5000, 10000};

            subResult.add("Subtraction runtime");
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new HugeInteger(n);
                    huge2 = new HugeInteger(n);
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        huge3 = huge1.subtract(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                subResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        sub.start();

        Thread comp = new Thread(() -> {
            HugeInteger huge1, huge2;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 50000;
            int[] nVals = new int[]{10, 100, 250, 500, 1000, 5000, 10000};

            compResult.add("Comparison runtime");
            int res = 0;
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new HugeInteger(n);
                    huge2 = new HugeInteger(n);
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        res = huge1.compareTo(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                compResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        comp.start();

        Thread mult = new Thread(() -> {
            HugeInteger huge1, huge2, huge3;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 10000;
            int[] nVals = new int[]{10, 50, 100};

            multResult.add("Multiplication runtime");
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new HugeInteger(n);
                    huge2 = new HugeInteger(n);
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        huge3 = huge1.multiply(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                multResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        mult.start();

        add.join();
        sub.join();
        comp.join();
        mult.join();

        addResult.forEach(System.out::println);
        System.out.println();
        subResult.forEach(System.out::println);
        System.out.println();
        compResult.forEach(System.out::println);
        System.out.println();
        multResult.forEach(System.out::println);

        addResult.clear();
        subResult.clear();
        compResult.clear();
        multResult.clear();

        System.out.println("\nBigInteger");

        add = new Thread(() -> {
            BigInteger huge1, huge2, huge3;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 50000;
            int[] nVals = new int[]{10, 100, 250, 500, 1000, 5000, 10000};

            addResult.add("Addition runtime");
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new BigInteger(n, ThreadLocalRandom.current());
                    huge2 = new BigInteger(n, ThreadLocalRandom.current());
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        huge3 = huge1.add(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                addResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        add.start();

        sub = new Thread(() -> {
            BigInteger huge1, huge2, huge3;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 50000;
            int[] nVals = new int[]{10, 100, 250, 500, 1000, 5000, 10000};

            subResult.add("Subtraction runtime");
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new BigInteger(n, ThreadLocalRandom.current());
                    huge2 = new BigInteger(n, ThreadLocalRandom.current());
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        huge3 = huge1.subtract(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                subResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        sub.start();

        comp = new Thread(() -> {
            BigInteger huge1, huge2;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 50000;
            int[] nVals = new int[]{10, 100, 250, 500, 1000, 5000, 10000};

            compResult.add("Comparison runtime");
            int res = 0;
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new BigInteger(n, ThreadLocalRandom.current());
                    huge2 = new BigInteger(n, ThreadLocalRandom.current());
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        res = huge1.compareTo(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                compResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        comp.start();

        mult = new Thread(() -> {
            BigInteger huge1, huge2, huge3;
            double startTime, endTime, runTime = 0.0;
            int MAXNUMINTS = 110, MAXRUN = 50000;
            int[] nVals = new int[]{10, 100, 250, 500, 1000, 5000, 10000};

            multResult.add("Multiplication runtime");
            for (int n : nVals) {
                for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                    huge1 = new BigInteger(n, ThreadLocalRandom.current());
                    huge2 = new BigInteger(n, ThreadLocalRandom.current());
                    startTime = System.nanoTime();

                    for (int numRun = 0; numRun < MAXRUN; numRun++) {
                        huge3 = huge1.multiply(huge2);
                    }

                    endTime = System.nanoTime();
                    runTime += (endTime - startTime) / MAXRUN / 1e9; // Average of each run in seconds
                }

                runTime /= MAXNUMINTS; // Average of the average of each run in seconds
                multResult.add("runtime at n=" + n + ": " + runTime);
            }
        });
        mult.start();

        add.join();
        sub.join();
        comp.join();
        mult.join();

        addResult.forEach(System.out::println);
        System.out.println();
        subResult.forEach(System.out::println);
        System.out.println();
        compResult.forEach(System.out::println);
        System.out.println();
        multResult.forEach(System.out::println);
    }
}
