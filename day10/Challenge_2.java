import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Challenge_2 {
    // Screen constants
    static final int LENGTH = 40;
    static final int HEIGHT = 6;

    public static void main(String[] args) throws FileNotFoundException {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        Scanner scanner = new Scanner(new File("input.txt"));

        // Initializing the screen pixels
        String[][] screen = initializeScreen();

        // Program parameters
        int cycleCounter = 0;
        int x = 1;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");

            // Processing the given instruction
            if (line[0].equals("addx")) {
                // addx requires 2 cycles to process
                for (int i = 0; i < 2; i++) {
                    cycleCounter++;

                    //Drawing CRT
                    drawCRT(screen, x, cycleCounter);
                }

                // Execute instruction AT THE END of a cycle
                x += Integer.parseInt(line[1]);
            }
            // "noop" case
            else {
                cycleCounter++;

                //Drawing CRT
                drawCRT(screen, x, cycleCounter);

                // Execute instruction AT THE END of a cycle
                // Do nothing
            }
        }

        // Printing the screen
        printScreen(screen);

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Draws the CRT pixel
    private static void drawCRT(String[][] screen, int x, int cycleCounter) {
        int currentHeight = 0;

        // Getting the line on which the screen is
        for (int i = 1; i < cycleCounter + 1; i++) {
            if (i == 41 + 40 * currentHeight)
                currentHeight++;
        }

        // Adding a pixel if the x position "###" matches the CRT position on the screen
        int crtPosition = (cycleCounter - currentHeight * 40) - 1;
        if (x == crtPosition) {
            screen[currentHeight][crtPosition] = "#";
        } else if (x - 1 == crtPosition) {
            screen[currentHeight][crtPosition] = "#";
        } else if (x + 1 == crtPosition) {
            screen[currentHeight][crtPosition] = "#";
        }
    }


    // Initializes the screen according to its HEIGHT and LENGTH
    private static String[][] initializeScreen() {
        String[][] screen = new String[HEIGHT][LENGTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < LENGTH; j++) {
                screen[i][j] = ".";
            }
        }

        return screen;
    }

    // Prints the screen on the terminal
    private static void printScreen(String[][] screen) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < LENGTH; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
