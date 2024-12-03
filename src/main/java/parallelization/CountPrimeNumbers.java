package parallelization;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * In this exercise, you have to count the number of prime numbers in
 * an interval of integers using multiple threads. You have to
 * implement three versions of the multithreaded computation: Using a
 * Runnable, using a Callable, and using a shared variable.
 **/
public class CountPrimeNumbers {
    /**
     * Integer counter that is thread-safe thanks to the
     * "synchronized" keyword.
     **/
    public static class SharedCounter {
        private int counter = 0;

        /**
         * Set the counter to the given value.
         **/
        public synchronized void set(int value) {
            counter = value;
        }

        /**
         * Add a value to the counter.
         **/
        public synchronized void add(int value) {
            counter += value;
        }

        /**
         * Get the value of the counter.
         **/
        public synchronized int getValue() {
            return counter;
        }
    }
    

    /**
     * Check whether the given number is prime. A prime is an integer
     * that is not a product of two smaller natural numbers. By
     * convention, negative numbers, zero, and 1 are not considered as
     * prime numbers.
     *
     * @param number The number to be tested.
     * @return true if the number is prime, false otherwise.
     */
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i =2; i < Math.round(Math.sqrt(number))+1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Count the number of primes inside the given interval of integers.
     *
     * @param start Start of the interval (inclusive).
     * @param end End of the interval (exclusive).
     * @return The number of integers "x" such that x is prime, "x >=
     * start", and "x < end".
     */
    public static int countPrimesInInterval(int start,
                                            int end) {
        int count = 0;
        for (int i = start; i<end; i++) {
            if (isPrime(i)) {
                count += 1;
            }
        }
        return count;
    }


    /**
     * Callable that counts the number of primes in an interval of integers.
     **/
    public static class CountPrimesCallable implements Callable<Integer> {
        private int start;
        private int end;

        /**
         * Constructor of the callable.
         * @param start Start of the interval (inclusive).
         * @param end End of the interval (exclusive).
         **/
        CountPrimesCallable(int start,
                            int end) {
            this.start = start;
            this.end = end;
        }

        public Integer call() {

            return countPrimesInInterval(start, end);
        }
    }


    /**
     * Runnable that counts the number of primes in an interval of integers.
     **/
    public static class CountPrimesRunnable implements Runnable {

        /**
         * Constructor of the runnable.
         * @param start Start of the interval (inclusive).
         * @param end End of the interval (exclusive).
         **/
        private final int start;
        private final int end;
        private int value;
        CountPrimesRunnable(int start,
                            int end) {
            this.start = start;
            this.end = end;

        }

        public void run() {
            value = countPrimesInInterval(start, end);
        }

        /**
         * Return the number of primes that were found in the interval
         * by the call to "run()".
         **/
        public int getResult() {

            return value;
        }
    }


    /**
     * Runnable that counts the number of primes in an interval of integers,
     * and that add this number to a shared counter.
     **/
    public static class CountPrimesSharedCounter implements Runnable {

        /**
         * Constructor of the runnable.
         * @param target Shared counter to be incremented by the number
         * of primes in the interval.
         * @param start Start of the interval (inclusive).
         * @param end End of the interval (exclusive).
         **/
        private final SharedCounter target;
        private final int start;
        private final int end;
        CountPrimesSharedCounter(SharedCounter target,
                                 int start,
                                 int end) {
            this.start = start;
            this.end = end;
            this.target = target;
        }

        public void run() {
            target.add(countPrimesInInterval(start, end));
        }
    }


    /**
     * Count the number of primes up to a given number "end" by using
     * a thread pool and the CountPrimesCallable class defined above.
     *
     * You must divide the range between "0" and "end" into
     * "countIntervals" intervals of roughly equal length (note that a
     * more realistic implementation could consider a sequence of
     * intervals with reducing length, to account for the linear
     * complexity of "countPrimesInInterval()").
     *
     * @param threadPool The thread pool to be used.
     * @param end Largest number to be considered (exclusive, i.e.
     * you have to count the numbers "x" such that x is prime, and "x
     * < end").
     * @param countIntervals The number of intervals to be processed
     * by Future. 
     * @return The number of prime numbers below "end".
     *
     * NOTES:
     *   - The "threadPool" parameter corresponds to the thread pool to
     *     be used. You *don't have* to call the
     *     "Executors.newFixedThreadPool()" method by yourself to
     *     create the thread pool, nor the "executor.shutdown()"
     *     method (this is already done for you in the unit tests).
     *   - You do not have to catch any exception.
     *   - You must throw IllegalArgumentException if countIntervals <= 0.
     **/
    public static int countPrimesWithCallable(ExecutorService threadPool,
                                              int end,
                                              int countIntervals) throws InterruptedException, ExecutionException {
        if (countIntervals <= 0) {
            throw new IllegalArgumentException();
        }
        int prec = 1;
        Future[] list = new Future[countIntervals];
        for (int i= 1; i<countIntervals+1; i++) {
            Future<Integer> future = threadPool.submit(new CountPrimesCallable(prec, i*end/countIntervals));
            list[i-1] = future;
            prec = i*end/countIntervals;
        }
        Integer sum = 0;
        for (Future<Integer> i: list) {
            sum += i.get();
        }
        return sum;
    }


    /**
     * Method with the same specification as
     * "countPrimesWithCallable()", but using CountPrimesRunnable.
     **/
    public static int countPrimesWithRunnable(ExecutorService threadPool,
                                              int end,
                                              int countIntervals) throws InterruptedException, ExecutionException {

        if (countIntervals <= 0) {
            throw new IllegalArgumentException();
        }
        int prec = 1;
        CountPrimesRunnable[] runnables = new CountPrimesRunnable[countIntervals];
        Future[] futures = new Future[countIntervals];

        SharedCounter target = new SharedCounter();
        for (int i= 1; i<countIntervals+1; i++) {
            runnables[i-1] = new CountPrimesRunnable(prec, i*end/countIntervals);
            futures[i-1] = threadPool.submit(runnables[i-1]);
            prec = i*end/countIntervals;
        }
        for (Future i : futures) {
            i.get();
        }
        int sum = 0;
        for (CountPrimesRunnable i : runnables) {
            sum += i.getResult();
        }
        return sum;
    }


    /**
     * Method with the same specification as
     * "countPrimesWithCallable()", but using CountPrimesSharedCounter.
     * Also, the result must be stored inside the "target" shared
     * variable, instead of being returned by the method.
     **/
    public static void countPrimesWithSharedCounter(SharedCounter target,
                                                    ExecutorService threadPool,
                                                    int end,
                                                    int countIntervals) throws InterruptedException, ExecutionException {
        if (countIntervals <= 0) {
            throw new IllegalArgumentException();
        }
        target.set(0);
        int prec = 1;
        Future[] futures = new Future[countIntervals];
        for (int i= 1; i<countIntervals+1; i++) {
            Future future = threadPool.submit(new CountPrimesSharedCounter(target, prec, i*end/countIntervals));
            futures[i-1] = future;
            prec = i*end/countIntervals;
        }

        for (Future<Integer> i: futures) {
            i.get();
        }
    }
}
