import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static List<String> allNodes;

    static String directions;

    // What happens if you go left?
    static Map<String, String> left;

    // What happens if you go right?
    static Map<String, String> right;

    // We now will have a full list of current nodes. Track them as a list.
    static List<String> currentNodes;

    static Map<String, NodeDestinations> nodeDestinationsMap;

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
                allNodes.add(currentNode);
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

    public static void createNodeDestinationsMap() {
        // We're going to pre-process the different Z's that you can reach
        // from each starting node. This will reduce the processing needed
        // while iterating.
        for (String node : allNodes) {
            String currentNode = node;

            int step = 0;
            NodeDestinations nodeDestinations = new NodeDestinations();
            for (char direction : directions.toCharArray()) {
                currentNode = makeMove(currentNode, direction);
                step++;
                if (currentNode.charAt(2) == 'Z') {
                    nodeDestinations.stepsToZNodes.add(step);
                }
            }
            nodeDestinations.destination = currentNode;
            nodeDestinationsMap.put(node, nodeDestinations);
        }
    }

    public static Integer findMatch() {
        NodeDestinations nodeDestinationsZero = null;
        List<NodeDestinations> nodeDestinationsList = new Vector<>();
        for (String currentNode : currentNodes) {
            if (nodeDestinationsZero == null) {
                nodeDestinationsZero = nodeDestinationsMap.get(currentNode);
            } else {
                nodeDestinationsList.add(nodeDestinationsMap.get(currentNode));
            }
        }

        boolean foundMatch = true;
        assert nodeDestinationsZero != null;
        Integer currentSteps = null;
        for (Integer steps : nodeDestinationsZero.stepsToZNodes) {
            foundMatch = true;
            currentSteps = steps;
            for (NodeDestinations nd : nodeDestinationsList) {
                if (!nd.stepsToZNodes.contains(steps)) {
                    foundMatch = false;
                    break;
                }
            }
        }

        if (foundMatch) {
            return currentSteps;
        }

        return null;
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file
        allNodes = new Vector<>();
        left = new HashMap<>();
        right = new HashMap<>();
        currentNodes = new Vector<>();
        nodeDestinationsMap = new HashMap<>();
        steps = 0;

        readFile(fileName);
        int directionsLength = directions.length();
        createNodeDestinationsMap();

        Integer match = null;
        do {
            match = findMatch();
            if (match != null) {
                steps = steps + match;
                break;
            }

            currentNodes.replaceAll(node -> nodeDestinationsMap.get(node).destination);
            steps = steps + directionsLength;
        } while (match == null);
        System.out.printf("\nSteps: %d", steps);
    }
}