package quizz;


// SEE THE explanations in the Videos

public class Flipper {
    // Fonctions pour de débug
    public static void printArray(int[][] a) {
        for (int i = 0; i<a.length; i++) {
            for (int j=0; j<a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void printArray(boolean[][] a) {
        for (int i = 0; i<a.length; i++) {
            for (int j=0; j<a[0].length; j++) {
                if (a[i][j]) System.out.print(a[i][j] + "  ");
                else System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    // BEGIN CODE
    public static int run(char [][] map) {
        int dir_x = 0;
        int dir_y = 1;
        int y = 0;
        int x = 0;
        int height = map.length;
        int width = map[0].length;
        boolean[][] is_visited = new boolean[height][width];
        int [][] count_visited = new int[height][width];
        int iter = 0;
        int count = 0;
        int max_iter = 2*height*width;
        while (x>=0 && y>=0 && x<height && y<width && iter < max_iter) {
            count_visited[x][y] ++;
            if (!is_visited[x][y]) count++;
            is_visited[x][y] = true;
            if (map[x][y] == '\\') {
                int temp = dir_y;
                dir_y = dir_x;
                dir_x = temp;
            }
            else if (map[x][y] == '/') {
                int temp = dir_y;
                dir_y = -dir_x;
                dir_x = -temp;
            }
            x += dir_x;
            y += dir_y;
            iter++;
        }
        printArray(count_visited);
        printArray(is_visited);
        return count;
    }
    // END CODE

    // Tests perso
    public static void main(String[] args) {
        char [][] map = {
                {'.', '.', '.', '.', '.', '.', '.', '\\', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '/', '.', '/', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '/', '.', '.', '.', '/', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '\\', '.', '.', '/', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        char [][] map2 = {
                {'.', '\\', '/', '\\', '/', '\\', '/', '\\', '/', '\\'},
                {'/', '/', '/', '/', '/', '/', '/', '/', '/', '/'},
                {'\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\'},
                {'/', '/', '/', '/', '/', '/', '/', '/', '/', '/'},
                {'\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\'},
                {'/', '/', '/', '/', '/', '/', '/', '/', '/', '/'},
                {'\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\'},
                {'/', '/', '/', '/', '/', '/', '/', '/', '/', '/'},
                {'\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\', '\\'},
                {'.', '\\', '/', '\\', '/', '\\', '/', '\\', '/', '\\'}
        };
        int answer = run(map);
        System.out.println("Answer : " + answer);
        System.out.println("Test 2");
        System.out.println("Answer 2 " + run(map2));
    }

}