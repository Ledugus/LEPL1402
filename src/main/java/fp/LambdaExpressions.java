package fp;

import javax.print.attribute.standard.MediaName;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class LambdaExpressions {
    /**
     * Return a binary operator that computes the sum of two Integer objects.
     */
    public static Object sumOfIntegers() {

        return (BinaryOperator<Integer>) (a, b) -> a + b;
    }

    /**
     * Return a predicate that tests whether a String is empty.
     */
    public static Object isEmptyString() {
        return (Predicate<String>) s -> s.isEmpty();
    }

    /**
     * Return a predicate that tests whether an Integer is an odd number.
     */
    public static Object isOddNumber() {

        return (Predicate<Integer>) n -> n % 2 == 1;
    }

    /**
     * Return a function that computes the mean of a List of Double objects.
     * If the list is empty, an IllegalArgumentException must be thrown.
     */
    public static Object computeMeanOfListOfDoubles() {

        return (Function<List<Double>, Double>) list -> {
            if (list.isEmpty()) {
                throw new IllegalArgumentException();
            }
            double sum = 0;
            for (double i : list) {
                sum += i;
            }
            return sum / list.size();
        };
    }

    /**
     * Remove the even numbers from a list of Integer objects.
     */
    public static void removeEvenNumbers(List<Integer> lst) {
        lst.removeIf(x -> x % 2 == 0);
    }

    /**
     * Return a function that computes the factorial of an Integer.
     * If the number is zero, the factorial equals 1 by convention.
     * If the number is negative, an IllegalArgumentException must be thrown.
     */
    public static Object computeFactorial() {

        return (UnaryOperator<Integer>) x -> {
            if (x < 0) {
                throw new IllegalArgumentException();
            }
            int result = 1;
            for (int i=2; i<=x; i++) {
                result *= i;
            }
            return result;
        };
    }

    /**
     * Return a function that converts a list of String objects to lower case.
     */
    public static Object listOfStringsToLowerCase() {

        return (UnaryOperator<List<String>>) lst -> {
            ArrayList<String> b = new ArrayList<>();

            for (String s : lst) {
                b.add(s.toLowerCase());
            }
            return b;
        };
    }

    /**
     * Return a function that concatenates two String objects.
     */
    public static Object concatenateStrings() {

        return (BinaryOperator<String>) (a, b) -> {
            return a + b;
        };
    }

    public static class MinMaxResult {
        private int minValue;
        private int maxValue;

        MinMaxResult(int minValue,
                     int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        int getMinValue() {
            return minValue;
        }

        int getMaxValue() {
            return maxValue;
        }
    }

    /**
     * Return a function that computes the minimum and maximum values in a list.
     * The content of the Optional must be present if and only if the list is non-empty.
     */
    public static Function<List<Integer>, Optional<MinMaxResult>> computeMinMax() {
        return lst -> {
            if (lst.isEmpty()) {
                return Optional.empty();
            } else {
                int minValue = lst.get(0);
                int maxValue = lst.get(0);
                for (Integer i : lst) {
                    minValue = Math.min(minValue, i);
                    maxValue = Math.max(maxValue, i);
                }
                return Optional.of(new MinMaxResult(minValue, maxValue));
            }
        };
    }

    /**
     * Return a function that, given a String object and a character, counts
     * the number of occurrences of the character inside the string.
     */
    public static Object countInstancesOfLetter() {

        return (BiFunction<String, Character, Integer>) (s, c) -> {
            int count = 0;
            for (int i=0; i<s.length(); i++) {
                if (c == s.charAt(i)) {
                    count++;
                }
            }
            return count;
        };
    }
}
