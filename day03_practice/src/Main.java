import generics.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println("Element: " + element.toString());
        }
    }

    public static void main1(String[] args) {
        System.out.println("Hello world!");

        BankAccount ba1 = new BankAccount("Jim", 12345.67);
        Box<BankAccount> box1 = new Box<>(ba1);

        BankAccount ba2 = new BankAccount("Kerri", 23456.78);
        Box<BankAccount> box2 = new Box<>(ba2);

        Box[] array = {box1, box2};
        printArray(array);
    }

    public static <T> List<T> removeDuplicates(List<T> originalList) {
        ArrayList<T> newList = new ArrayList<T>(originalList);
        for (int i = 0; i < newList.size(); i++) {
            for (int j = i + 1; j < newList.size(); j++) {
                if (newList.get(i).equals(newList.get(j))) {
                    newList.remove(j);
                }
            }
        }

        return newList;
    }

    public static void main2(String[] args) {
        List<Integer> intList = List.of(10, 20, 30, 40, 50,
                60, 70, 80, 90, 100, 110);
        List<Double> doubleList = List.of(3.5, 6.2, 8.9);

        double maxInt = NumberUtils.findMax(intList);
        double maxDouble = NumberUtils.findMax(doubleList);

        System.out.println("Maximum Integer: " + maxInt);
        System.out.println("Maximum Double: " + maxDouble);

        NumberUtils.printNumber(123456.78 / 10.0);

        Pair<String, Integer> p1 = new Pair<>("Jim", 12345);
        Pair<String, Integer> p2 = new Pair<>("Kerri", 123456);

        Pair<Integer, String> p3 = p1.swap();
        Pair<Integer, String> p4 = p2.swap();

        System.out.printf("%s %s\n", p3.getSecondElement(), p4.getSecondElement());

        List<Integer> l1 = List.of(10, 20, 30, 40, 50, 20, 30, 40, 50, 60, 10, 70, 80);
        List<Integer> l2 = removeDuplicates(l1);
        printArray(l2.toArray());
    }
    public static void main(String[] args) {
    }
}