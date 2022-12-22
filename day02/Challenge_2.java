import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Challenge_2 {
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

                // Defining whether we win, tie or lose
                switch (ourHand) {
                    case "X" -> {
                        // Defining which hand to play to win, tie or lose
                        totalScore += gameOutput(opponentHand, "A") == 0 ? 1 : gameOutput(opponentHand, "B") == 0 ? 2 : 3;
                    }
                    case "Y" -> {
                        totalScore += 3;
                        // Defining which hand to play to win, tie or lose
                        totalScore += gameOutput(opponentHand, "A") == 3 ? 1 : gameOutput(opponentHand, "B") == 3 ? 2 : 3;
                    }
                    case "Z" -> {
                        totalScore += 6;
                        // Defining which hand to play to win, tie or lose
                        totalScore += gameOutput(opponentHand, "A") == 6 ? 1 : gameOutput(opponentHand, "B") == 6 ? 2 : 3;
                    }
                }
            }

            scanner.close();

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
