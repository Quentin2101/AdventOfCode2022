import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Challenge_1 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int totalScore = 0;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Parsing line to have the opponent hand and ours
                String[] parsed_line = line.split(" ");
                String opponentHand = parsed_line[0];
                String ourHand = parsed_line[1];

                // Defining how many points our hand gives us
                switch (ourHand) {
                    case "X" -> {
                        ourHand = "A";
                        totalScore += 1;
                    }
                    case "Y" -> {
                        ourHand = "B";
                        totalScore += 2;
                    }
                    case "Z" -> {
                        ourHand = "C";
                        totalScore += 3;
                    }
                }

                // Defining how many points the game output gives us
                totalScore += gameOutput(opponentHand, ourHand);
            }

            System.out.println("The game score is: " + totalScore);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Returns the output of the Rock, Paper, Scissor game (0 if lost, 3 if tied, 6 if won)
    // A: Rock, B: Paper, C: Scissor
    private static int gameOutput(String opponentHand, String ourHand) {
        if (opponentHand.equals(ourHand)) {
            return 3;
        } else if (opponentHand.equals("A")) {
            return Objects.equals(ourHand, "B") ? 6 : 0;
        } else if (opponentHand.equals("B")) {
            return Objects.equals(ourHand, "C") ? 6 : 0;
        } else {
            return Objects.equals(ourHand, "A") ? 6 : 0;
        }
    }
}