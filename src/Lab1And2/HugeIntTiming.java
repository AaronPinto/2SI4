package Lab1And2;

public class HugeIntTiming {
    public static void main(String[] args) {
        HugeInteger huge1, huge2, huge3;
        long startTime, endTime;
        double runTime = 0.0;
        int MAXNUMINTS = 100, MAXRUN = 1000;
        int[] nVals = new int[]{10, 100, 500, 1000, 5000, 10000};

        for (int n : nVals) {
            for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                huge1 = new HugeInteger(n);
                huge2 = new HugeInteger(n);
                startTime = System.currentTimeMillis();
                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    huge3 = huge1.add(huge2);
                }
                endTime = System.currentTimeMillis();
                runTime += (double) (endTime - startTime) / ((double) MAXRUN);
            }

            runTime /= MAXNUMINTS;
            System.out.println("runtime at n=" + n + ": " + runTime);
        }
    }
}
