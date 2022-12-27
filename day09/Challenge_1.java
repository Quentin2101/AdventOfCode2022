import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Challenge_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        // Getting the game matrix sizes and initial position from input.txt as [rowSize, columnSize, initialRowPosition, initialColumnPosition]
        File file = new File("input.txt");
        int[] initializeGame = getGameParameters(file);

        // Building the game matrix
        int[][] matrix = new int[initializeGame[1]][initializeGame[0]];

        // Position of the Head and the Tail, initialized with the initial game positions, format: [column, row]
        int[] headPosition = {initializeGame[3], initializeGame[2]};
        int[] tailPosition = {initializeGame[3], initializeGame[2]};

        // Play the game
        playTheGame(headPosition, tailPosition, matrix, file);

        // Counting the number of 1 int the game matrix (Corresponding to the number of positions the tail went to)
        int positionCounter = countPositionInMatrix(matrix);

        System.out.println("The tail visited at least once " + positionCounter + " positions");

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Returns the number of 1 in the matrix
    private static int countPositionInMatrix(int[][] matrix) {
        int counter = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private static void playTheGame(int[] headPosition, int[] tailPosition, int[][] matrix, File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            // Tail position is marked as 1
            matrix[tailPosition[0]][tailPosition[1]] = 1;

            String[] line = scanner.nextLine().split(" ");
            int moveCounter = Integer.parseInt(line[1]);

            // Moving the head and the tail in the matrix according to the inputs
           while(moveCounter > 0) {
               moveHead(headPosition, line[0]);
               moveTail(tailPosition, headPosition);

               // Tail position is marked as 1
               matrix[tailPosition[0]][tailPosition[1]] = 1;

               moveCounter--;
           }
        }
    }

    // Moves the tail according to the head direction
    private static void moveTail(int[] tailPosition, int[] headPosition) {
        int diff_row = headPosition[1] - tailPosition[1];
        int diff_column = headPosition[0] - tailPosition[0];

        if (Math.abs(diff_column) > 1 || Math.abs(diff_row) > 1) {
            tailPosition[0] += Integer.signum(diff_column);
            tailPosition[1] += Integer.signum(diff_row);
        }
    }

    // Moves the head point according to the direction given
    private static void moveHead(int[] headPosition, String direction) {
        switch (direction) {
            case "D" -> headPosition[0]++;
            case "U" -> headPosition[0]--;
            case "R" -> headPosition[1]++;
            case "L" -> headPosition[1]--;
        }
    }

    // Returns the game initial parameters as [rowSize, columnSize, initialRowPosition, initialColumnPosition]
    private static int[] getGameParameters(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        // Tracks the current row/column value
        int rowTracker = 0;
        int columnTracker = 0;

        // Getting the row and column ranges
        int[] rowRange = new int[2];
        int[] columnRange = new int[2];


        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");

            // Switch case on the Head direction
            switch (line[0]) {
                case "D" -> columnTracker += Integer.parseInt(line[1]);
                case "U" -> columnTracker -= Integer.parseInt(line[1]);
                case "L" -> rowTracker -= Integer.parseInt(line[1]);
                case "R" -> rowTracker += Integer.parseInt(line[1]);
            }
            if (rowTracker < rowRange[0]) {
                rowRange[0] = rowTracker;
            } else if (rowTracker > rowRange[1]) {
                rowRange[1] = rowTracker;
            }

            if (columnTracker < columnRange[0]) {
                columnRange[0] = columnTracker;
            } else if (columnTracker > columnRange[1]) {
                columnRange[1] = columnTracker;
            }
        }

        scanner.close();

        // Getting the starting indexes
        int initialRowPosition = Math.abs(rowRange[0]);
        int initialColumnPosition = Math.abs(columnRange[0]);

        // Getting column and row sizes
        int rowSize = Math.abs(rowRange[1] - rowRange[0]) + 1;
        int columnSize = Math.abs(columnRange[1] - columnRange[0]) + 1;

        return new int[]{rowSize, columnSize, initialRowPosition, initialColumnPosition};
    }
}
