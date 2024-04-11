import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static int getFirstNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                return c - '0';
            }
        }
        return 0;
    }

    public static int getLastNumber(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                return c - '0';
            }
        }

        return 0;
    }

    public static Integer getRealNumber(String s, int i) {
        char c = s.charAt(i);
        if (Character.isDigit(c)) {
            return c - '0';
        } else {
            String substring = s.substring(i);
            if (substring.startsWith("one")) {
                return 1;
            } else if (substring.startsWith("two")) {
                return 2;
            } else if (substring.startsWith("three")) {
                return 3;
            } else if (substring.startsWith("four")) {
                return 4;
            } else if (substring.startsWith("five")) {
                return 5;
            } else if (substring.startsWith("six")) {
                return 6;
            } else if (substring.startsWith("seven")) {
                return 7;
            } else if (substring.startsWith("eight")) {
                return 8;
            } else if (substring.startsWith("nine")) {
                return 9;
            } else if (substring.startsWith("zero")) {
                return 0;
            }
        }

        return null;
    }
    public static int getFirstRealNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            Integer realNumber = getRealNumber(s, i);
            if (realNumber != null) {
                return realNumber;
            }
        }
        return 0;
    }

    public static int getLastRealNumber(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            Integer realNumber = getRealNumber(s, i);
            if (realNumber != null) {
                return realNumber;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file

        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            // Read lines from the file until the end is reached
            while (line != null) {
                int first = getFirstRealNumber(line);
                int last = getLastRealNumber(line);
                int number = first * 10 + last;
                total += number;
                System.out.printf("%02d, %02d: %s%n", first, last, line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.printf("Total: %d", total);
    }
}