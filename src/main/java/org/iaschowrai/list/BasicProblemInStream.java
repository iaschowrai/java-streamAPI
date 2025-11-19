package org.iaschowrai.list;

import java.util.*;
import java.util.stream.Collectors;

public class BasicProblemInStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,3,4,4,5,6,7,8,9,9);
        System.out.println("Original List: " + list);

        // =========================================
        // 1️⃣ Remove duplicates (retain unique values only)
        // =========================================
        List<Integer> distinctList = list.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct elements: " + distinctList);  // [1,2,3,4,5,6,7,8,9]


        // =========================================
        // 2️⃣ Find duplicates (simplest approaches)
        // =========================================

        // Approach A: Using frequency (O(n²), good for small lists)
        Set<Integer> duplicatesUsingFrequency = list.stream()
                .filter(n -> Collections.frequency(list, n) > 1)
                .collect(Collectors.toSet());
        System.out.println("Duplicates using frequency: " + duplicatesUsingFrequency); // [3,4,9]

        // Approach B: Using Index (O(n²), good for small lists)
        Set<Integer>  duplicateListUsingIndex = list.stream()
                .filter(x -> list.indexOf(x) != list.lastIndexOf(x))
                .collect(Collectors.toSet());
        System.out.println("Duplicates using Index: " + duplicateListUsingIndex); // [3,4,9]

        // Approach C: Using a HashSet to track duplicates (O(n))
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicatesUsingSet = list.stream()
                .filter(n -> !seen.add(n)) // add() returns false if already exists
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Duplicates using HashSet: " + duplicatesUsingSet); // [3,4,9]`

        // =========================================
        // 3️⃣ Using Streams and groupingBy to count occurrences (efficient O(n))
        // =========================================

        Map<Integer, Long> frequencyMap = list.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        System.out.println("Frequency Map: " + frequencyMap);  // {1=1, 2=1, 3=2, 4=2, 5=1, 6=1, 7=1, 8=1, 9=2}

        // Extract only duplicates (count > 1)
        List<Integer> duplicatesWithGrouping = frequencyMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Duplicates from groupingBy: " + duplicatesWithGrouping); // [3,4,9]

        // Optional: Get duplicates along with their counts
        Map<Integer, Long> duplicatesWithCount = frequencyMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Duplicates with counts: " + duplicatesWithCount); // {3=2, 4=2, 9=2}

        // =========================================
        // 4️⃣ Using partitioningBy to separate unique and duplicate elements
        // =========================================
        Map<Boolean, List<Integer>> partitioned = list.stream()
                .collect(Collectors.partitioningBy(n -> Collections.frequency(list, n) > 1));

        System.out.println("Unique elements: " + partitioned.get(false));    // [1,2,5,6,7,8]
        System.out.println("Duplicate elements (with repeats): " + partitioned.get(true)); // [3,3,4,4,9,9]

    }
}
