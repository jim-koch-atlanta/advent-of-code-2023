import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static String directions;

    // What happens if you go left?
    static Map<String, String> left;

    // What happens if you go right?
    static Map<String, String> right;
    public static void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            directions = br.readLine();

            // Read the empty line.
            br.readLine();

            String line = br.readLine();
            while (line != null) {

                // FTD = (QRN, JJC)
                String currentNode = line.substring(0, 3);
                String leftNode = line.substring(7, 10);
                String rightNode = line.substring(12, 15);
                System.out.printf("%s: %s, %s\n", currentNode, leftNode, rightNode);
                left.put(currentNode, leftNode);
                right.put(currentNode, rightNode);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static String makeMove(String currentNode, char direction) {
        if (direction == 'L') {
            return left.get(currentNode);
        } else {
            return right.get(currentNode);
        }
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file
        left = new HashMap<>();
        right = new HashMap<>();

        readFile(fileName);

        // Let's go!
        int steps = 0;
        String currentNode = "AAA";
        while (!currentNode.equals("ZZZ")) {
            for (char c : directions.toCharArray()) {
                System.out.printf("Current Node: %s, Move: %c\n", currentNode, c);
                currentNode = makeMove(currentNode, c);
                steps++;
                if (currentNode.equals("ZZZ"))
                    break;
            }
        }

        System.out.printf("\nSteps: %d", steps);
    }
}