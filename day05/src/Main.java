import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main {
    static List<ConversionMap> maps;
    static List<Long> seeds;

    public static void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // Read seeds.
            // seeds: 79 14 55 13
            // Skip the first 7 characters. Split by space.
            String line = br.readLine();
            line = line.substring(7);
            String[] strSeeds = line.split(" ");
            for (String strSeed : strSeeds) {
                Long seed = Long.parseLong(strSeed);
                seeds.add(seed);
            }

            // Read the blank line.
            line = br.readLine();

            // Read lines from the file until the end is reached
            while (line != null) {
                // Read the map type.
                line = br.readLine();

                // Print the map type.
                System.out.println("Map type: " + line);

                ConversionMap map = new ConversionMap();

                // Read the map type.
                line = br.readLine();
                while ((line != null) && (!line.isEmpty())) {
                    String[] values = line.split(" ");
                    map.addElement(
                            Long.parseLong(values[0]),
                            Long.parseLong(values[1]),
                            Long.parseLong(values[2]));
                    line = br.readLine();
                }

                maps.add(map);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file
        maps = new ArrayList<>();
        seeds = new Vector<>();
        readFile(fileName);

        for (Long seed : seeds) {
            System.out.println("Seed: " + seed);
            for (ConversionMap map : maps) {
                seed = map.convert(seed);
            }
            System.out.println("Final Seed: " + seed);
            System.out.println();
        }
    }
}