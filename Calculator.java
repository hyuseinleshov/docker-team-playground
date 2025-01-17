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
        System.out.println("Result of multiplication operation: " + multiply(5, 5));
    }
}