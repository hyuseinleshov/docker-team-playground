public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }
	
	public static int subtraction(int a, int b) {
		return a - b;
	}

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        // Example usage:
        System.out.println("Result of subtraction operation: " + subtraction(10, 5));
    }
}