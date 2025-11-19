package org.iaschowrai.string;

import java.util.stream.Stream;

public class ProblemOne {
    public static void main(String[] args) {

        actualProblemAskedInInterview();
        modifiedTheProblem();

    }

    private static void actualProblemAskedInInterview() {
        int[] counter = {0};

        Stream.of("apple", "banana", "cherry")
                .filter(s -> {
                    counter[0]++;
                    return s.startsWith("a");
                });
        System.out.println("Counter: " + counter[0]);
    }
    private static void modifiedTheProblem() {
        int[] counter = {0};

        long count =  Stream.of("apple", "banana", "cherry")
                .filter(s -> {
                    counter[0]++;
                    return s.startsWith("a");
                }).count();
        System.out.println("Counter: " + counter[0]);
        System.out.println("Count: " + count);
    }
}
