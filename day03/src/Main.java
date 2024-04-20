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

    public static Integer getAdjacentLeftNumber(String line, int i, int j) {
        boolean foundANumber = false;
        StringBuilder number = new StringBuilder();
        while ((j >= 0) && (Character.isDigit(line.charAt(j)))) {
            foundANumber = true;
            number.append(line.charAt(j));
            j--;
        }

        if (foundANumber) {
            return Integer.parseInt(number.reverse().toString());
        }

        return null;
    }

    public static Integer getAdjacentRightNumber(String line, int i, int j) {
        boolean foundANumber = false;
        int number = 0;
        while ((j < line.length()) && (Character.isDigit(line.charAt(j)))) {
            foundANumber = true;
            number = number * 10 + (line.charAt(j) - '0');
            j++;
        }

        if (foundANumber) {
            return number;
        }

        return null;
    }

    public static ArrayList<Integer> getAdjacentPartNumbers(String[] schematic, int i, int j) {
        System.out.printf("Looking for numbers adjacent to [%02d, %02d]\n", i, j);
        ArrayList<Integer> adjacentPartNumbers = new ArrayList<>();

        // Check to the left.
        Integer leftNumber = getAdjacentLeftNumber(schematic[i], i, j - 1);
        if (leftNumber != null) {
            System.out.println("Found a number to the left: " + leftNumber);
            adjacentPartNumbers.add(leftNumber);
        }

        // Check to the right.
        Integer rightNumber = getAdjacentRightNumber(schematic[i], i, j + 1);
        if (rightNumber != null) {
            System.out.println("Found a number to the right: " + rightNumber);
            adjacentPartNumbers.add(rightNumber);
        }

        // Check directly above.
        if (i > 0) {
            if (Character.isDigit(schematic[i - 1].charAt(j))) {
                // Find the left-most digit of the number.
                int tmpJ = j;
                while (tmpJ >= 0 && Character.isDigit(schematic[i - 1].charAt(tmpJ))) {
                    tmpJ--;
                }

                rightNumber = getAdjacentRightNumber(schematic[i - 1], i - 1, tmpJ + 1);
                if (rightNumber != null) {
                    System.out.println("Found a number above: " + rightNumber);
                    adjacentPartNumbers.add(rightNumber);
                }
            } else {
                // Check above left.
                leftNumber = getAdjacentLeftNumber(schematic[i - 1], i - 1, j - 1);
                if (leftNumber != null) {
                    System.out.println("Found a number above-left: " + leftNumber);
                    adjacentPartNumbers.add(leftNumber);
                }

                // Check above right.
                rightNumber = getAdjacentRightNumber(schematic[i - 1], i - 1, j + 1);
                if (rightNumber != null) {
                    System.out.println("Found a number above-right: " + rightNumber);
                    adjacentPartNumbers.add(rightNumber);
                }
            }
        }

        // Check directly below.
        if (i < schematic.length - 1) {
            if (Character.isDigit(schematic[i + 1].charAt(j))) {
                // Find the left-most digit of the number.
                int tmpJ = j;
                while (tmpJ >= 0 && Character.isDigit(schematic[i + 1].charAt(tmpJ))) {
                    tmpJ--;
                }

                rightNumber = getAdjacentRightNumber(schematic[i + 1], i - 1, tmpJ + 1);
                if (rightNumber != null) {
                    System.out.println("Found a number to the right: " + rightNumber);
                    adjacentPartNumbers.add(rightNumber);
                }
            } else {
                // Check below left.
                leftNumber = getAdjacentLeftNumber(schematic[i + 1], i + 1, j - 1);
                if (leftNumber != null) {
                    System.out.println("Found a number below-left: " + leftNumber);
                    adjacentPartNumbers.add(leftNumber);
                }

                // Check below right.
                rightNumber = getAdjacentRightNumber(schematic[i + 1], i + 1, j + 1);
                if (rightNumber != null) {
                    System.out.println("Found a number below-right: " + rightNumber);
                    adjacentPartNumbers.add(rightNumber);
                }
           }
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