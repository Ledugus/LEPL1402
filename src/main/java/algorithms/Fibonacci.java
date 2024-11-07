package algorithms;

/**
 * This class provides a (recursive) method to compute Fibonacci numbers using recursion.
 *
 * Our feeling is that the time complexity of this method is not optimal.
 * 1) Do an analysis of the time complexity of this method in function of n
 * 2) Implement a more efficient method to compute Fibonacci numbers.
 *    Your method should have a time complexity of O(n) and space complexity of O(1).
 *
 * Your final method doesn't need to be recursive.
 */
public class Fibonacci {

    /**
     * Computes the nth Fibonacci number.
     *
     * @param n The position of the Fibonacci number to compute. It should be non-negative.
     * @return The nth Fibonacci number.
     * @throws IllegalArgumentException if n is negative.
     */
     public static long fibonacci(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be non-negative");
        }
        if (n==0) {
            return 0;
        }
        long prev1 = 1;
        long prev2 = 0;
        long curr = 1;
        for (int i = 0; i<n-1; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;

        }
        return curr;
     }

}
