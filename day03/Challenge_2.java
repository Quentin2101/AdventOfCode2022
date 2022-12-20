import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge_2 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            String[] lineArray = new String[3];
            int counter = 0;
            int score = 0;

            while (scanner.hasNextLine()) {
                // Filling lineArray
                lineArray[counter] = scanner.nextLine();

                if (counter == 2) {
                    // Replacing characters by their priority for all 3 lines
                    int[][] prioArray = new int[3][];
                    for (int i = 0; i < lineArray.length; i++) {
                        prioArray[i] = Challenge_1.charArrayToprioArray(lineArray[i].toCharArray());
                    }

                    // Finding the shared character in the 3 lines
                    int sharedChar = findSharedChar(prioArray);
                    if (sharedChar > -1)
                        score += sharedChar;
                    else
                        System.out.println("No shared character found !");
                }

                if(counter == 2)
                    counter = 0;
                else
                    counter++;
            }

            System.out.println("The sum of all common priorities is : " + score);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    //Returns the priority of the shared character in all 3 arrays of prioArray
    private static int findSharedChar(int[][] prioArray) {
        // Creating an HashMap that stores the number of times we encounter a character in all 3 arrays
        HashMap<Integer, Integer> dict = new HashMap<>();

        // Filling the HashMap
        for (int i = 0; i < prioArray.length; i++) {
            HashSet<Integer> encounteredChar = new HashSet<>();
            for (int j = 0; j < prioArray[i].length; j++) {
                if (!encounteredChar.contains(prioArray[i][j])) {
                    if (!dict.containsKey(prioArray[i][j])) {
                        dict.put(prioArray[i][j], 1);
                    } else {
                        dict.replace(prioArray[i][j], dict.get(prioArray[i][j]) + 1);
                    }
                    encounteredChar.add(prioArray[i][j]);
                }
            }
        }

        // Getting the priority that is encountered 3 times in the HashMap
        if (dict.containsValue(3)) {
            for (HashMap.Entry<Integer, Integer> entry : dict.entrySet()) {
                if (Objects.equals(entry.getValue(), 3)) {
                   return entry.getKey();
                }
            }
        }

        return -1;
    }
}
