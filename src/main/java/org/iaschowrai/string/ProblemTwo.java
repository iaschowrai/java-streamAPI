package org.iaschowrai.string;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProblemTwo {
    public static void main(String[] args) {
        actualProblem();
        solutionOne();
    }

    private static void solutionOne() {
        List<String> list = List.of("a", "b", "a");

        Map<Character, String> map = list.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s,
                        (existing, replacement) -> existing  // keep the first value
                ));

        map.entrySet().forEach(System.out::println);

    }

    private static void actualProblem() {

        List<String> list = List.of("a", "b", "a");

        Map<Character, String> map = list.stream()
                .collect(Collectors.toMap(s -> s.charAt(0), S-> S));

        map.entrySet().stream().forEach(System.out::println);


    }
}
