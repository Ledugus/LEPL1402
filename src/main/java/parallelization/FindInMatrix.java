package parallelization;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindInMatrix {
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

    public static class Result {
        private int row;
        private List<Integer> columns;

        Result(int row,
               List<Integer> columns) {
            this.row = row;
            this.columns = columns;
        }

        public int getRow() {
            return row;
        }

        public List<Integer> getColumns() {
            return columns;
        }
    }

    /**
     * This method finds the row that contains the most number of occurrences of a
     * certain value and returns the row index of that row and the column indexes
     * (in increasing order) where the value occurs in that row.
     * The matrix is represented by a two-dimensional array.
     *
     * Example: if the method is called with the value "3" and the following matrix
     *     (1   3  2  -4)          // <- there is a "3" in column 1
     *     (-3  4  5  -2)
     *     (3   0  3   2)          // <- there is a "3" in column 0 and column 2
     * then the result of the search is:
     *      row=2 , columns=[0,2]
     * because row 2 contains the most number of occurrences of "3" (in columns 0 and 2).
     *
     * Your solution MUST use a thread pool to accelerate the operation.
     *
     * @param matrix a rectangular matrix
     * @param value the value to find
     * @param poolSize the thread pool size
     * @return the result of the search
     *
     * You can assume that there is exactly one row in the matrix with the
     * most number of occurrences of the value.
     * Catch exceptions and ignore them.
     */



    public static Result findValue(int[][] matrix, int value, int poolSize) {

        class RowSearchCallable implements Callable<Result> {
            int[] row;
            int value;
            int rowIndex;

            RowSearchCallable(int rowIndex, int[] row, int value) {
                this.row = row;
                this.value = value;
                this.rowIndex = rowIndex;
            }
            public Result call() {
                List<Integer> columns = new ArrayList<>();
                for (int i=0; i<row.length; i++) {
                    if (row[i] == value) {
                        columns.add(i);
                    }
                }
                return new Result(rowIndex, columns);
            }

        }
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        Future<Result>[] futures = new Future[matrix.length];
        for (int i = 0; i<matrix.length; i++) {
            futures[i] = executor.submit(new RowSearchCallable(i, matrix[i], value));
        }
        Result current_max = null;
        try {
            current_max = futures[0].get();
            for (Future<Result> i : futures) {
                Result other = i.get();
                if (other.columns.size() > current_max.columns.size()) {
                    current_max = other;
                }

            }
        } catch (Exception e){}
        return current_max;
    }
}