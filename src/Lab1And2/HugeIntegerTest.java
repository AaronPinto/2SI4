package Lab1And2;

public class HugeIntegerTest {
    public static void main(String[] args) {
        HugeInteger x1, x2, x3;

        // String Constructor test cases
        try {
            x1 = new HugeInteger(null);
        } catch (Exception e) {
            System.out.println("String constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger("");
        } catch (Exception e) {
            System.out.println("String constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger("--");
        } catch (Exception e) {
            System.out.println("String constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger("-");
        } catch (Exception e) {
            System.out.println("String constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger("-0");
        } catch (Exception e) {
            System.out.println("String constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger("0");
        } catch (Exception e) {
            System.out.println("String constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger("3523534-34568374");
        } catch (Exception e) {
            System.out.println("String constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger("23453");
        } catch (Exception e) {
            System.out.println("String constructor throws exception for valid input\n");
        }

        // Random Constructor Test cases
        try {
            x1 = new HugeInteger(0);
        } catch (Exception e) {
            System.out.println("Random constructor throws exception for invalid input\n");
        }

        try {
            x1 = new HugeInteger(10);
        } catch (Exception e) {
            System.out.println("Random constructor throws exception for valid input");
        }

        // Addition Test Cases
        System.out.println("Addition");
        try {
            x1 = new HugeInteger("1");
            x2 = new HugeInteger("0");
            x3 = x1.add(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("0");
            x2 = new HugeInteger("1");
            x3 = x1.add(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("688");
            x2 = new HugeInteger("412");
            x3 = x1.add(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("100");
            x2 = new HugeInteger("-100");
            x3 = x1.add(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("1000");
            x2 = new HugeInteger("-100");
            x3 = x1.add(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Exception generated by the class for valid test cases");
            System.out.println("Invalid input for HugeInteger");
            System.out.println("Required: String equivalent of integer");
        }

        // Subtraction test cases
        System.out.println("Subtraction");
        try {
            x1 = new HugeInteger("1");
            x2 = new HugeInteger("0");
            x3 = x1.subtract(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("0");
            x2 = new HugeInteger("1");
            x3 = x1.subtract(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("100");
            x2 = new HugeInteger("-100");
            x3 = x1.subtract(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("100");
            x2 = new HugeInteger("100");
            x3 = x1.subtract(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("100");
            x2 = new HugeInteger("1000");
            x3 = x1.subtract(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error during subtraction");
        }

        // Multiplication test cases
        System.out.println("Multiplication");
        try {
            x1 = new HugeInteger("1");
            x2 = new HugeInteger("0");
            x3 = x1.multiply(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("10");
            x2 = new HugeInteger("10");
            x3 = x1.multiply(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("10");
            x2 = new HugeInteger("-10");
            x3 = x1.multiply(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("1");
            x2 = new HugeInteger("10");
            x3 = x1.multiply(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
            x1 = new HugeInteger("10");
            x2 = new HugeInteger("1");
            x3 = x1.multiply(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x3.toString());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error during multiplication");
        }

        // Comparison test cases
        System.out.println("Comparison");
        try {
            x1 = new HugeInteger("10");
            x2 = new HugeInteger("11");
            int x = x1.compareTo(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x);
            System.out.println();
            x1 = new HugeInteger("-10");
            x2 = new HugeInteger("-11");
            x = x1.compareTo(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x);
            System.out.println();
            x1 = new HugeInteger("0");
            x2 = new HugeInteger("0");
            x = x1.compareTo(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x);
            System.out.println();
            x1 = new HugeInteger("100");
            x2 = new HugeInteger("1");
            x = x1.compareTo(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x);
            System.out.println();
            x1 = new HugeInteger("1");
            x2 = new HugeInteger("100");
            x = x1.compareTo(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x);
            System.out.println();
            x1 = new HugeInteger("10");
            x2 = new HugeInteger("20");
            x = x1.compareTo(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x);
            System.out.println();
            x1 = new HugeInteger("10");
            x2 = new HugeInteger("10");
            x = x1.compareTo(x2);
            System.out.println(x1.toString());
            System.out.println(x2.toString());
            System.out.println(x);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error during comparison");
        }
    }
}
