package basics;

public class MagicSquare {


    /**
     * A magic square is an (n x n) matrix such that:
     *
     * - all the positive numbers 1,2, ..., n*n are present (thus each number appears exactly once)
     * - the sums of the numbers in each row, each column and both main diagonals are the same
     *
     *   For instance a 3 x 3 magic square is the following
     *
     *   2 7 6
     *   9 5 1
     *   4 3 8
     *
     *   You have to implement the method that verifies if a matrix is a valid magic square
     */

    /**
     *
     * @param array (matrix) a square matrix of size n x n
     * @return true if matrix is a n x n magic square, false otherwise
     */
    public static int sumOfArray(int[] array) {
        int sum  = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }
    public static boolean isMagicSquare(int [][] matrix) {
        int sum = sumOfArray(matrix[0]);
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] <= 0 || matrix[i][j] > n*n) {
                    return false;
                }
            }
        }

        // permutation
        boolean [] present = new boolean[n*n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int v = matrix[i][j];
                if (present[v]) {
                    return false;
                }
                present[v] = true;
            }
        }
        // check lines sum
        for (int[] ints : matrix) {
            if (sum != sumOfArray(ints)) {
                return false;
            }
        }
        // check col sums
        for (int i=0; i< n; i++) {
            int colsum = 0;
            for (int j=0; j< n; j++) {
                colsum += matrix[j][i];
            }
            if (colsum != sum) {return false;}
        }
        // check diagonals
        int diag1 = 0;
        int diag2 = 0;
        for (int i =0; i <n; i++){
            diag1 += matrix[i][i];
            diag2 += matrix[i][n-i-1];
        }
        if (diag1 != sum || diag2 != sum){
            return false;
        }
        return true;
    }
}
