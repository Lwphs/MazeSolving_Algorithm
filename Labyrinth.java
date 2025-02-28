public class Labyrinth {

    static final int INITIAL_X = 0;
    static final int INITIAL_Y = 0;

    static int x = INITIAL_X;
    static int y = INITIAL_Y;

    static int rows = 25;
    static int columns = 25;

    static char[][] labyrinth;


    public static void main(String[] args) {
        labyrinth = mazeGenerator(rows, columns);

        if (algorithm(x, y))
            System.out.println("Made it through!");
        else
            System.out.println("No solution");
    }

    private static void imprimirLaberint() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (char[] fila : labyrinth) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void wait(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static boolean isValid(int x, int y) {
        return (x >= INITIAL_X && y >= INITIAL_Y && x <= rows - 1 && y <= columns - 1
                && labyrinth[x][y] != '#' && labyrinth[x][y] != '·');
    }

    private static boolean algorithm(int x, int y) {
        if (!isValid(x, y)) return false;
        if (labyrinth[x][y] == 'X') return true;

        labyrinth[x][y] = '·';
        imprimirLaberint();
        wait(50);

        if (algorithm(x + 1, y))
            return true;
        if (algorithm(x, y + 1))
            return true;
        if (algorithm(x - 1, y))
            return true;
        if (algorithm(x, y - 1))
            return true;

        labyrinth[x][y] = ' ';
        imprimirLaberint();
        wait(50);

        return false;
    }

    private static char[][] mazeGenerator(int rows, int columns) {
        labyrinth = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (Math.random() < 0.25)
                    labyrinth[i][j] = '#';
                else
                    labyrinth[i][j] = ' ';
            }
            System.out.println();

        }


        labyrinth[rows - 1][columns - 1] = 'X';
        labyrinth[0][0] = ' ';

        return labyrinth;
    }
}
