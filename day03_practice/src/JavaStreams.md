Sure! Here's a training lesson on the Introduction to Java Streams API:

---

### Introduction to Java Streams API

#### Explanation:
The Java Streams API, introduced in Java 8, provides a powerful and functional approach to processing collections of objects. Streams allow you to perform operations such as filtering, mapping, sorting, and aggregating elements in a concise and declarative manner.

#### Key Concepts:
1. **Stream**: A sequence of elements that supports sequential and parallel aggregate operations.
2. **Intermediate Operations**: Operations that transform a stream into another stream, such as `filter`, `map`, `sorted`, etc.
3. **Terminal Operations**: Operations that produce a result or side-effect, such as `forEach`, `collect`, `reduce`, etc.
4. **Parallel Streams**: Streams that can leverage multiple threads to perform operations in parallel, improving performance on multi-core processors.

#### Example:
Let's consider an example where we have a list of integers and we want to perform various operations using the Streams API:

```java
import java.util.Arrays;
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Example 1: Filter even numbers and print them
        System.out.println("Even numbers:");
        numbers.stream()
               .filter(num -> num % 2 == 0)
               .forEach(System.out::println);

        // Example 2: Map each number to its square and print
        System.out.println("Squared numbers:");
        numbers.stream()
               .map(num -> num * num)
               .forEach(System.out::println);

        // Example 3: Reduce the stream to calculate sum
        int sum = numbers.stream()
                        .reduce(0, Integer::sum);
        System.out.println("Sum of numbers: " + sum);
    }
}
```

#### Output:
```
Even numbers:
2
4
6
8
10
Squared numbers:
1
4
9
16
25
36
49
64
81
100
Sum of numbers: 55
```

#### Practice Exercise:
Write a program that reads a list of strings, filters out strings containing the letter 'a', converts the remaining strings to uppercase, and prints them.

---

This lesson should provide a solid introduction to the Java Streams API and its basic usage. Feel free to experiment further with different stream operations and explore its capabilities in depth.