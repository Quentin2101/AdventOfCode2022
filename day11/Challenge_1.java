import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenge_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        // Parsing input.txt
        File file = new File("input.txt"); // Parsed file
        ArrayList<Object[]> monkeysSpecifications = new ArrayList<>();
        parsingMonkeysSpecifications(file, monkeysSpecifications);

        // Processing the game
        int numberOfRounds = 20;

        playTheGame(numberOfRounds, monkeysSpecifications);

        // Return the level of monkey buisness
        int monkeyBusiness = findMonkeyBusiness(monkeysSpecifications);

        System.out.println("The level of monkey business after 20 rounds of stuff-slinging simian shenanigans is: " + monkeyBusiness);

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Return the multiplication of the 2 greatest number of times a monkey processed items
    private static int findMonkeyBusiness(ArrayList<Object[]> monkeysSpecifications) {
        int[] processingSet = new int[monkeysSpecifications.size()];

        for (int i = 0; i < monkeysSpecifications.size(); i++) {
            processingSet[i] = (int) monkeysSpecifications.get(i)[5];
        }
        Arrays.sort(processingSet);

        return processingSet[processingSet.length - 1] * processingSet[processingSet.length - 2];
    }

    private static void playTheGame(int numberOfRounds, ArrayList<Object[]> monkeysSpecifications) {
        for (int i = 0; i < numberOfRounds; i++) {
            for (int j = 0; j < monkeysSpecifications.size(); j++) {
                // Processing the current monkey items
                ArrayDeque<Integer> currentMonkeyItems = (ArrayDeque<Integer>) monkeysSpecifications.get(j)[0];
                while(!currentMonkeyItems.isEmpty()) {
                    int item = currentMonkeyItems.removeFirst();

                    // Processing the operation on the item
                    String[] currentMonkeyOperation = (String[]) monkeysSpecifications.get(j)[1];
                    int operationResult;
                    if (Objects.equals(currentMonkeyOperation[0], "old"))
                        operationResult = item;
                    else
                        operationResult = Integer.parseInt(currentMonkeyOperation[0]);
                    for (int k = 2; k < currentMonkeyOperation.length; k+=2) {
                        int value;
                        if (Objects.equals(currentMonkeyOperation[k], "old"))
                            value = item;
                        else
                            value = Integer.parseInt(currentMonkeyOperation[k]);

                        switch (currentMonkeyOperation[k - 1]) {
                            case "+" -> operationResult += value;
                            case "*" -> operationResult *= value;
                            case "-" -> operationResult -= value;
                            case "/" -> operationResult /= value;
                        }
                    }
                    operationResult/=3;

                    // Processing the condition on the operation result
                    int divisibleTestValue = (int) monkeysSpecifications.get(j)[2];
                    if (operationResult%divisibleTestValue == 0) {
                        int receivingMonkeyIndex = (int) monkeysSpecifications.get(j)[3];
                        ArrayDeque<Integer> receivingMonkeyItems = (ArrayDeque<Integer>) monkeysSpecifications.get(receivingMonkeyIndex)[0];
                        receivingMonkeyItems.addLast(operationResult);
                    } else {
                        int receivingMonkeyIndex = (int) monkeysSpecifications.get(j)[4];
                        ArrayDeque<Integer> receivingMonkeyItems = (ArrayDeque<Integer>) monkeysSpecifications.get(receivingMonkeyIndex)[0];
                        receivingMonkeyItems.addLast(operationResult);
                    }

                    // Adding 1 to the number of items processed by the monkey
                    monkeysSpecifications.get(j)[5] = (int) monkeysSpecifications.get(j)[5] + 1;
                }
            }
        }
    }

    // Parse the specifications  of monkeys from a file
    public static void parsingMonkeysSpecifications(File file, ArrayList<Object[]> monkeysSpecifications) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String specification = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.equals("")) {
                specification = specification.concat(line + "\n");
            } else {
                // Parsing specification
                parsingMonkeySpecifications(specification, monkeysSpecifications);
                specification = "";
            }
        }
        // Parsing the last specification not processed in the while statement
        parsingMonkeySpecifications(specification, monkeysSpecifications);
    }

    // Parse the specifications of a single monkey specification string
    private static void parsingMonkeySpecifications(String specification, ArrayList<Object[]> monkeysSpecifications) {
        // Adding the monkey specification to its array list
        monkeysSpecifications.add(new Object[6]);
        Object[] currentSpecification = monkeysSpecifications.get(monkeysSpecifications.size() - 1);
        currentSpecification[currentSpecification.length - 1] = 0;

        String[] parsedSpecification = specification.split("\n");
        for (int i = 1; i < parsedSpecification.length; i++) {
            String line = parsedSpecification[i].split(": ")[1];
            switch (i) {
                case 1 -> {
                    currentSpecification[0] = parsingItems(line);
                }
                case 2 -> {
                    currentSpecification[1] = parsingOperation(line);
                }
                case 3 -> {
                    currentSpecification[2] = parsingDivisibleTest(line);
                }
                case 4 -> {
                    currentSpecification[3] = parsingCondition(line);
                }
                case 5 -> {
                    currentSpecification[4] = parsingCondition(line);
                }
            }
        }
    }

    // Parse the condition line of specification
    private static int parsingCondition(String line) {
        return Integer.parseInt(line.split("throw to monkey ")[1]);
    }

    // Parse the divisible test line of specification
    private static int parsingDivisibleTest(String line) {
        return Integer.parseInt(line.split("divisible by ")[1]);
    }

    // Parse the operation line of a specification
    private static String[] parsingOperation(String operationLine) {
        String operationParsed = operationLine.split("new = ")[1];

        return operationParsed.split(" ");
    }

    // Parse the item line of a specification
    private static ArrayDeque<Integer> parsingItems(String itemLine) {
        String[] numbersParsed = itemLine.split(", ");
        ArrayDeque<Integer> items = new ArrayDeque<>();
        for (int j = 0; j < numbersParsed.length; j++) {
            items.addLast(Integer.parseInt(numbersParsed[j]));
        }

        return items;
    }
}
