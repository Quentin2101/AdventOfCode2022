import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Challenge_1 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int max_value = 0;
            int current_value = 0;

            // Reading each line of input.txt file
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!Objects.equals(line, "")) {
                    current_value += Integer.parseInt(line);
                } else {
                    if (current_value > max_value) {
                        max_value = current_value;
                    }
                    current_value = 0;
                }
            }

            // Case if the end of the file doesn't finish with a ""
            if (current_value > max_value) {
                max_value = current_value;
            }


            System.out.println(max_value);

            scanner.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }
}
