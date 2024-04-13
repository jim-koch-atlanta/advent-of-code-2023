import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Car c = new Car("Ford", "Focus", 2013, 139876);
        c.printSummary();

        Integer[][] input = {
                {8, 4},
                {8, 2},
                {8, 0},
                {9, 3},
                {9, 4}
        };

        for (Integer[] pair : input) {
            int d = JimMath.divide(pair[0], pair[1]);
            System.out.printf("%d / %d = %d\n", pair[0], pair[1], d);
        }

        ArrayList<String> studentNames = new ArrayList<String>();
        studentNames.add("Ethan");
        studentNames.add("Courtney");
        studentNames.add("Kerri");
        studentNames.add("Sian");
        studentNames.add("Jim");

        System.out.println("Class roster");
        System.out.println("------------");
        studentNames.forEach(studentName -> {
            System.out.println(studentName);
        });
        System.out.println();

        Map<String, Integer> studentGrades = new HashMap<String, Integer>();
        studentGrades.put("Kerri", 100); // The perfectionist.
        studentGrades.put("Jim", 98); // The less smart perfectionist.
        studentGrades.put("Courtney", 91); // She's smart, but she studies last minute.
        studentGrades.put("Sian", 84); // She's smart, but she doesn't bother studying.
        studentGrades.put("Ethan", 61); // The class clown.

        System.out.println("Class roster");
        System.out.println("------------");
        studentGrades.forEach((name, grade) -> {
            System.out.println(name + ": " + grade);
        });


        BankAccount kerri = new BankAccount("Kerri", 1234.56); // Buys lots of pins.
        kerri.deposit(5000.0); // Pay day!
        kerri.withdraw(5100.0); // Pin purchase!

        try (BankAccount jim = new BankAccount("Jim", 12345.67)) {
            jim.deposit(5000.0); // Pay day!
            jim.withdraw(17345.68); // Bank frog.
        } catch (Exception ex) {
            System.out.println("EXCEPTION!!!");
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}