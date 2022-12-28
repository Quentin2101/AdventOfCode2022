import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Challenge_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        Scanner scanner = new Scanner(new File("input.txt"));

        // Program parameters
        int cycleCounter = 0;
        int x = 1;
        int result = 0;

        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");

            // Processing the given instruction
            if (line[0].equals("addx")) {
                // addx requires 2 cycles to process
               for (int i = 0; i < 2; i++) {
                   cycleCounter++;
                   // Signal strength is processed DURING a cycle
                   if (cycleCounter == 20 || cycleCounter == 60 || cycleCounter == 100 || cycleCounter == 140 || cycleCounter == 180 || cycleCounter == 220) {
                       result += cycleCounter * x;
                   }
               }

               // Execute instruction AT THE END of a cycle
               x += Integer.parseInt(line[1]);
            }
            // "noop" case
            else {
               cycleCounter++;

               // Signal strength is processed DURING a cycle
               if (cycleCounter == 20 || cycleCounter == 60 || cycleCounter == 100 || cycleCounter == 140 || cycleCounter == 180 || cycleCounter == 220) {
                   result += cycleCounter * x;
               }

                // Execute instruction AT THE END of a cycle
               // Do nothing
            }
        }

        System.out.println("The sum of the signal strengths is: " + result);

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }
}
