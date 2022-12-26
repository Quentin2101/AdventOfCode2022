import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Challenge_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        // Building the associated matrix of input.txt
        int[][] matrix = buildMatrixFromInput(new File("input.txt"));

        // Processing the number of visible trees inside the grid
        int treesCounter = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // Building the matrix row array
                int[] columnArray = buildColumnArray(matrix, j);

                // Checking if the tree is visible from the outside of its row or its column
                if (isVisibleFromOutside(matrix[i][j], matrix[i], j) || isVisibleFromOutside(matrix[i][j], columnArray, i))
                    treesCounter++;
            }
        }


        System.out.println("The number of visible trees outside the grid is: " + treesCounter);

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Build the column array given a matrix and a row index
    private static int[] buildColumnArray(int[][] matrix, int rowIndex) {
        int[] columnArray = new int[matrix[0].length];

        for (int i = 0; i < matrix[0].length; i++) {
            columnArray[i] = matrix[i][rowIndex];
        }

        return columnArray;
    }

    // Check whether value is <= to all values in both sides of the array line, from the valueIndex
    private static boolean isVisibleFromOutside(int value, int[] line, int valueIndex) {
        // Edge case
        if (valueIndex == 0 || valueIndex == line.length - 1)
            return true;


        boolean leftSide = true;
        boolean rightSide = true;

        // Is visible from the left side
        for (int i = valueIndex - 1; i >= 0; i--) {
            if (line[i] >= value){
                leftSide = false;
                break;
            }
        }

        // Is visible from the right side
        for (int i = valueIndex + 1; i < line.length; i++) {
            if (line[i] >= value) {
                rightSide = false;
                break;
            }
        }

        return leftSide || rightSide;
    }

    // Build a matrix from an input.txt
    public static int[][] buildMatrixFromInput(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);

        // Getting number of rows and columns of the matrix
        int rows = 0;
        int columns = 0;
        while (scanner.hasNextLine()) {
            char[] line = scanner.nextLine().toCharArray();
            rows = line.length;
            columns++;
        }
        scanner.close();

        // Building the matrix
        Scanner scanner1 = new Scanner(input);
        int[][] matrix = new int[columns][rows];
        int column_counter = 0;

        while (scanner1.hasNextLine()) {
            char[] line = scanner1.nextLine().toCharArray();

            for (int i = 0; i < line.length; i++) {
                matrix[column_counter][i] = Character.getNumericValue(line[i]);
            }
            column_counter++;
        }
        scanner1.close();

        return matrix;
    }
}


