package com.paintme.infrastucture.board_examiners;

import java.util.HashMap;
import java.util.Map;

public abstract class CubeBoardExaminer extends BoardExaminerBase {
    protected int sides = 6;
    protected SquareBoardExaminer sideExaminer = null;

    @Override
    public char findWinningSymbol(String cells) {
        Map<Character, Integer> winningSymbols = new HashMap<>();

        int cellsPerSide = cells.length() / this.sides;
        for (int i = 0; i < this.sides; i++) {
            char winningSymbol = this.sideExaminer.findWinningSymbol(
                    cells.substring(i * cellsPerSide, (i + 1) * cellsPerSide));

            if (winningSymbol == '-') {
                continue;
            } else if (winningSymbols.containsKey(winningSymbol)) {
                winningSymbols.replace(winningSymbol, winningSymbols.get(winningSymbol) + 1);
            } else {
                winningSymbols.put(winningSymbol, 1);
            }
        }

        for (Map.Entry<Character, Integer> pair : winningSymbols.entrySet()) {
            if (pair.getValue() > this.sides / 2) {
                return pair.getKey();
            }
        }

        return  '-';
    }

    @Override
    public Map<Integer[], Character> findCombinationOfTwo(String cells) {
        Map<Integer[], Character> twos = new HashMap<>();

        int cellsPerSide = cells.length() / this.sides;
        for (int i = 0; i < this.sides; i++) {
            for (Map.Entry<Integer[], Character> pair : this.sideExaminer.findCombinationOfTwo(
                    cells.substring(i * cellsPerSide, (i + 1) * cellsPerSide)).entrySet()) {
                Integer[] keyToAdd = new Integer[pair.getKey().length];
                for (int j = 0; j < pair.getKey().length; j++) {
                    keyToAdd[j] = pair.getKey()[j] + i * cellsPerSide;
                }
                twos.put(keyToAdd, pair.getValue());
            }
        }

        return twos;
    }

    @Override
    public Map<Integer[], Character> findCombinationOfThree(String cells) {
        Map<Integer[], Character> threes = new HashMap<>();

        int cellsPerSide = cells.length() / this.sides;
        for (int i = 0; i < this.sides; i++) {
            for (Map.Entry<Integer[], Character> pair : this.sideExaminer.findCombinationOfThree(
                    cells.substring(i * cellsPerSide, (i + 1) * cellsPerSide)).entrySet()) {
                Integer[] keyToAdd = new Integer[pair.getKey().length];
                for (int j = 0; j < pair.getKey().length; j++) {
                    keyToAdd[j] = pair.getKey()[j] + i * cellsPerSide;
                }
                threes.put(keyToAdd, pair.getValue());
            }
        }

        return threes;
    }

    @Override
    public Map<Integer[], Character> findCombinationOfFour(String cells) {
        Map<Integer[], Character> fours = new HashMap<>();

        int cellsPerSide = cells.length() / this.sides;
        for (int i = 0; i < this.sides; i++) {
            for (Map.Entry<Integer[], Character> pair : this.sideExaminer.findCombinationOfFour(
                    cells.substring(i * cellsPerSide, (i + 1) * cellsPerSide)).entrySet()) {
                Integer[] keyToAdd = new Integer[pair.getKey().length];
                for (int j = 0; j < pair.getKey().length; j++) {
                    keyToAdd[j] = pair.getKey()[j] + i * cellsPerSide;
                }
                fours.put(keyToAdd, pair.getValue());
            }
        }

        return fours;
    }
}
