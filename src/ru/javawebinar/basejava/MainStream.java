package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] values = {7, 5, 4, 3, 2, 1, 8, 9, 5, 3, 2, 6, 5, 3};
        List<Integer> integers = Arrays.stream(values).boxed().collect(Collectors.toList());
        System.out.println(minValue(values));
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((left, right) -> left * 10 + right)
                .orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {

        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        boolean isEven = sum % 2 == 0;
        System.out.println(sum);

        return integers.stream()
                .filter(integer -> (integer % 2 != 0) == isEven)
                .collect(Collectors.toList());
    }

}
