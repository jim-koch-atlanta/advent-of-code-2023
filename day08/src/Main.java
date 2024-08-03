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

    // We now will have a full list of current nodes. Track them as a list.
    static List<String> currentNodes;

    static long steps;

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
                // System.out.printf("%s: %s, %s\n", currentNode, leftNode, rightNode);
                left.put(currentNode, leftNode);
                right.put(currentNode, rightNode);

                if (currentNode.endsWith("A")) {
                    System.out.printf("Starting node: %s\n", currentNode);
                    currentNodes.add(currentNode);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.printf("\n\n");
    }

    public static String makeMove(String currentNode, char direction) {
        if (direction == 'L') {
            return left.get(currentNode);
        } else {
            return right.get(currentNode);
        }
    }

    public static boolean makeMoves(char direction) {
        boolean foundAllZ = true;
        for (int i = 0; i < currentNodes.size(); i++) {
            String currentNode = currentNodes.get(i);
            currentNode = makeMove(currentNode, direction);
            if (!currentNode.endsWith("Z")) {
                foundAllZ = false;
            }
            currentNodes.set(i, currentNode);
        }

        return foundAllZ;
    }

    public static void printCurrentState() {
        System.out.printf("Current steps: %d\n", steps);
        for (String currentNode : currentNodes) {
            System.out.println(currentNode);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file
        left = new HashMap<>();
        right = new HashMap<>();
        currentNodes = new Vector<>();
        steps = 0;

        readFile(fileName);

        // Let's go!
        boolean foundAllZ = false;
        while (!foundAllZ) {
            for (char direction : directions.toCharArray()) {
                foundAllZ = makeMoves(direction);
                steps++;

                if (steps % 10000000 == 0) {
                    System.out.printf("%d steps\n", steps);
                }

                if (foundAllZ) {
                    System.out.printf("FOUND ALL Z AT %d steps\n", steps);
                    break;
                }
            }
        }

        System.out.printf("\nSteps: %d", steps);
    }
}