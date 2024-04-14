package generics;

import java.util.List;

public class NumberUtils {
    public static double findMax(List<? extends Number> numbers) {
        double max = Double.NEGATIVE_INFINITY;
        for (Number number : numbers) {
            double value = number.doubleValue();
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void printNumber(Number number) {
        System.out.println("Number: " + number.toString());
    }
}