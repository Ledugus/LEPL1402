package algorithms;

public class MaximumSumSubarray {

    /**
     * Class representing a sub-array in an array. It is defined by the start
     * and end position (both inclusive) of the sub-array in the array
     */
    public static class ArrayIndex {
        int start;
        int end;

        public ArrayIndex(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof ArrayIndex) {
                ArrayIndex o = (ArrayIndex) other;
                return o.start == this.start && o.end == this.end;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", this.start, this.end);
        }
    }

    /**
     * Finds the contiguous non-empty sub-array for which the sum of its elements is maximal.
     * If multiple sub-arrays have the same maximal sum, returns the one that starts at
     * the lowest index.
     *
     * For example, in the sub-array [1 ,1 , 3, -10, 3, 4, -5, -3, 2, 1], the methods returns
     * (4, 5).
     *
     * @param array A non-empty array filled with non-zero integers (which might be negative)
     * @return The position of the array for which the sum of its element is maximal (if there
     *          is a tie, the one that starts the earliest is returned)
     */
    public static ArrayIndex maximumSumSubarray(int [] array) {
        int bestStart = 0;
        int bestEnd = 0;
        int bestSum = Integer.MIN_VALUE;
        int currentSum = array[0];
        int currentStart = 0;
        for (int currentEnd = 1; currentEnd < array.length; currentEnd++){
            currentSum += array[currentEnd];

            if (currentSum > bestSum){
                bestSum = currentSum;
                bestStart = currentStart;
                bestEnd = currentEnd;
            }
            if (currentSum < 0) {
                currentSum = 0;
                currentStart = currentEnd + 1;
            }


        }
        return new ArrayIndex(bestStart, bestEnd);
    }
}