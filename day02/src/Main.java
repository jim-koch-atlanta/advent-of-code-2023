import practice.*;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.*;

public class Main {
    public static void main0(String[] args) {
        System.out.println("Hello world!");
        Person jim = new Person("Jim", 43, "male");
        jim.displayInfo();
        jim.celebrateBirthday();
    }

    public static void main1(String[] args) {
        System.out.println("Hello world!");
        String[] friends = {"Kerri", "Sian", "Courtney", "Rick", "Brian", "Ethan"};
        Person jim = new Jim("Software Engineer", friends);
        jim.displayInfo();
        jim.celebrateBirthday();
    }

    public static void main2(String[] args) {
        System.out.println("Hello world!");
        String[] friends = {"Kerri", "Sian", "Courtney", "Rick", "Brian", "Ethan"};
        Person jim = new Jim("Software Engineer", friends);
        jim.displayInfo();
        jim.celebrateBirthday();
    }

    public static Map<String, Integer> COLOR_COUNT;
    public static boolean isValid(String pull) {
        while (!pull.isEmpty()) {
            int comma = pull.indexOf(',');
            String currentColor = "";
            if (comma >= 0) {
                currentColor = pull.substring(0, comma);
                pull = pull.substring(comma + 2); // Skip comma AND space.
            } else {
                currentColor = pull;
                pull = "";
            }

            int space = currentColor.indexOf(' ');
            int count = Integer.parseInt(currentColor.substring(0, space));
            String color = currentColor.substring(space + 1);

            int max = COLOR_COUNT.get(color);
            if (count > max) {
                System.out.println("Not valid! " + count + " " + color);
                return false;
            }
        }
        return true;
    }

    /**
     * This function will evaluate a line of text like:
     *    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
     * It will determine if this game is possible if the bag contains only:
     *    12 red cubes, 13 green cubes, and 14 blue cubes
     * i.e. Does each "pull" have less than that number of each.
     * @param line The line of text representing the game.
     * @return If the game is valid, the game number. Else, 0.
     */
    public static int getGameNumber(String line) {
        // Get the game number.
        line = line.substring(5);
        int colon = line.indexOf(':');
        int gameNumber = Integer.parseInt(line.substring(0, colon));
        line = line.substring(colon + 2); // Skip colon AND space.

        // We stripped off the game number.  Now iterate through the "bag pulls".
        while (!line.isEmpty()) {
            String currentPull = "";
            int semicolon = line.indexOf(';');
            if (semicolon >= 0) {
                currentPull = line.substring(0, semicolon);
                line = line.substring(semicolon + 2); // Skip semicolon AND space.
            } else {
                currentPull = line;
                line = "";
            }
            System.out.println("Current pull: " + currentPull);
            if (!isValid(currentPull)) {
                System.out.println("Game " + gameNumber + ", Invalid pull: [" + currentPull + "]");
                return 0;
            }
        }
        return gameNumber;
    }

    public static void main(String[] args) {
        COLOR_COUNT = new HashMap<String, Integer>();
        COLOR_COUNT.put("red", 12);
        COLOR_COUNT.put("green", 13);
        COLOR_COUNT.put("blue", 14);

        String fileName = "src/input.txt"; // Specify the path to your text file

        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            // Read lines from the file until the end is reached
            while (line != null) {
                int gameNumber = getGameNumber(line);
                total += gameNumber;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.printf("Total: %d", total);
    }
}