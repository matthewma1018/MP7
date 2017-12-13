import java.util.Scanner;

/**
 * A class that performs simple calculations including addition, subtraction, 
 * multiplication, division, mod and power.
 */
public class Calculator {
	
	public static void main(String[] args) {
		
		boolean valid = false;
		
		/*
         * Perform the calculation only if the input is valid for calculation.
         */
		while (!valid) {
			valid = true;
			
			/*
			 * Read the input from keyboard.
			 */
			System.out.println("Please enter a valid equation:");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			
			/*
	         * Check if the input string contains invalid characters.
	         */
			for (int i = 0; i < str.length(); i++) {
				if (!(str.charAt(i) >= 46 && str.charAt(i) <= 57) && str.charAt(i) != 94 
						&& !(str.charAt(i)<= 43 && str.charAt(i)>= 40) && str.charAt(i) != 45 
						&& str.charAt(i) != 37) {
					valid = false;
				}
			}
			
			/*
	         * Perform the calculation.
	         */
			if (valid) {
				System.out.print(addAndSubtract(str));
			}
		}
	}
	
	/**
     * @param input the equation to be operated
     * @return the result after operations addition and subtraction
     */
	public static double addAndSubtract(String input) {
		
		for (int i = input.length() - 1; i >= 0; i--) {
			if (input.charAt(i) == '+') {
				return addAndSubtract(input.substring(0, i)) + addAndSubtract(input.substring(i + 1));
			}
			if (input.charAt(i) == '-') {
				return addAndSubtract(input.substring(0, i)) - addAndSubtract(input.substring(i + 1));
			}
		}
		return multiplyAndDivideAndMod(input);
	}
	
	/**
     * @param input the equation to be operated
     * @return the result after operations multiplication, division and mod
     */
	public static double multiplyAndDivideAndMod(String input) {
		
		for (int i = input.length() - 1; i >= 0; i--) {
			if (input.charAt(i) == '*') {
				return multiplyAndDivideAndMod(input.substring(0, i)) 
						* multiplyAndDivideAndMod(input.substring(i + 1));
			}
			if (input.charAt(i) == '/') {
				return multiplyAndDivideAndMod(input.substring(0, i)) 
						/ multiplyAndDivideAndMod(input.substring(i + 1));
			}
			if (input.charAt(i) == '%') {
				return multiplyAndDivideAndMod(input.substring(0, i)) 
						% multiplyAndDivideAndMod(input.substring(i + 1));
			}
		}
		return power(input);
	}
	
	/**
     * @param input the equation to be operated
     * @return the result after operations power
     */
	public static double power(String input) {
		
		for (int i = input.length() - 1; i >= 0; i--) {
			if (input.charAt(i) == '^') {
				return Math.pow(power(input.substring(0, i)), power(input.substring(i + 1)));
			}
		}
		return dot(input);
	}

	/**
     * @param input the number to be operated
     * @return the double value of input string
     */
	public static double dot(String input) {
		
		/*
         * If the input string has a dot, the input is a decimal. 
         * The integral part of the decimal is on the left of the dot, 
         * and the decimal part is on the right.
         */
		double integralPart = 0, decimalPart = 0;
		for (int i = input.length() - 1; i >= 0; i--) {
			if (input.charAt(i) == '.') {
				if (!input.substring(0, i).equals("")) {
					integralPart = Integer.parseInt(input.substring(0, i));
				}
				decimalPart = Integer.parseInt(input.substring(i + 1)) 
						/ Math.pow(10.0, input.substring(i + 1).length());
				return integralPart + decimalPart;
			}
		}
		
		/*
		 * If there is nothing on either side of the dot, 
		 * assign the value of that part to 0.0.
		 */
		if (input.equals("")) {
			return 0.0;
		}
		
		/*
		 * If the input string has no dot, the input is a integer.
		 */
		return Integer.parseInt(input);
	}
}
