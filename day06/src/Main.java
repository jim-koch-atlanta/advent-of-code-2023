import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Long> times;
    public static List<Long> distanceRecords;

    public static void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // Read times.
            // Time:        44     80     65     72
            // Skip the first 5 characters. Split by space.
            String line = br.readLine();
            line = line.substring(5);
            String[] strTimes = line.split(" ");
            for (int i = 0; i < strTimes.length; i = i + 1) {
                String strTime = strTimes[i];
                if (!strTime.isEmpty()) {
                    Long time = Long.parseLong(strTime);
                    times.add(time);
                }
            }

            // Read distance records.
            // Distance:   208   1581   1050   1102
            // Skip the first 9 characters. Split by space.
            line = br.readLine();
            line = line.substring(9);
            String[] strDistanceRecords = line.split(" ");
            for (int i = 0; i < strDistanceRecords.length; i = i + 1) {
                String strDistanceRecord = strDistanceRecords[i];
                if (!strDistanceRecord.isEmpty()) {
                    Long distanceRecord = Long.parseLong(strDistanceRecord);
                    distanceRecords.add(distanceRecord);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main_part1(String[] args) {
        String fileName = "src/input.txt"; // Specify the path to your text file
        times = new ArrayList<>();
        distanceRecords = new ArrayList<>();
        readFile(fileName);

        long value = 1;
        for (int i = 0; i < times.size(); i++) {
            long currentTime = times.get(i);
            long currentDistanceRecord = distanceRecords.get(i);

            int waysToWin = 0;
            for (long velocity = 0; velocity <= currentTime; velocity++) {
                long distance = velocity * (currentTime - velocity);
                if (distance > currentDistanceRecord) {
                    waysToWin++;
                }
            }

            value = value * waysToWin;
        }

        System.out.println(value);
    }

    public static void main(String[] args) {
        long time = 44806572;
        BigInteger distance = new BigInteger("208158110501102");

        long waysToWin = 0;
        for (long velocity = 0; velocity <= time; velocity++) {
            long timeMoving = time - velocity;
            BigInteger biTimeMoving = new BigInteger(Long.toString(timeMoving));
            BigInteger biVelocity = new BigInteger(Long.toString(velocity));
            if (biTimeMoving.multiply(biVelocity).compareTo(distance) > 0) {
                waysToWin++;
            }
        }

        System.out.println(waysToWin);
    }
}