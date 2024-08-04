import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static List<List<Integer>> lines;

    public static void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();
            while (line != null) {
                List<Integer> intLine = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
                lines.add(intLine);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.print("\n\n");
    }

    public static Integer findNextValue(List<Integer> line) {
        if (line.stream().anyMatch(i -> i != 0)) {
            List<Integer> nextLine = new Vector<>();
            Integer prevValue = null;
            for (Integer value : line) {
                if (prevValue == null) {
                    prevValue = value;
                } else {
                    nextLine.add(value - prevValue);
                    prevValue = value;
                }
            }

            return (findNextValue(nextLine) + line.getLast());
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file

        lines = new Vector<>();
        readFile(fileName);

        Integer total = 0;
        for (List<Integer> line : lines) {
            Integer nextValue = findNextValue(line);
            total += nextValue;
        }

        System.out.printf("Result: %d\n", total);
    }
}