package com.paintme.infrastucture;

import java.util.*;

public class AlgorithmsDictionaries {
    public static final Map<Integer[], Integer> Square3ThirdInARow;
    public static final List<Integer[]> Square3BAnglesWinningPositions;
    public static final List<Integer[]> Square3BTriangleWinningPositions;

    static {
        Square3ThirdInARow = Collections.unmodifiableMap(formSquare3ThirdInARow());
        Square3BAnglesWinningPositions = Collections.unmodifiableList(formSquare3BAnglesWinningPositions());
        Square3BTriangleWinningPositions = Collections.unmodifiableList(formSquare3BTriangleWinningPositions());
    }

    private static Map<Integer[], Integer> formSquare3ThirdInARow() {
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

    private static List<Integer[]> formSquare3BAnglesWinningPositions() {
        List<Integer[]> triangleWinningPositions = new ArrayList<>();

        triangleWinningPositions.add(new Integer[] {0, 2});
        triangleWinningPositions.add(new Integer[] {0, 6});
        triangleWinningPositions.add(new Integer[] {6, 8});
        triangleWinningPositions.add(new Integer[] {2, 8});

        return triangleWinningPositions;
    }

    private static List<Integer[]> formSquare3BTriangleWinningPositions() {
        List<Integer[]> triangleWinningPositions = new ArrayList<>();

        triangleWinningPositions.add(new Integer[] {0, 1});
        triangleWinningPositions.add(new Integer[] {0, 3});
        triangleWinningPositions.add(new Integer[] {2, 1});
        triangleWinningPositions.add(new Integer[] {2, 5});
        triangleWinningPositions.add(new Integer[] {6, 3});
        triangleWinningPositions.add(new Integer[] {6, 7});
        triangleWinningPositions.add(new Integer[] {8, 5});
        triangleWinningPositions.add(new Integer[] {8, 7});

        return triangleWinningPositions;
    }
}
