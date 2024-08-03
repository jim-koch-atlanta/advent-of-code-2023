import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main2 {

    static String directions;

    // What happens if you go left?
    static Map<String, String> left;

    // What happens if you go right?
    static Map<String, String> right;

    // We now will have a full list of current nodes. Track them as a list.
    static List<String> startNodes;

    static Map<String, Long> stepsToZ;
    static Map<String, Long> stepsInCycle;

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

                left.put(currentNode, leftNode);
                right.put(currentNode, rightNode);

                if (currentNode.endsWith("A")) {
                    System.out.printf("Starting node: %s\n", currentNode);
                    startNodes.add(currentNode);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.print("\n\n");
    }

    public static String makeMove(String currentNode, char direction) {
        if (direction == 'L') {
            return left.get(currentNode);
        } else {
            return right.get(currentNode);
        }
    }

    public static void calculateCycles() {
        for (String startNode : startNodes) {
            String current = startNode;

            // Find a node that ends with Z.
            long steps = 0;
            do {
                for (char direction : directions.toCharArray()) {
                    current = makeMove(current, direction);
                    steps++;
                    if (current.charAt(2) == 'Z') {
                        break;
                    }
                }
            } while (current.charAt(2) != 'Z');

            System.out.printf("Start Node: %s, Z Node: %s, Steps To Z: %d\n", startNode, current, steps);
            stepsToZ.put(startNode, steps);

            String zNode = current;
            steps = 0;
            do {
                for (char direction : directions.toCharArray()) {
                    current = makeMove(current, direction);
                    steps++;
                    if (zNode.equals(current)) {
                        break;
                    }
                }
            } while (!zNode.equals(current));

            System.out.printf("Start Node: %s, Z Node: %s, Steps: %d\n\n", startNode, current, steps);
            stepsInCycle.put(startNode, steps);
        }
    }

    // Method to compute GCD using Euclidean algorithm
    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Method to compute LCM of two numbers
    public static long lcm(long a, long b) {
        return a * (b / gcd(a, b)); // Using a * (b / gcd(a, b)) to prevent overflow
    }

    // Method to compute LCM of multiple numbers
    public static long lcmOfArray(Long[] numbers) {
        return Arrays.stream(numbers).reduce(1L, Main2::lcm);
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file
        left = new HashMap<>();
        right = new HashMap<>();
        startNodes = new Vector<>();
        stepsToZ = new HashMap<>();
        stepsInCycle = new HashMap<>();

        readFile(fileName);
        calculateCycles();

        long steps = lcmOfArray(stepsInCycle.values().toArray(new Long[0]));

        System.out.printf("\nSteps: %d", steps);
    }
}