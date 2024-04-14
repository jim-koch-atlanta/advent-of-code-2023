import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountWords {
    public static void main(String[] args) {
        int wordCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String words[] = line.split("[ ]+");
                wordCount += words.length;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(wordCount);
    }
}
