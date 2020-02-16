package Lab1And2;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Aaron Pinto
 */
public class HugeInteger {
    private int signum; // -1, 0 or +1
    private int[] digits; // Max value per element of 9, cuz radix (base) is 10

    public HugeInteger(String val) {
        if (val == null) {
            throw new IllegalArgumentException("input string can't be null");
        }

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
            digits = new int[0];
            return;
        }

        signum = sign;
        digits = new int[len - cursor];

        // Process/parse each digit
        for (int i = 0; (i + cursor) < len; i++) {
            String digit = val.substring(i + cursor, i + cursor + 1);
            digits[i] = Integer.parseInt(digit);
            if (digits[i] < 0) {
                throw new NumberFormatException("Illegal digit");
            }
        }
    }

    public HugeInteger(int n) {
        if (n < 1) { throw new IllegalArgumentException("n must be larger or equal to 1"); }

        // Populate digits, first one can't be 0
        signum = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
        digits = new int[n];
        digits[0] = ThreadLocalRandom.current().nextInt(1, 10);

        for (int i = 1; i < n; i++) {
            digits[i] = ThreadLocalRandom.current().nextInt(0, 10);
        }
    }

    private HugeInteger(int[] digits, int signum) {
        this.digits = digits;
        this.signum = signum;
    }

    /**
     * Adds another HugeInteger to this one and returns the result
     *
     * @param h the HugeInteger to add to this one
     *
     * @return a new HugeInteger representing the result
     */
    public HugeInteger add(HugeInteger h) {
        if (h.signum == 0) { return this; }
        if (signum == 0) { return h; }
        if (signum == h.signum) { return new HugeInteger(add(digits, h.digits), signum); }

        // Signums are opposite, so we need to figure out which one is smaller and then subtract the smaller one from the bigger one
        // Then we can create a new HugeInteger with a signum of 1 if this one was bigger, or -1 if h was bigger
        int compare = compareDigits(h);
        if (compare == 0) { return new HugeInteger(new int[0], 0); }

        int[] result = compare == 1 ? subtract(digits, h.digits) : subtract(h.digits, digits);

        return new HugeInteger(result, compare == signum ? 1 : -1);
    }

    private static int[] add(int[] digits, int[] hDigits) {
        // Make digits be the small one by swapping
        if (digits.length > hDigits.length) {
            int[] tmp = hDigits;
            hDigits = digits;
            digits = tmp;
        }

        boolean carry = false;
        int smallIndex = digits.length, bigIndex = hDigits.length;
        int[] result = new int[bigIndex];

        // Start from the end and sum each pair of elements with the optional carry, until small number is done
        while (smallIndex > 0) {
            int sum = digits[--smallIndex] + hDigits[--bigIndex] + (carry ? 1 : 0);
            if (sum > 9) { // Can only have 1 digit per element so handle if the sum > 9
                carry = true;
                sum -= 10;
            } else {
                carry = false;
            }
            result[bigIndex] = sum;
        }

        // Sum remaining big number elements with optional carry
        while (bigIndex > 0) {
            int sum = hDigits[--bigIndex] + (carry ? 1 : 0);
            if (sum > 9) {
                carry = true;
                sum -= 10;
            } else {
                carry = false;
            }
            result[bigIndex] = sum;
        }

        // Grow result depending on possible last carry
        if (carry) {
            int[] grown = new int[result.length + 1];
            System.arraycopy(result, 0, grown, 1, result.length);
            grown[0] = 1;
            return grown;
        }

        return result;
    }

    /**
     * Subtracts another HugeInteger from this one and returns the result
     *
     * @param h the HugeInteger to subtract from this one
     *
     * @return a new HugeInteger representing the result
     */
    public HugeInteger subtract(HugeInteger h) {
        if (h.signum == 0) { return this; }
        if (signum == 0) { return new HugeInteger(h.digits, -signum); }
        if (h.signum != signum) { return new HugeInteger(add(digits, h.digits), signum); }

        // Same comparison as addition, because the signums are the same
        int compare = compareDigits(h);
        if (compare == 0) { return new HugeInteger(new int[0], 0); }

        int[] result = compare == 1 ? subtract(digits, h.digits) : subtract(h.digits, digits);

        return new HugeInteger(result, compare == signum ? 1 : -1);
    }

    private static int[] subtract(int[] big, int[] small) {
        boolean borrow = false;
        int bigIndex = big.length, smallIndex = small.length;
        int[] result = new int[bigIndex];

        // Start from the end and subtract each pair of elements with the optional borrow, until small number is done
        while (smallIndex > 0) {
            int difference = big[--bigIndex] - small[--smallIndex] - (borrow ? 1 : 0);
            if (difference < 0) { // Can only have 1 digit per element so handle if the difference < 0
                borrow = true;
                difference += 10;
            } else {
                borrow = false;
            }
            result[bigIndex] = difference;
        }

        // Subtract remaining big number elements with optional borrow
        while (bigIndex > 0) {
            int difference = big[--bigIndex] - (borrow ? 1 : 0);
            if (difference < 0) {
                borrow = true;
                difference += 10;
            } else {
                borrow = false;
            }
            result[bigIndex] = difference;
        }

        return stripLeadingZeroes(result);
    }

    /**
     * Multiply this HugeInteger with another HugeInteger, with a max asymptotic time complexity of O(n^(log2(3))) or O(n^1.585) using
     * Karatsuba's method, and return a new HugeInteger with the result
     *
     * @param h the HugeInteger to multiply this with
     *
     * @return a new HugeInteger representing the result of the multiplication
     */
    public HugeInteger multiply(HugeInteger h) {
        if (signum == 0 || h.signum == 0) {
            return new HugeInteger(new int[0], 0);
        }

        int resSignum = signum == h.signum ? 1 : -1;
        int len = digits.length, hLen = h.digits.length;
        if (len == 1) {
            return multiplyByDigit(h, digits[0], resSignum);
        }
        if (hLen == 1) {
            return multiplyByDigit(this, h.digits[0], resSignum);
        }

        return karatsuba(this, h);
    }

    private HugeInteger multiplyn2(HugeInteger h, int signum) {
        int[] result = new int[h.digits.length + this.digits.length];
        int xstart = this.digits.length - 1;
        int ystart = h.digits.length - 1;
        int[] temp = h.digits, temp2 = this.digits;

        int carry;
        for (int i = xstart; i >= 0; i--) {
            carry = 0;
            for (int j = ystart, k = ystart + 1 + i; j >= 0; j--, k--) {
                int product = temp[j] * temp2[i] + result[k] + carry;
                if (product > 9) {
                    result[k] = product % 10;
                    carry = product / 10;
                } else {
                    result[k] = product;
                    carry = 0;
                }
            }
            result[i] = carry;
        }

        return new HugeInteger(stripLeadingZeroes(result), signum);
    }

    private static HugeInteger multiplyByDigit(HugeInteger x, int digit, int signum) {
        int[] result = new int[x.digits.length + 1];
        int carry = 0, res;
        int rIndex = result.length - 1;

        for (int i = x.digits.length - 1; i >= 0; i--) {
            res = x.digits[i] * digit + carry;
            if (res > 9) {
                result[rIndex--] = res % 10;
                carry = res / 10;
            } else {
                result[rIndex--] = res;
                carry = 0;
            }
        }

        // Get rid of empty spaces from array
        if (carry == 0) {
            result = Arrays.copyOfRange(result, 1, result.length);
        } else {
            result[rIndex] = carry;
        }

        return new HugeInteger(result, signum);
    }

    /**
     * Multiplies two HugeIntegers using the Karatsuba multiplication algorithm. This is a recursive divide-and-conquer algorithm which is
     * more efficient for large numbers than the typical algorithm used in multiplication. If the numbers to be multiplied have length n,
     * the typical algorithm has an asymptotic complexity of O(n^2). In contrast, the Karatsuba algorithm has complexity of O(n^(log2(3))),
     * or O(n^1.585). It achieves this increased performance by doing 3 multiplies instead of 4 when evaluating the product.
     * <p>
     * See: https://en.wikipedia.org/wiki/Karatsuba_algorithm
     */
    private HugeInteger karatsuba(HugeInteger x, HugeInteger y) {
        int xLen = x.digits.length, yLen = y.digits.length;

        int mid = (Math.max(xLen, yLen) + 1) / 2;

        // Divide both numbers into their upper and lower parts
        HugeInteger xUp = x.getUpper(mid), xLow = x.getLower(mid), yUp = y.getUpper(mid), yLow = y.getLower(mid);

        HugeInteger z0 = xLow.multiply(yLow);
        HugeInteger z1 = xUp.multiply(yUp);
        HugeInteger z2 = xLow.add(xUp).multiply(yLow.add(yUp));

        HugeInteger result = z1.shiftLeft(mid).add(z2.subtract(z1).subtract(z0)).shiftLeft(mid).add(z0);

        if (x.signum != y.signum) {
            return new HugeInteger(result.digits, -result.signum);
        } else {
            return result;
        }
    }

    /**
     * Returns a HugeInteger repersenting the number shifted left n positions. It is equivalent to this * 10^n.
     *
     * @param n the number of positions to shift
     *
     * @return the shifted HugeInteger
     */
    private HugeInteger shiftLeft(int n) {
        if (signum == 0) {
            return new HugeInteger(new int[0], 0);
        }
        if (n > 0) {
            // Copy the digits array to a bigger one, starting at 0 to emulate the shift
            int[] result = new int[digits.length + n];
            System.arraycopy(digits, 0, result, 0, digits.length);
            return new HugeInteger(result, signum);
        } else if (n == 0) {
            return this;
        } else {
            throw new IllegalArgumentException("n cannot be negative");
        }
    }

    /**
     * Returns a new HugeInteger representing n lower digits of the number.
     */
    private HugeInteger getLower(int n) {
        int length = digits.length;

        // Return the absolute value of this HugeInteger if the length is <= the number of digits in the lower half
        if (length <= n) {
            return signum >= 0 ? this : new HugeInteger(this.digits, -this.signum);
        }

        int[] lowerDigits = new int[n];
        System.arraycopy(digits, length - n, lowerDigits, 0, n);
        lowerDigits = stripLeadingZeroes(lowerDigits);
        return new HugeInteger(lowerDigits, lowerDigits.length == 0 ? 0 : 1); // Check if the lower half is 0
    }

    /**
     * Returns a new HugeInteger representing digits.length-n upper digits of the number.
     */
    private HugeInteger getUpper(int n) {
        int length = digits.length;

        // Return 0 if the length is <= the number of digits in the upper half
        if (length <= n) {
            return new HugeInteger(new int[0], 0);
        }

        int upperLength = length - n;
        int[] upperDigits = new int[upperLength];
        System.arraycopy(digits, 0, upperDigits, 0, upperLength);

        return new HugeInteger(stripLeadingZeroes(upperDigits), 1);
    }

    /**
     * Strip leading zeroes, find first non-zero element and return a copy of the result array from there to the end
     *
     * @param val the digit array to strip
     *
     * @return the stripped digit array
     */
    private static int[] stripLeadingZeroes(int[] val) {
        int nonZeroIndex;
        for (nonZeroIndex = 0; nonZeroIndex < val.length && val[nonZeroIndex] == 0; nonZeroIndex++) {}
        return nonZeroIndex != 0 ? Arrays.copyOfRange(val, nonZeroIndex, val.length) : val;
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
