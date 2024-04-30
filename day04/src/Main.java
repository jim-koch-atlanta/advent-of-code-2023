import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    // Function to check if an array contains an element
    private static boolean contains(Integer[] array, Integer element) {
        for (int num : array) {
            if (num == element) {
                return true;
            }
        }
        return false;
    }

    public static void main0(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file

        int total = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

            // Read lines from the file until the end is reached
            while (line != null) {
                String[] colonSplit = line.split(":");
                String[] splits = colonSplit[1].split("\\|");
                String[] strCardNumbers = splits[0].split("\\s");
                String[] strMyNumbers = splits[1].split("\\s");

                Integer[] cardNumbers = Arrays.stream(strCardNumbers)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

                Integer[] myNumbers = Arrays.stream(strMyNumbers)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

                Integer[] matchingNumbers = Arrays.stream(cardNumbers)
                                .filter(cardNumber -> contains(myNumbers, cardNumber))
                                .toArray(Integer[]::new);

                if (matchingNumbers.length > 0) {
                    total += (int) Math.pow(2, matchingNumbers.length - 1);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Total: " + total);
    }

    public static Integer getMatches(String line) {
        String[] colonSplit = line.split(":");
        String[] splits = colonSplit[1].split("\\|");
        String[] strCardNumbers = splits[0].split("\\s");
        String[] strMyNumbers = splits[1].split("\\s");

        Integer[] cardNumbers = Arrays.stream(strCardNumbers)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        Integer[] myNumbers = Arrays.stream(strMyNumbers)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        Integer[] matchingNumbers = Arrays.stream(cardNumbers)
                .filter(cardNumber -> contains(myNumbers, cardNumber))
                .toArray(Integer[]::new);

        return matchingNumbers.length;
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file

        HashMap<Integer, Integer> Scratchcards = new HashMap<Integer, Integer>();
        int totalScratchcards = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            int currentLine = 0;

            // Read lines from the file until the end is reached
            while (line != null) {
                System.out.println(line);
                // How many scratchcards do I get of the current line?
                int numberOfScratchcards = (Scratchcards.getOrDefault(currentLine, 0));
                numberOfScratchcards++;

                System.out.println("Number of scratchcards: " + numberOfScratchcards);

                totalScratchcards += numberOfScratchcards;

                System.out.println("Total scratchcards    : " + totalScratchcards);

                // How many matches did I get on the current line?
                int numberOfMatches = getMatches(line);
                System.out.println("Number of matches     : " + numberOfMatches);
                for (int i = 1; i <= numberOfMatches; i++) {
                    // Update the number of scratchcards that I have available.
                    int previousNumberOfScratchcards = Scratchcards.getOrDefault(currentLine + i, 0);
                    int newNumberOfScratchcards = previousNumberOfScratchcards + numberOfScratchcards;
                    Scratchcards.put(currentLine + i, newNumberOfScratchcards);
                    System.out.printf("Scratchcards[%d]=[%d]\n", currentLine + i, newNumberOfScratchcards);
                }

                line = br.readLine();
                currentLine++;

                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Total Scratchcards: " + totalScratchcards);
    }
}