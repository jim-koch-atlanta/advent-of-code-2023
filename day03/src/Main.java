import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static boolean containsSymbol(
            ArrayList<String> schematic,
            int lineNum,
            int startIndex,
            int endIndex) {
        if ((lineNum < 0) || (lineNum >= schematic.size())) {
            return false;
        }

        startIndex = Math.max(0, startIndex - 1);
        endIndex = Math.min(schematic.size() - 1, endIndex + 2);

        String str = schematic.get(lineNum).substring(startIndex, endIndex);
        System.out.println("Testing " + str);

        String regex = "[^0-9.]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }
    public static boolean isAdjacentToSymbol(
            ArrayList<String> schematic,
            int lineNum,
            int startIndex,
            int endIndex) {
        // Check above.
        return containsSymbol(schematic, lineNum - 1, startIndex, endIndex) ||
                containsSymbol(schematic, lineNum, startIndex, endIndex) ||
                containsSymbol(schematic, lineNum + 1, startIndex, endIndex);
    }

    public static void main0(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file

        ArrayList<String> schematic = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            // Read lines from the file until the end is reached
            while (line != null) {
                schematic.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        int total = 0;
        // Iterate over the "schematic" looking for numbers.
        for (int lineNum = 0; lineNum < schematic.size(); lineNum++) {
            String line = schematic.get(lineNum);

            // Iterate over each individual line.
            for (int characterNum = 0; characterNum < line.length(); characterNum++) {
                int number = 0;
                boolean foundANumber = false;
                int startIndex = characterNum;
                int endIndex = characterNum;
                // We found a numerical digit. Let's iterate to the end of it.
                while ((characterNum < line.length()) &&
                        Character.isDigit(line.charAt(characterNum))) {
                    endIndex = characterNum;
                    foundANumber = true;
                    number = number * 10 + (line.charAt(characterNum) - '0');
                    characterNum++;
                }

                if (foundANumber) {
                    System.out.printf("Found a number %d at [%02d, %02d]\n",
                            number, startIndex, endIndex);

                    // Check to see if it's adjacent to a symbol.
                    if (isAdjacentToSymbol(schematic, lineNum, startIndex, endIndex)) {
                        System.out.println("Adjacent to a symbol!");
                        total += number;
                    }
                }
            }
        }
        System.out.println("number=" + total);
    }

    public static void main(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file

        ArrayList<String> schematic = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            // Read lines from the file until the end is reached
            while (line != null) {
                schematic.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


        int sumOfGearRatios = calculateSumOfGearRatios(schematic.toArray(new String[0]));
        System.out.println("Sum of gear ratios: " + sumOfGearRatios);
    }

    public static ArrayList<Integer> getAdjacentPartNumbers(String[] schematic, int i, int j) {
        System.out.printf("Looking for numbers adjacent to [%02d, %02d]\n", i, j);
        ArrayList<Integer> adjacentPartNumbers = new ArrayList<>();

        // Check to the left.
        boolean foundANumber = false;
        int number = 0;
        int tmpJ = j - 1;
        while ((tmpJ >= 0) && (Character.isDigit(schematic[i].charAt(tmpJ)))) {
            foundANumber = true;
            number = number * 10 + (schematic[i].charAt(tmpJ) - '0');
            tmpJ--;
        }

        if (foundANumber) {
            number = Integer.parseInt(new StringBuilder(Integer.toString(number)).reverse().toString());
            System.out.println("Found a number to the left: " + number);
            adjacentPartNumbers.add(number);
        }

        // Check to the right.
        foundANumber = false;
        number = 0;
        tmpJ = j + 1;
        while ((tmpJ < schematic[i].length()) && (Character.isDigit(schematic[i].charAt(tmpJ)))) {
            foundANumber = true;
            number = number * 10 + (schematic[i].charAt(tmpJ) - '0');
            tmpJ++;
        }

        if (foundANumber) {
            System.out.println("Found a number to the right: " + number);
            adjacentPartNumbers.add(number);
        }

        // Check directly above.
        if ((i > 0) && (Character.isDigit(schematic[i].charAt(j)))) {

        } else {
            // Check above left.
            // Check above right.
        }

        // Check directly below.
        if ((i < schematic.length - 1) && (Character.isDigit(schematic[i].charAt(j)))) {

        } else {
            // Check below left.
            // Check below right.
        }

        return adjacentPartNumbers;
    }

    public static int calculateSumOfGearRatios(String[] schematic) {
        int sum = 0;
        int rows = schematic.length;
        int cols = schematic[0].length();

        // Iterate through each cell in the schematic
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check if the current cell contains a *
                if (schematic[i].charAt(j) == '*') {
                    ArrayList<Integer> adjacentPartNumbers = getAdjacentPartNumbers(schematic, i, j);

                    // If exactly two adjacent part numbers found, add gear ratio to sum
                    if (adjacentPartNumbers.size() == 2) {
                        sum += adjacentPartNumbers.get(0) * adjacentPartNumbers.get(1);
                    }
                }
            }
        }
        return sum;
    }
}