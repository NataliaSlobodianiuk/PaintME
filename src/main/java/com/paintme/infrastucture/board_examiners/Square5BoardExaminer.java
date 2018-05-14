package com.paintme.infrastucture.board_examiners;

import java.util.HashMap;
import java.util.Map;

public class Square5BoardExaminer extends SquareBoardExaminer {
    public Square5BoardExaminer(){
        this.size = 5;
        this.winAmount = 4;
    }

    @Override
    public char findWinningSymbol(String cells) {
        char winningSymbol = '-';

        Map<Integer[], Character> combinationsOfFourDictionary = this.findCombinationOfFour(cells);
        if (!combinationsOfFourDictionary.isEmpty()) {
            winningSymbol = (char) combinationsOfFourDictionary.values().toArray()[0];
        }

        return winningSymbol;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoColumns(String cells) {
        Map<Integer[], Character> twosColumns = new HashMap<>();

        for (int i = 0; i < this.size * (this.size - 1); i++) {
            if (cells.charAt(i) != '-' && cells.charAt(i) == cells.charAt(i + this.size)) {
                if ((i - this.size * 2 >= 0 && cells.charAt(i - this.size * 2) == '-')
                        && (i - this.size >= 0 && cells.charAt(i - this.size) == '-')) {
                    twosColumns.put(new Integer[]{ i, i + this.size, i - this.size * 2, i - this.size }, cells.charAt(i));
                }
                if ((i - this.size >= 0 && cells.charAt(i - this.size) == '-')
                        && (i + this.size * 2 < cells.length() && cells.charAt(i + this.size * 2) == '-')) {
                    twosColumns.put(new Integer[]{ i, i + this.size, i - this.size, i + this.size * 2 }, cells.charAt(i));
                }
                if ((i + this.size * 2 < cells.length() && cells.charAt(i + this.size * 2) == '-')
                        && (i + this.size * 3 < cells.length() && cells.charAt(i + this.size * 3) == '-')) {
                    twosColumns.put(new Integer[]{ i, i + this.size, i + this.size * 3, i + this.size * 2 }, cells.charAt(i));
                }
            }
        }

        return twosColumns;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoDiagonals(String cells) {
        Map<Integer[], Character> twosDiagonals = new HashMap<>();

        //left-up corner (right)
        for (int i = 0; i <= this.size - this.winAmount; i++) {
            for (int j = 0; j < this.size - i - 1; j++) {
                if (cells.charAt(i + j * (this.size + 1)) != '-'
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 1) * (this.size + 1))) {
                    if ((j > 1 && cells.charAt(i + (j - 2) * (this.size + 1)) == '-')
                            && ((j > 0 && cells.charAt(i + (j - 1) * (this.size + 1)) == '-'))) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j - 2) * (this.size + 1),
                                        i + (j - 1) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                    if ((j > 0 && cells.charAt(i + (j - 1) * (this.size + 1)) == '-')
                            && (j < this.size - i - 2 && cells.charAt(i + (j + 2) * (this.size + 1)) == '-')) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j - 1) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                    if ((j < this.size - i - 2 && cells.charAt(i + (j + 2) * (this.size + 1)) == '-')
                            && (j < this.size - i - 3 && cells.charAt(i + (j + 3) * (this.size + 1)) == '-')) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j + 3) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                }
            }
        }

        //left-up corner (2nd line) (down)
        for (int i = this.size; i < this.size * (this.size - this.winAmount + 1); i += this.size) {
            for (int j = 0; j < this.size - (i / this.size) - 1; j++) {
                if (cells.charAt(i + j * (this.size + 1)) != '-'
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 1) * (this.size + 1))) {
                    if ((j > 1 && cells.charAt(i + (j - 2) * (this.size + 1)) == '-')
                            && ((j > 0 && cells.charAt(i + (j - 1) * (this.size + 1)) == '-'))) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j - 2) * (this.size + 1),
                                        i + (j - 1) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                    if ((j > 0 && cells.charAt(i + (j - 1) * (this.size + 1)) == '-')
                            && (j < this.size - (i / this.size) - 2 && cells.charAt(i + (j + 2) * (this.size + 1)) == '-')) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j - 1) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                    if ((j < this.size - (i / this.size) - 2 && cells.charAt(i + (j + 2) * (this.size + 1)) == '-')
                            && (j < this.size - (i / this.size) - 3 && cells.charAt(i + (j + 3) * (this.size + 1)) == '-')) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j + 3) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                }
            }
        }

        //right-up corner (left)
        for (int i = this.size - 1; i >= this.winAmount - 1; i--) {
            for (int j = 0; j < i; j++) {
                if (cells.charAt(i + j * (this.size - 1)) != '-'
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 1) * (this.size - 1))) {
                    if ((j > 1 && cells.charAt(i + (j - 2) * (this.size - 1)) == '-')
                            && ((j > 0 && cells.charAt(i + (j - 1) * (this.size - 1)) == '-'))) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j - 2) * (this.size - 1),
                                        i + (j - 1) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                    if ((j > 0 && cells.charAt(i + (j - 1) * (this.size - 1)) == '-')
                            && (j < i - 1 && cells.charAt(i + (j + 2) * (this.size - 1)) == '-')) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j - 1) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                    if ((j < i - 1 && cells.charAt(i + (j + 2) * (this.size - 1)) == '-')
                            && (j < i - 2 && cells.charAt(i + (j + 3) * (this.size - 1)) == '-')) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j + 3) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                }
            }
        }

        //right-up corner (2nd line) (down)
        for (int i = 2 * this.size - 1; i < this.size * (this.size - this.winAmount + 1); i += this.size) {
            for (int j = 0; j < this.size - Math.floorDiv(i, this.size) - 1; j++) {
                if (cells.charAt(i + j * (this.size - 1)) != '-'
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 1) * (this.size - 1))) {

                    if ((j > 1 && cells.charAt(i + (j - 2) * (this.size - 1)) == '-')
                            && ((j > 0 && cells.charAt(i + (j - 1) * (this.size - 1)) == '-'))) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j - 2) * (this.size - 1),
                                        i + (j - 1) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                    if ((j > 0 && cells.charAt(i + (j - 1) * (this.size - 1)) == '-')
                            && (j < this.size - Math.floorDiv(i, this.size) - 2 && cells.charAt(i + (j + 2) * (this.size - 1)) == '-')) {
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j - 1) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                    if ((j < this.size - Math.floorDiv(i, this.size) - 2 && cells.charAt(i + (j + 2) * (this.size - 1)) == '-')
                            && (j < this.size - Math.floorDiv(i, this.size) - 3 && cells.charAt(i + (j + 3) * (this.size - 1)) == '-'))
                        twosDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j + 3) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
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
                    if ((i + j - 2 >= i * this.size && cells.charAt(i + j - 2) == '-')
                            && (i + j - 1 >= i * this.size && cells.charAt(i + j - 1) == '-')) {
                        twosRows.put(new Integer[]{ i + j, i + j + 1, i + j - 2, i + j - 1 }, cells.charAt(i));
                    }
                    if ((i + j - 1 >= i * this.size && cells.charAt(i + j - 1) == '-')
                            && (i + j + 2 < i * this.size + this.size && cells.charAt(i + j + 2) == '-')) {
                        twosRows.put(new Integer[]{ i + j, i + j + 1, i + j - 1, i + j + 2 }, cells.charAt(i));
                    }
                    if ((i + j + 2 < i * this.size + this.size && cells.charAt(i + j + 2) == '-')
                            && (i + j + 3 < i * this.size + this.size && cells.charAt(i + j + 3) == '-')) {
                        twosRows.put(new Integer[]{ i + j, i + j + 1, i + j + 3, i + j + 2 }, cells.charAt(i));
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
                if (i - this.size >= 0 && cells.charAt(i - this.size) == '-') {
                    threesColumns.put(new Integer[]{ i, i + this.size, i + this.size * 2, i - this.size }, cells.charAt(i));
                }
                if (i + this.size * 3 < cells.length() && cells.charAt(i + this.size * 3) == '-') {
                    threesColumns.put(new Integer[]{ i, i + this.size, i + this.size * 2, i + this.size * 3 }, cells.charAt(i));
                }
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
                    if (j > 0 && cells.charAt(i + (j - 1) * (this.size + 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1),
                                        i + (j - 1) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                    if (j < this.size - i - 3 && cells.charAt(i + (j + 3) * (this.size + 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[]{
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1),
                                        i + (j + 3) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                }
            }
        }

        //left-up corner (2nd line) (down)
        for (int i = this.size; i < this.size * (this.size - this.winAmount + 1) ; i += this.size)
        {
            for (int j = 0; j < this.size - (i / this.size) - 2; j++) {
                if (cells.charAt(i + j * (this.size + 1)) != '-'
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 1) * (this.size + 1))
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 2) * (this.size + 1))) {
                    if (j > 0 && cells.charAt(i + (j - 1) * (this.size + 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1),
                                        i + (j - 1) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
                    if (j < this.size - (i / this.size) - 3 && cells.charAt(i + (j + 3) * (this.size + 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size + 1),
                                        i + (j + 1) * (this.size + 1),
                                        i + (j + 2) * (this.size + 1),
                                        i + (j + 3) * (this.size + 1)},
                                cells.charAt(i + j * (this.size + 1)));
                    }
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
                    if (j > 0 && cells.charAt(i + (j - 1) * (this.size - 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1),
                                        i + (j - 1) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                    if (j < i - 2 && cells.charAt(i + (j + 3) * (this.size - 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1),
                                        i + (j + 3) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                }
            }
        }

        //right-up corner (2nd line) (down)
        for (int i = 2 * this.size - 1; i < this.size * (this.size - this.winAmount + 1) ; i += this.size)
        {
            for (int j = 0; j < this.size - Math.floorDiv(i, this.size) - 2; j++) {
                if (cells.charAt(i + j * (this.size - 1)) != '-'
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 1) * (this.size - 1))
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 2) * (this.size - 1))) {
                    if (j > 0 && cells.charAt(i + (j - 1) * (this.size - 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1),
                                        i + (j - 1) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
                    if (j < this.size - Math.floorDiv(i, this.size) - 3 && cells.charAt(i + (j + 3) * (this.size - 1)) == '-') {
                        threesDiagonals.put(
                                new Integer[] {
                                        i + j * (this.size - 1),
                                        i + (j + 1) * (this.size - 1),
                                        i + (j + 2) * (this.size - 1),
                                        i + (j + 3) * (this.size - 1)},
                                cells.charAt(i + j * (this.size - 1)));
                    }
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
                    if (i + j - 1 >= i * this.size && cells.charAt(i + j - 1) == '-') {
                        threesRows.put(new Integer[]{ i + j, i + j + 1, i + j + 2, i + j - 1}, cells.charAt(i));
                    } else if (i + j + 3 < i * this.size + this.size && cells.charAt(i + j + 3) == '-') {
                        threesRows.put(new Integer[]{ i + j, i + j + 1, i + j + 2, i + j + 3}, cells.charAt(i));
                    }
                }
            }
        }

        return threesRows;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfFourColumns(String cells) {
        Map<Integer[], Character> foursColumns = new HashMap<>();

        for (int i = 0; i < this.size * (this.size - 3); i++) {
            if (cells.charAt(i) != '-'
                    && cells.charAt(i) == cells.charAt(i + this.size)
                    && cells.charAt(i) == cells.charAt(i + this.size * 2)
                    && cells.charAt(i) == cells.charAt(i + this.size * 3)) {
                foursColumns.put(new Integer[]{ i, i + this.size, i + this.size * 2, i + this.size * 3 }, cells.charAt(i));
            }
        }

        return foursColumns;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfFourDiagonals(String cells) {
        Map<Integer[], Character> foursDiagonals = new HashMap<>();

        //left-up corner (right)
        for (int i = 0; i <= this.size - this.winAmount; i++) {
            for (int j = 0; j < this.size - i - 3; j++) {
                if (cells.charAt(i + j * (this.size + 1)) != '-'
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 1) * (this.size + 1))
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 2) * (this.size + 1))
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 3) * (this.size + 1))) {
                    foursDiagonals.put(
                            new Integer[]{
                                    i + j * (this.size + 1),
                                    i + (j + 1) * (this.size + 1),
                                    i + (j + 2) * (this.size + 1),
                                    i + (j + 3) * (this.size + 1)},
                            cells.charAt(i + j * (this.size + 1)));
                }
            }
        }

        //left-up corner (2nd line) (down)
        for (int i = this.size; i < this.size * (this.size - this.winAmount + 1) ; i += this.size) {
            for (int j = 0; j < this.size - (i / this.size) - 3; j++) {
                if (cells.charAt(i + j * (this.size + 1)) != '-'
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 1) * (this.size + 1))
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 2) * (this.size + 1))
                        && cells.charAt(i + j * (this.size + 1)) == cells.charAt(i + (j + 3) * (this.size + 1))) {
                    foursDiagonals.put(
                            new Integer[]{
                                    i + j * (this.size + 1),
                                    i + (j + 1) * (this.size + 1),
                                    i + (j + 2) * (this.size + 1),
                                    i + (j + 3) * (this.size + 1)},
                            cells.charAt(i + j * (this.size + 1)));
                }
            }
        }

        //right-up corner (left)
        for (int i = this.size - 1; i >= this.winAmount - 1; i--)
        {
            for (int j = 0; j < i - 2; j++) {
                if (cells.charAt(i + j * (this.size - 1)) != '-'
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 1) * (this.size - 1))
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 2) * (this.size - 1))
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 3) * (this.size - 1))) {
                    foursDiagonals.put(
                            new Integer[]{
                                    i + j * (this.size - 1),
                                    i + (j + 1) * (this.size - 1),
                                    i + (j + 2) * (this.size - 1),
                                    i + (j + 3) * (this.size - 1)},
                            cells.charAt(i + j * (this.size - 1)));
                }
            }
        }

        //right-up corner (2nd line) (down)
        for (int i = 2 * this.size - 1; i < this.size * (this.size - this.winAmount + 1) ; i += this.size)
        {
            for (int j = 0; j < this.size - Math.floorDiv(i, this.size) - 3; j++) {
                if (cells.charAt(i + j * (this.size - 1)) != '-'
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 1) * (this.size - 1))
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 2) * (this.size - 1))
                        && cells.charAt(i + j * (this.size - 1)) == cells.charAt(i + (j + 3) * (this.size - 1))) {
                    foursDiagonals.put(
                            new Integer[]{
                                    i + j * (this.size - 1),
                                    i + (j + 1) * (this.size - 1),
                                    i + (j + 2) * (this.size - 1),
                                    i + (j + 3) * (this.size - 1)},
                            cells.charAt(i + j * (this.size - 1)));
                }
            }
        }

        return foursDiagonals;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfFourRows(String cells) {
        Map<Integer[], Character> foursRows = new HashMap<>();

        for (int i = 0; i <= this.size * (this.size - 1); i += this.size) {
            for (int j = 0; j < this.size - 3; j++) {
                if (cells.charAt(i + j) != '-'
                        && cells.charAt(i + j) == cells.charAt(i + j + 1)
                        && cells.charAt(i + j) == cells.charAt(i + j + 2)
                        && cells.charAt(i + j) == cells.charAt(i + j + 3)) {
                    foursRows.put(new Integer[]{ i + j, i + j + 1, i + j + 2, i + j + 3 }, cells.charAt(i));
                }
            }
        }

        return foursRows;
    }
}