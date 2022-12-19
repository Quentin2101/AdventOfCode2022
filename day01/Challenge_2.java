import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

public class Challenge_2 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int[] max_values = { 0, 0, 0 };
            int current_value = 0;

            // Reading each line of input.txt file
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!Objects.equals(line, "")) {
                    current_value += Integer.parseInt(line);
                } else {
                    int min_array_value = minOfArray(max_values);
                    if (current_value > min_array_value) {
                        replace_array_value(max_values, min_array_value, current_value);
                    }
                    current_value = 0;
                }
            }

            // Case if the end of the file doesn't finish with a ""
            int min_array_value = minOfArray(max_values);
            if (current_value > min_array_value) {
                replace_array_value(max_values, min_array_value, current_value);
            }


            System.out.println(Arrays.toString(max_values));
            System.out.println(IntStream.of(max_values).sum());

            scanner.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Replace the first occurrence of the given value of an array to the new value
    private static void replace_array_value(int[] array, int given_value, int new_value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == given_value) {
                array[i] = new_value;
                return;
            }
        }

    }

    // Get the minimum value of an array of size 3 (Sort + binary search would be overkill)
    private static int minOfArray(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int j : array) {
            if (j < min) {
                min = j;
            }
        }

        return min;
    }
}
