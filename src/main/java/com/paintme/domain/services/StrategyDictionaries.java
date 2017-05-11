package com.paintme.domain.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StrategyDictionaries {
    public static final Map<Integer[], Integer> Square3BlockingTwos;
    static {
        Square3BlockingTwos = Collections.unmodifiableMap(formSquare3BlockingTwos());
    }

    private static Map<Integer[], Integer> formSquare3BlockingTwos() {
        Map<Integer[], Integer> blockingTwos = new LinkedHashMap<>();

        for (int i = 0; i < 9; i = i + 3) {
            blockingTwos.put(new Integer[] {i, i + 1}, i + 2);
            blockingTwos.put(new Integer[] {i, i + 2}, i + 1);
            blockingTwos.put(new Integer[] {i + 1, i + 2}, i);
        }

        for (int i = 0; i < 3; i++) {
            blockingTwos.put(new Integer[] {i, i + 3}, i + 6);
            blockingTwos.put(new Integer[] {i, i + 6}, i + 3);
            blockingTwos.put(new Integer[] {i + 3, i + 6}, i);
        }

        for (int i = 0; i < 9; i = i + 2) {
            Integer[] two = new Integer[] {i, 4};
            Arrays.sort(two);
            blockingTwos.put(two, 9 - 1 - i);
        }

        return blockingTwos;
    }
}
