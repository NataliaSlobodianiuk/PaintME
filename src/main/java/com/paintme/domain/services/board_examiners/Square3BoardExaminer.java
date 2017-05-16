package com.paintme.domain.services.board_examiners;

import java.util.*;

public class Square3BoardExaminer extends SquareBoardExaminer {
    @Override
    public char findWinningSymbol(String cells) {
        char winningSymbol = '\u0000';

        Map<Integer[], Character> combinationsOfTreeDictionary =
                this.findCombinationOfThree(cells);
        if (!combinationsOfTreeDictionary.isEmpty()) {
            winningSymbol = (char) combinationsOfTreeDictionary
                    .values().toArray()[0];
        }

        return winningSymbol;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoColumns(String cells) {
        Map<Integer[], Character> twosColumns = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            if (cells.charAt(i) != '-'
                    && cells.charAt(i) == cells.charAt(i + 3)
                    && cells.charAt(i + 6) == '-') {
                twosColumns.put(new Integer[] {i, i + 3}, cells.charAt(i));
            }
            else if (cells.charAt(i + 3) != '-'
                    && cells.charAt(i + 3) == cells.charAt(i + 6)
                    && cells.charAt(i) == '-') {
                twosColumns.put(new Integer[] {i + 3, i + 6}, cells.charAt(i + 3));
            }
            else if (cells.charAt(i) != '-'
                    && cells.charAt(i) == cells.charAt(i + 6)
                    && cells.charAt(i + 3) == '-') {
                twosColumns.put(new Integer[]{i, i + 6}, cells.charAt(i));
            }
        }

        return twosColumns;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoDiagonals(String cells) {
        Map<Integer[], Character> twosDiagonals = new HashMap<>();

        Character symbolToCheck = cells.charAt(4);
        if (symbolToCheck != '-') {
            for (int i = 0; i < 9; i = i + 2) {
                if (i == 4) {
                    continue;
                }
                if (symbolToCheck == cells.charAt(i)
                        && cells.charAt(9 - 1 - i) == '-') {
                    Integer[] two = new Integer[]{i, 4};
                    Arrays.sort(two);
                    twosDiagonals.put(two, symbolToCheck);
                }
            }
        }
        else {
            symbolToCheck = cells.charAt(0);
            if (symbolToCheck == cells.charAt(8)) {
                twosDiagonals.put(new Integer[] {0, 8}, symbolToCheck);
            }
            symbolToCheck = cells.charAt(2);
            if (symbolToCheck == cells.charAt(6)) {
                twosDiagonals.put(new Integer[] {2, 6}, symbolToCheck);
            }
        }

        return twosDiagonals;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoRows(String cells) {
        Map<Integer[], Character> twosRows = new HashMap<>();

        for (int i = 0; i < 9; i = i + 3) {
            if (cells.charAt(i) != '-'
                    && cells.charAt(i) == cells.charAt(i + 1)
                    && cells.charAt(i + 2) == '-') {
                twosRows.put(new Integer[] {i, i + 1}, cells.charAt(i));
            }
            else if (cells.charAt(i + 1) != '-'
                    && cells.charAt(i + 1) == cells.charAt(i + 2)
                    && cells.charAt(i) == '-') {
                twosRows.put(new Integer[] {i + 1, i + 2}, cells.charAt(i + 1));
            }
            else if (cells.charAt(i) != '-'
                    && cells.charAt(i) == cells.charAt(i + 2)
                    && cells.charAt(i + 1) == '-') {
                twosRows.put(new Integer[] {i, i + 2}, cells.charAt(i));
            }
        }

        return twosRows;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeColumns(String cells) {
        Map<Integer[], Character> threesColumns = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            Character symbolToCheck = cells.charAt(i);
            if (symbolToCheck != '-'
                    && symbolToCheck == cells.charAt(i + 3)
                    && symbolToCheck == cells.charAt(i + 6)) {
                threesColumns.put(new Integer[]{i, i + 3, i + 6}, symbolToCheck);
            }
        }

        return threesColumns;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeDiagonals(String cells) {
        Map<Integer[], Character> threesDiagonals = new HashMap<>();

        Character symbolToCheck = cells.charAt(4);
        if (symbolToCheck != '-') {
            if (symbolToCheck == cells.charAt(0)
                    && symbolToCheck == cells.charAt(8)) {
                threesDiagonals.put(new Integer[] {0, 4, 8}, symbolToCheck);
            }
            else if (symbolToCheck == cells.charAt(2)
                    && symbolToCheck == cells.charAt(6)) {
                threesDiagonals.put(new Integer[] {0, 2, 6}, symbolToCheck);
            }
        }

        return threesDiagonals;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeRows(String cells) {
        Map<Integer[], Character> threesRows = new HashMap<>();

        for (int i = 0; i < 9; i = i + 3) {
            Character symbolToCheck = cells.charAt(i);
            if (symbolToCheck != '-'
                    && symbolToCheck == cells.charAt(i + 1)
                    && symbolToCheck == cells.charAt(i + 2)) {
                threesRows.put(new Integer[]{i, i + 1, i + 2}, symbolToCheck);
            }
        }

        return threesRows;
    }
}