package basics;

public class CommonElements {
    /**
     *
     * @param tab1 is a non null array
     * @param tab2 is a non null array
     * @return the number of elements that are the same at the same index
     *         more exactly the size of set {i such that tab1[i] == tab2[i]}
     *         for instance count([1,3,5,5],[1,2,5,5,6]) = 3
     */
    public static int count(int [] tab1, int [] tab2) {
        int count = 0;
        int i = 0;
        while (i < tab1.length && i < tab2.length) {
            if (tab1[i] == tab2[i]) {
                count++;
            }
            i++;
        }
        return count;
    }

    /**
     *
     * @param tab1 is a non null 2D array
     * @param tab2 is a non null 2D array
     * @return the number of elements that are the same at the same index
     *         more exactly the size of set {(i,j) such that tab1[i][j] == tab2[i][j]}
     */
    public static int count(int [][] tab1, int [][] tab2) {

        int count = 0;
        int i = 0;
        // Possible aussi avec la boucle for
        // de longueur Math.min(tab1.length, tab2.length)
        while (i < tab1.length && i < tab2.length) {
            int j = 0;
            while (j < tab1[i].length && j < tab2[i].length) {
                if (tab1[i][j] == tab2[i][j]) {
                    count++;
                }
                j++;
            }
            i++;
        }
        return count;
    }
}
