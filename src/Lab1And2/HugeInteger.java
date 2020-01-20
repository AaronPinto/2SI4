package Lab1And2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Aaron Pinto
 */
public class HugeInteger {
    private final int signum; // -1, 0 or +1
    private byte[] digits; // Max value per element of 9, cuz radix (base) is 10

    public HugeInteger(String val) {
        final int len = val.length();
        int cursor = 0;

        if (len == 0) { throw new NumberFormatException("Zero length HugeInteger"); }

        // Check for at most one leading negative sign
        int sign = 1, index1 = val.lastIndexOf('-');
        if (index1 >= 0) {
            if (index1 != 0) {
                throw new NumberFormatException("Illegal digit");
            }
            sign = -1;
            cursor = 1;
        }

        if (cursor == len) { throw new NumberFormatException("Zero length HugeInteger"); }

        // Skip leading zeros and compute number of digits
        while (cursor < len && Character.digit(val.charAt(cursor), 10) == 0) {
            cursor++;
        }

        // Input is 0
        if (cursor == len) {
            signum = 0;
            digits = new byte[0];
            return;
        }

        signum = sign;
        digits = new byte[len - cursor];

        // Process/parse each digit
        for (int i = 0; (i + cursor) < len; i++) {
            String digit = val.substring(i + cursor, i + cursor + 1);
            digits[i] = Byte.parseByte(digit);
            if (digits[i] < 0) {
                throw new NumberFormatException("Illegal digit");
            }
        }
    }

    public HugeInteger(int n) {
        if (n < 1) { throw new IllegalArgumentException("n must be larger or equal to 1"); }

        // Populate digits, first one can't be 0
        signum = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
        digits = new byte[n];
        digits[0] = (byte) ThreadLocalRandom.current().nextInt(1, 10);

        for (int i = 1; i < n; i++) {
            digits[i] = (byte) ThreadLocalRandom.current().nextInt(0, 10);
        }
    }

    private HugeInteger(byte[] digits, int signum) {
        this.digits = digits;
        this.signum = signum;
    }

    /**
     * Perform element-wise addition, where the max value of any element is 9, because the radix (base) is 10
     *
     * @param h the HugeInteger to add to this one
     *
     * @return a new HugeInteger representing the result
     */
    public HugeInteger add(HugeInteger h) {
        if (h.signum == 0) { return this; }
        if (signum == 0) { return h; }

        byte[] thisTemp = digits, hTemp = h.digits;

        // Make thisTemp be the small one by swapping
        if (thisTemp.length > hTemp.length) {
            byte[] tmp = hTemp;
            hTemp = thisTemp;
            thisTemp = tmp;
        }

        boolean carry = false;
        int smallLen = thisTemp.length, bigLen = hTemp.length;
        byte[] result = new byte[bigLen];

        // Start from the end and sum each pair of elements with the optional carry, until small number is done
        while (smallLen > 0) {
            byte sum = (byte) (thisTemp[--smallLen] + hTemp[--bigLen] + (carry ? 1 : 0));
            if (sum > 9) { // Can only have 1 digit per element so handle if the sum > 9
                carry = true;
                sum -= 10;
            } else {
                carry = false;
            }
            result[bigLen] = sum;
        }

        // Sum remaining big number elements with optional carry
        while (bigLen > 0) {
            byte sum = (byte) (hTemp[--bigLen] + (carry ? 1 : 0));
            if (sum > 9) {
                carry = true;
                sum -= 10;
            } else {
                carry = false;
            }
            result[bigLen] = sum;
        }

        // Grow result depending on possible last carry
        if (carry) {
            byte[] grown = new byte[result.length + 1];
            System.arraycopy(result, 0, grown, 1, result.length);
            grown[0] = 1;
            return new HugeInteger(grown, signum);
        }

        return new HugeInteger(result, signum);
    }

    public HugeInteger subtract(HugeInteger h) {
        return null;
    }

    public HugeInteger multiply(HugeInteger h) {
        return null;
    }

    /**
     * Compares this HugeInteger with another HugeInteger
     *
     * @param h the other HugeInteger to compare
     *
     * @return -1, 0 or 1 if this HugeInteger is numerically less than, equal to, or greater than h.
     */
    public int compareTo(HugeInteger h) {
        // Compare signs first, then digits
        if (signum == h.signum) {
            switch (signum) {
                case 1:
                    return compareDigits(h);
                case -1:
                    return h.compareDigits(this);
                default:
                    return 0;
            }
        }

        return signum > h.signum ? 1 : -1;
    }

    private int compareDigits(HugeInteger h) {
        // Compare length first then each digit pair
        int len1 = digits.length, len2 = h.digits.length;

        if (len1 < len2) { return -1; }
        if (len1 > len2) { return 1; }

        // Iterate through both digits and compare them
        for (int i = 0; i < len1; i++) {
            int a = digits[i], b = h.digits[i];
            if (a != b) {
                return a < b ? -1 : 1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        if (signum == 0) { return "0"; }

        StringBuilder sb = new StringBuilder(digits.length + 1);

        if (signum == -1) { sb.append("-"); }

        for (int digit : digits) {
            sb.append(digit);
        }

        return sb.toString();
    }
}
