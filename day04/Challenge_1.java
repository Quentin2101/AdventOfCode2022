import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Challenge_1 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int containCounter = 0;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Getting both pairs
                String[] lineParsed = line.split(",");

                // Getting x-y as an array from pairs
                int[] range1 = getRangeArray(lineParsed[0]);
                int[] range2 = getRangeArray(lineParsed[1]);

                // Checking if one pair contains the other
                if(areRangesContained(range1, range2))
                    containCounter++;
            }

            System.out.println("The number of pairs that fully contain another pair is: " + containCounter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }


    //Returns true if one range is fully contained in the other, false otherwise (Range format is [x, y])
    private static Boolean areRangesContained(int[] range1, int[] range2) {
        return (range1[0] >= range2[0] && range1[1] <= range2[1]) || (range2[0] >= range1[0] && range2[1] <= range1[1]);
    }

    // Returns the range array of ints [x, y] of the format string "x-y"
    private static int[] getRangeArray(String rangeString) {
        String[] rangeStringParsed = rangeString.split("-");

        return new int[]{Integer.parseInt(rangeStringParsed[0]), Integer.parseInt(rangeStringParsed[1])};
    }
}
