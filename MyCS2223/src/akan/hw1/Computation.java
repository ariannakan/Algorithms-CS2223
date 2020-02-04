package akan.hw1;

import edu.princeton.cs.algs4.*;

/**
 * Copy this class into your USERID.hw1 package 
 */
public class Computation {

	/**
	 * Return a stack of prime factors, with larger factors towards the top of the stack,
	 * and smaller factors at the bottom.
	 * 
	 * In fact, the resulting stack will be the factors of n in reverse order.
	 * 
	 * @param n    integer to be factored
	 * @return     stack of factors, where the factors appear in reverse sorted order (largest on top).
	 */
	static Stack<Long> factorize(long n) {
		Stack<Long> stack = new Stack<Long>();
		for(long i = 2; i <= n; i++){
			if(n % i == 0){
				while(n % i == 0){
					stack.push(i);
					System.out.println(i);
					n = n / i;
				}
			}
		}
		return stack;
	}
	
	/**
	 * Given a stack of numbers, representing the prime factors of a number n, return
	 * true if the number n is a perfect square.
	 * 
	 * This method may change the contents of the stack
	 * 
	 * @param factors   a Stack of factors (in reverse order) as produced by factorize.
	 * @return          True if the factors represents a number that is a square; false otherwise.
	 */
	static boolean isSquare(Stack<Long> factors) {
		int sum = 1;
		while(factors.size() != 0){
			sum *= factors.pop();
		}
		double num = Math.sqrt(sum);
		if(num - Math.floor(num) == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		// Read token. push if operator.
		StdOut.println("Enter a positive integer:");
		String s = StdIn.readString();

		try {
			long val = Long.valueOf(s);
			
			Stack<Long> factors = factorize(val);
			if (isSquare(factors)) {
				System.out.println(val + " is a perfect square.");
			} else {
				System.out.println(val + " is NOT a perfect square.");
			}
			
		} catch (Exception e) {
			System.out.println(s + " is not an integer.");
		}
	}
}
