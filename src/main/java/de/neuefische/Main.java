package de.neuefische;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
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
    }
}