import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class JavaStreams {
    public static void main(String[] args) {
        // Write a program that reads a list of strings,
        // filters out strings containing the letter 'a',
        // converts the remaining strings to uppercase,
        // and prints them.

        try (BufferedReader br = new BufferedReader(new FileReader("src/i_am.txt"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("ORIGINAL: " + line);
                System.out.printf("REVISED:  ");
                List<String> words = List.of(line.split("\\s+"));
                words.stream()
                        .filter(s -> s.indexOf('a') == -1 )
                        .map(s -> s.toUpperCase())
                        .forEach(s -> System.out.printf("%s ", s));
                System.out.println();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
