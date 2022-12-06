package de.neuefische;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> words = new ArrayList<>(
            List.of(
                "cat",
                "dog",
                "unicorn",
                "horse",
                "snake",
                "sheep"
            )
        );

        System.out.println("Words");
        words.stream().forEach(word -> System.out.println(word));

        for (String word : words) {
            System.out.println(word);
        }

        System.out.println();
        System.out.println("Word lengths");
        words.stream().forEach(word -> System.out.println(word.length()));

        List<Integer> lengths = words
            .stream()
            .map(word -> {
                int length = word.length();
                return length;
            })
            .collect(Collectors.toList());
        System.out.println();
        System.out.println("Word lengths as list");
        System.out.println(lengths);

        List<Integer> lengthsFor = new ArrayList<>();
        for (String word : words) {
            lengthsFor.add(word.length());
        }

        List<String> startsWithS = words
                .stream()
                .filter(word -> {
                    return word.startsWith("s");
                }).collect(Collectors.toList());
        System.out.println();
        System.out.println("Words starting with s");
        System.out.println(startsWithS);

        List<String> startsWithSFor = new ArrayList<>();
        for (String word : words) {
            if (word.startsWith("s")) {
                startsWithSFor.add(word);
            }
        }

        String allTogether = words
                .stream()
                .reduce((word, sum) -> {
                    return sum + word;
                }).orElse("");
        System.out.println();
        System.out.println("All together");
        System.out.println(allTogether);

        int characterCount = words
            .stream()
            .map(word -> word.length())
            .filter(count -> count > 3)
            .reduce((count, sum) -> {
                return sum + count;
            }).orElse(0);
        System.out.println();
        System.out.println("Character Count");
        System.out.println(characterCount);

        System.out.println();
        System.out.println("Sorted");
        System.out.println(words.stream().sorted().collect(Collectors.toList()));

        int[] zahlen = new int[]{1, 2, 3, 4};
        System.out.println("Sum: " + sum(zahlen));
        System.out.println("Product: " + product(zahlen));

        System.out.println(parseStudentsCSV());
        System.out.println(parseStudentsCSVFor());
    }

    public static int sum (int[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    public static int product (int[] numbers) {
        return Arrays.stream(numbers)
                .reduce((number, sum) -> sum * number)
                .orElse(0);
    }

    // ["1,Stefan,12345,40", "1,Stefan,12345,40"]
    // [["1", "Stefan", "12345", "40"], [], ["1", "Stefan", "12345", "40"]]
    // [["1", "Stefan", "12345", "40"], ["Abc", "Stefan", "12345", "40"]]
    // [["1", "Stefan", "12345", "40"]]
    // [Student[id=1,name='Stefan',postalCode='12345',age=40]]
    public static List<Student> parseStudentsCSV () throws IOException {
        return Files.lines(
                Path.of(
                    "/Users/bitbytejoy/Downloads/neuefische-streams/src/main/resources/students.csv"
                )
            ).skip(1)
            .map(line -> line.split(","))
            .filter(properties -> properties.length == 4)
            .filter(properties -> {
                try {
                    Integer.parseInt(properties[0]);
                    Integer.parseInt(properties[3]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            })
            .map(properties -> new Student(
                Integer.parseInt(properties[0]),
                properties[1],
                properties[2],
                Integer.parseInt(properties[3])
            )).distinct()
            .collect(Collectors.toList());
    }

    public static List<Student> parseStudentsCSVFor () throws IOException {
        List<String> lines = Files.readAllLines(
            Path.of(
            "/Users/bitbytejoy/Downloads/neuefische-streams/src/main/resources/students.csv"
            )
        );

        Set<Student> studentsUnique = new HashSet<>();

        for (String line : lines) {
            List<String> properties = List.of(line.split(","));

            if (properties.size() != 4) {
                continue;
            }

            try {
                Integer.parseInt(properties.get(0));
                Integer.parseInt(properties.get(3));
            } catch (NumberFormatException e) {
                continue;
            }

            studentsUnique.add(new Student(
                Integer.parseInt(properties.get(0)),
                properties.get(1),
                properties.get(2),
                Integer.parseInt(properties.get(3))
            ));
        }

        return new ArrayList<>(studentsUnique);
    }
}