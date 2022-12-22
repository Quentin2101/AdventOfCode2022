import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Challenge_1 {
    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();
        ArrayList<Stack<String>> stackArrayList = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(new File("input.txt"));
            String stackLines = ""; // Lines associated with stacks building
            String line = "-1";

            // Getting lines from input.txt that are associated with the stacks building
            while (scanner.hasNextLine() && !line.equals("\n")) {
                 line = scanner.nextLine() + "\n";
                 stackLines = stackLines.concat(line);
            }
            // Building the stacks with the lines given
            buildStackArrayLists(stackLines, stackArrayList);

            // Getting moves that need to be done on the list of stacks
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();

                // Parsing line to extract the move that needs to be done in the format [nbrOfElements, fromStack, toStack]
                String[] parsedLine = line.split(" ");
                int[] move = new int[3];
                move[0] = Integer.parseInt(parsedLine[1]);
                move[1] = Integer.parseInt(parsedLine[3]) - 1; // Indexing the stack number
                move[2] = Integer.parseInt(parsedLine[5]) - 1; // Indexing the stack number

                // Processing the move on stackArrayList
                processRearrangement(move, stackArrayList);
            }

            // Printing the top of each Stack
            for (Stack<String> stack : stackArrayList) {
                System.out.print(stack.peek().toCharArray()[1]);
            }
            System.out.print("\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Processes the move given (format [nbrOfElements, fromStack, toStack]) on stackArrayList
    private static void processRearrangement(int[] move, ArrayList<Stack<String>> stackArrayList) {
        for (int i = 0; i < move[0]; i++) {
            stackArrayList.get(move[2]).add(stackArrayList.get(move[1]).pop());
        }
    }

    // Builds the list of stacks associated with the stacks string given by input.txt
    private static void buildStackArrayLists(String stackLines, ArrayList<Stack<String>> stackArrayList) {
        String[] stackLinesParsed = stackLines.split("\n");

        String[] nbrOfStacks = stackLinesParsed[stackLinesParsed.length - 1].split("  ");

        // Building the ArrayList of stacks with the right number of stacks
        for (int i = 0; i < nbrOfStacks.length; i++) {
            stackArrayList.add(new Stack<>());
        }


        // Building stackArray by parsing each stackLines and retrieving their value to add to their associated Stack
        for (int i = stackLinesParsed.length - 2; i >= 0; i--) {
            String[] parsedLine = stackLinesParsed[i].split(" ");
            String[] newParsedLine = new String[nbrOfStacks.length]; // new parsedLine with the right size (deleting the 4 following empty string associated to nothing in parsedLine)

            // When 4 following "" is encountered, nbrOfSpace is incremented allowing to correctly implement newParsedLine
            int nbrOfSpace = 0;
            int followingSpaceCounter = 0;

            // Processing newParsedLine
            for (int j = 0; j < parsedLine.length; j++) {
                if (!parsedLine[j].equals("")) {
                    newParsedLine[j - 3 * nbrOfSpace] = parsedLine[j];
                } else {
                    followingSpaceCounter++;
                }
                if (followingSpaceCounter == 4) {
                    nbrOfSpace++;
                    newParsedLine[j - 3 * nbrOfSpace] = parsedLine[j];
                    followingSpaceCounter = 0;
                }
            }
            newParsedLine[nbrOfStacks.length - 1] = parsedLine[parsedLine.length - 1];

            // Adding to stacks their value
            for (int j = 0; j < newParsedLine.length; j++) {
                if(!newParsedLine[j].equals(""))
                    stackArrayList.get(j).add(newParsedLine[j]);
            }
        }
    }
}
