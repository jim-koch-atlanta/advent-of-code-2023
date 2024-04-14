import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
