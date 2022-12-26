import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Challenge_2 {
    public static void main(String[] args) throws FileNotFoundException {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        // Building the associated matrix of input.txt
        int[][] matrix = buildMatrixFromInput(new File("input.txt"));

        // Processing the number of visible trees inside the grid
        int treesVisibility = 0;

        // Not processing edge trees because their scenic score is always 0 (at least one of their viewing distance is 0)
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                // Building the matrix row array
                int[] columnArray = buildColumnArray(matrix, j);

                // Processing the scenic score (multiplying th viewing distance of each side)
                int currentVisibility = howVisibleFromOutside(matrix[i][j], matrix[i], j) * howVisibleFromOutside(matrix[i][j], columnArray, i);
                if (currentVisibility > treesVisibility)
                    treesVisibility = currentVisibility;
            }
        }


        System.out.println("The highest scenic score possible is: " + treesVisibility);

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

    // Processing the scenic score of line
    private static int howVisibleFromOutside(int value, int[] line, int valueIndex) {
        int rightVisibility = 0;
        int leftVisibility = 0;

        // How far is it visible from the left side
        for (int i = valueIndex - 1; i >= 0; i--) {
            leftVisibility++;
            if (line[i] >= value){
                break;
            }
        }

        // How far is it visible from the right side
        for (int i = valueIndex + 1; i < line.length; i++) {
            rightVisibility++;
            if (line[i] >= value) {
                break;
            }
        }

        return rightVisibility * leftVisibility;
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


