import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Challenge_2 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        try {
            Scanner scanner= new Scanner(new File("input.txt"));
            int index = 0; // Index of the first occurrence in the start-of-packet array

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char[] lineArray = line.toCharArray();
                char[] subArray = new char[14]; // Start-of-packet array

                // Processing the first start-of-packet mark
                for (int i = 0; i < lineArray.length - 14; i++) {
                    System.arraycopy(lineArray, i, subArray, 0, subArray.length);

                    ArrayList<Character> encounteredChars = new ArrayList<>();
                    for (char c : subArray) {
                        if (encounteredChars.contains(c)) {
                            i += encounteredChars.indexOf(c);
                            break;
                        } else {
                            encounteredChars.add(c);
                        }
                    }
                    if (encounteredChars.size() == 14) {
                        index = i + 14;
                        break;
                    }
                }
            }

            scanner.close();

            System.out.println(index + " characters need to be processes before the first start-of-packet is detected");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }
}
