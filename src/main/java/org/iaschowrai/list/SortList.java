package org.iaschowrai.list;

import java.util.*;
import java.util.stream.Collectors;

public class SortList {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 9, 3, 8, 2, 6, 4, 7, 5);
        System.out.println("Original List: " + list);

        // =========================================
        // 1️⃣ Comparable-based Sorting (Natural Order)
        // =========================================

        System.out.println("\n--- Comparable: Natural Sorting ---");

        // a) Using Collections.sort() - uses natural order via Comparable
        List<Integer> ascCollections = new ArrayList<>(list); // copy to preserve original
        Collections.sort(ascCollections);
        System.out.println("Collections.sort (ascending natural order): " + ascCollections);

        // b) Using Stream.sorted() - natural order
        List<Integer> ascStream = list.stream()
                .sorted() // relies on Comparable implementation (Integer.compareTo)
                .collect(Collectors.toList());
        System.out.println("Stream.sorted (ascending natural order): " + ascStream);

        // c) Using Stream.sorted() with compareTo explicitly
        List<Integer> ascCompareTo = list.stream()
                .sorted((a, b) -> a.compareTo(b))
                .collect(Collectors.toList());
        System.out.println("Stream.sorted with compareTo (ascending): " + ascCompareTo);


        // d) Using arithmetic (lambda) - for integers only
        List<Integer> ascArithmetic = list.stream()
                .sorted((a, b) -> a - b)
                .collect(Collectors.toList());
        System.out.println("Stream.sorted with arithmetic (a-b) ascending: " + ascArithmetic);

        // e) Using map transformation and then sorting
        List<Integer> ascMap = list.stream()
                .map(n -> n * 1) // any transformation
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Stream.sorted after map (ascending): " + ascMap);

        // f) Using peek to observe elements before and after sort
        List<Integer> ascPeek = list.stream()
                .peek(n -> System.out.print("Before sort: " + n + " "))
                .sorted()
                .peek(n -> System.out.print("| After sort: " + n + " "))
                .collect(Collectors.toList());
        System.out.println("\nStream.sorted with peek: " + ascPeek);

        // =========================================
        // 2️⃣ Comparator-based Sorting (Custom Order)
        // =========================================
        System.out.println("\n--- Comparator: Custom Sorting ---");

        // a) Collections.sort with Comparator.reverseOrder() - descending
        List<Integer> descCollections = new ArrayList<>(list);
        Collections.sort(descCollections, Comparator.reverseOrder());
        System.out.println("Collections.sort (descending Comparator.reverseOrder()): " + descCollections);

        // b) List.sort with Comparator.reverseOrder()
        List<Integer> descListSort = new ArrayList<>(list);
        descListSort.sort(Comparator.reverseOrder());
        System.out.println("List.sort (descending Comparator.reverseOrder()): " + descListSort);

        // c) List.sort with lambda using compareTo (custom descending)
        List<Integer> descLambdaCompareTo = new ArrayList<>(list);
        descLambdaCompareTo.sort((a, b) -> b.compareTo(a));
        System.out.println("List.sort with lambda (b.compareTo(a)) descending:" + descLambdaCompareTo);
        //        descLambdaCompareTo.forEach(System.out::println);

        // d) Stream.sorted with Comparator.reverseOrder()
        List<Integer> descStream = list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Stream.sorted (descending Comparator.reverseOrder()): " + descStream);

        // e) Stream.sorted with lambda using Integer.compare
        List<Integer> descStreamCompare = list.stream()
                .sorted((a, b) -> Integer.compare(b, a))
                .collect(Collectors.toList());
        System.out.println("Stream.sorted with lambda Integer.compare (descending): " + descStreamCompare);

        // f) Stream.sorted using Comparator.comparing (method reference)
        List<Integer> ascComparatorMethodRef = list.stream()
                .sorted(Comparator.comparing(Integer::intValue)) // ascending natural order
                .collect(Collectors.toList());
        System.out.println("Stream.sorted with Comparator.comparing (Integer::intValue ascending): " + ascComparatorMethodRef);

        // g) Stream.sorted using Comparator.comparing (method reference) in descending order
        List<Integer> descComparatorMethodRef = list.stream()
                .sorted(Comparator.comparing(Integer::intValue).reversed()) // descending order
                .collect(Collectors.toList());
        System.out.println("Stream.sorted with Comparator.comparing (Integer::intValue descending): " + descComparatorMethodRef);

    }
}
