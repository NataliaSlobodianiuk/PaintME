package com.paintme.infrastucture.board_examiners;

import java.util.*;

public class Square3BoardExaminer extends SquareBoardExaminer {
    public Square3BoardExaminer(){
        this.size = 3;
        this.winAmount = 3;
    }

    @Override
    public char findWinningSymbol(String cells) {
        char winningSymbol = '-';

        Map<Integer[], Character> combinationsOfTreeDictionary = this.findCombinationOfThree(cells);
        if (!combinationsOfTreeDictionary.isEmpty()) {
            winningSymbol = (char) combinationsOfTreeDictionary.values().toArray()[0];
        }

        return winningSymbol;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoColumns(String cells) {
        Map<Integer[], Character> twosColumns = new HashMap<>();

        for (int i = 0; i < this.size * (this.size - 1); i++) {
            if (cells.charAt(i) != '-' && cells.charAt(i) == cells.charAt(i + this.size)) {
                if (i - this.size >= 0 && cells.charAt(i - this.size) == '-') {
                    twosColumns.put(new Integer[]{ i, i + this.size, i - this.size }, cells.charAt(i));
                }
                if(i + this.size * 2 < cells.length() && cells.charAt(i + this.size * 2) == '-') {
                    twosColumns.put(new Integer[]{ i, i + this.size, i + this.size * 2 }, cells.charAt(i));
                }
            }
        }

        return twosColumns;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoDiagonals(String cells) {
        Map<Integer[], Character> twosDiagonals = new HashMap<>();

        //left-up corner (right)
        for (int i = 0; i <= this.size - this.winAmount; i++)
        {
            for (int j = 0; j < this.size - i - 1; j++) {
                if (cells.charAt(i + j * (this.size + 1)) != '-'
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 1) * (this.size + 1))) {
                    if (j > 0 && cells.charAt(i + (j - 1) * (this.size + 1)) == '-') {
                        twosDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j - 1) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                    if (j < this.size - i - 2 && cells.charAt(i + (j + 2) * (this.size + 1)) == '-') {
                        twosDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                }
            }
        }

        //right-up corner (left)
        for (int i = this.size - 1; i >= this.winAmount - 1; i--)
        {
            for (int j = 0; j < i; j++) {
                if (cells.charAt(i + j * (this.size - 1)) != '-'
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 1) * (this.size - 1))) {
                    if (j > 0 && cells.charAt(i + (j - 1) * (this.size - 1)) == '-') {
                        twosDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j - 1) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                    if (j < i - 1 && cells.charAt(i + (j + 2) * (this.size - 1)) == '-') {
                        twosDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                }
            }
        }

        return twosDiagonals;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoRows(String cells) {
        Map<Integer[], Character> twosRows = new HashMap<>();

        for (int i = 0; i <= this.size * (this.size - 1); i += this.size) {
            for (int j = 0; j < this.size - 1; j++) {
                if (cells.charAt(i + j) != '-' && cells.charAt(i + j) == cells.charAt(i + j + 1)) {
                    if (i + j - 1 >= i * this.size && cells.charAt(i + j - 1) == '-') {
                        twosRows.put(new Integer[]{ i + j, i + j + 1, i + j - 1 }, cells.charAt(i));
                    }
                    if (i + j + 2 < i * this.size + this.size && cells.charAt(i + j + 2) == '-') {
                        twosRows.put(new Integer[]{ i + j, i + j + 1, i + j + 2 }, cells.charAt(i));
                    }
                }
            }
        }

        return twosRows;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeColumns(String cells) {
        Map<Integer[], Character> threesColumns = new HashMap<>();

        for (int i = 0; i < this.size * (this.size - 2); i++) {
            if (cells.charAt(i) != '-'
                    && cells.charAt(i) == cells.charAt(i + this.size)
                    && cells.charAt(i) == cells.charAt(i + this.size * 2)) {
                threesColumns.put(new Integer[]{i, i + this.size, i + this.size * 2}, cells.charAt(i));
            }
        }

        return threesColumns;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeDiagonals(String cells) {
        Map<Integer[], Character> threesDiagonals = new HashMap<>();

        //left-up corner (right)
        for (int i = 0; i <= this.size - this.winAmount; i++)
        {
            for (int j = 0; j < this.size - i - 2; j++) {
                if (cells.charAt(i + j * (this.size + 1)) != '-'
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 1) * (this.size + 1))
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 2) * (this.size + 1))) {
                    threesDiagonals.put(
                            new Integer[]{i + j * (this.size + 1), i + (j + 1) * (this.size + 1), i + (j + 2) * (this.size + 1)},
                            cells.charAt(i + j * (this.size + 1)));
                }
            }
        }

        //right-up corner (left)
        for (int i = this.size - 1; i >= this.winAmount - 1; i--)
        {
            for (int j = 0; j < i - 1; j++) {
                if (cells.charAt(i + j * (this.size - 1)) != '-'
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 1) * (this.size - 1))
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 2) * (this.size - 1))) {
                    threesDiagonals.put(
                            new Integer[]{i + j * (this.size - 1), i + (j + 1) * (this.size - 1), i + (j + 2) * (this.size - 1)},
                            cells.charAt(i + j * (this.size - 1)));

                }
            }
        }

        return threesDiagonals;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeRows(String cells) {
        Map<Integer[], Character> threesRows = new HashMap<>();

        for (int i = 0; i <= this.size * (this.size - 1); i += this.size) {
            for (int j = 0; j < this.size - 2; j++) {
                if (cells.charAt(i + j) != '-'
                        && cells.charAt(i + j) == cells.charAt(i + j + 1)
                        && cells.charAt(i + j) == cells.charAt(i + j + 2)) {
                    threesRows.put(new Integer[]{i + j, i + j + 1, i + j + 2}, cells.charAt(i));
                }
            }
        }

        return threesRows;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfFourColumns(String cells) {
        return new HashMap<>();
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfFourDiagonals(String cells) {
        return new HashMap<>();
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfFourRows(String cells) {
        return new HashMap<>();
    }
}