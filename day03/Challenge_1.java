import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Challenge_1 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int score = 0;

            while (scanner.hasNextLine()) {
                char[] lineArray = scanner.nextLine().toCharArray();

                // Replacing characters by their priority
                int[] prioArray = charArrayToprioArray(lineArray);

                // Finding the shared character in a divided line
                int sharedChar = findSharedChar(prioArray);
                if (sharedChar > -1)
                    score += sharedChar;
                else
                    System.out.println("No shared character found !");
            }

            System.out.println("The sum of all common priorities is: " + score);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    //Returns the priority of the shared character in prioArray, array divided in 2 subarrays
    private static int findSharedChar(int[] prioArray) {
        int[] subarray1 = Arrays.copyOfRange(prioArray, 0, prioArray.length / 2);
        int[] subarray2 = Arrays.copyOfRange(prioArray, prioArray.length / 2, prioArray.length);

        // Checking subarrays length are the same
        assert(subarray1.length == subarray2.length);

        // Sorting subarray2
        Arrays.sort(subarray2);

        //Binary searching in sorted subarray2 each element of subarray1
        for (int i = 0; i < subarray1.length; i++) {
            int index = Arrays.binarySearch(subarray2, subarray1[i]);
            if (index >= 0) {
                return subarray2[index];
            }
        }

        // If no characters are shared, return -1
        return -1;
    }

    // Transforms an array of character to an array of priorities (int)
    public static int[] charArrayToprioArray(char[] lineArray) {
        int[] prioArray = new int[lineArray.length];

        for (int i = 0; i < lineArray.length; i++) {
            prioArray[i] = lineArray[i];
            prioArray[i] -= Character.isLowerCase(lineArray[i]) ? 96 : 38;
        }

        return prioArray;
    }
}
