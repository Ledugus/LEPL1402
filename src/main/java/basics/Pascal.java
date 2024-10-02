package basics;

import java.util.Arrays;

/**
 * <p>
 * Le triangle de Pascal jusqu'à la 5ème rangée est donné ci-dessous:
 * <p>
 * row1      1
 * row2     1 1
 * row3    1 2 1
 * row4   1 3 3 1
 * row5  1 4 6 4 1
 * <p>
 * Comme vous pouvez l'observer, chaque élément de chaque
 * ligne est soit 1, soit la somme de
 * des deux éléments situés juste au-dessus.
 * Par exemple, la dernière ligne
 * du triangle est construite comme suit :
 * <p>
 * 1 (puisque le premier élément de chaque rangée n'a pas deux éléments au-dessus de lui)
 * 4 (1 + 3)
 * 6 (3 + 3)
 * 4 (3 + 1)
 * 1 (puisque le dernier élément de chaque ligne n'a pas deux éléments au-dessus de lui)
 * <p>
 * Votre tâche est de retourner un tableau qui représente
 * la nième ligne du triangle de Pascal
 * <p>
 * Vous pouvez ajouter des méthodes dans la classe.
 */
public class Pascal {

    /**
     * Computes the nth row of Pascal triangle
     *
     * @param n > 0
     * @return the nth row of Pascal triangle
     */
    
    public static int[] pascal(int n) {
        return pascal(n, new int[]{1});
    }
    // Fonction récursive remplaçable par une simple boucle
    public static int[] pascal(int n, int[] prev) {

        if (prev.length == n) return prev;
        else {
            int[] res = new int[prev.length + 1];
            res[0] = 1;
            res[prev.length] = 1;
            for (int i = 1; i < res.length - 1; i++) {
                res[i] = prev[i - 1] + prev[i];
            }
            return pascal(n, res);
        }

    }
}
