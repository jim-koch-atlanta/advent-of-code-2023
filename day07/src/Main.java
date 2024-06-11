import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static List<Hand> hands;
    public static void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();
            while (line != null) {
                Hand h = new Hand(line);
                hands.add(h);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file
        hands = new ArrayList<>();
        readFile(fileName);

        Collections.sort(hands);

        long rank = 1;
        long total = 0;
        for (Hand h : hands) {
            System.out.printf("Rank: %03d, Bid: %03d, Hand: %s\n", rank, h.getBid(), h.toString());
            total += h.getBid() * rank;
            rank++;
        }

        System.out.println("Total = " + total);
    }
}