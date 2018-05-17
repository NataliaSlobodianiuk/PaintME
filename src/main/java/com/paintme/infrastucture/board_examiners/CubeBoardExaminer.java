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
            twos.putAll(this.sideExaminer.findCombinationOfTwo(
                    cells.substring(i * cellsPerSide, (i + 1) * cellsPerSide)));
        }

        return twos;
    }

    @Override
    public Map<Integer[], Character> findCombinationOfThree(String cells) {
        Map<Integer[], Character> threes = new HashMap<>();

        int cellsPerSide = cells.length() / this.sides;
        for (int i = 0; i < this.sides; i++) {
            threes.putAll(this.sideExaminer.findCombinationOfThree(
                    cells.substring(i * cellsPerSide, (i + 1) * cellsPerSide)));
        }

        return threes;
    }

    @Override
    public Map<Integer[], Character> findCombinationOfFour(String cells) {
        Map<Integer[], Character> fours = new HashMap<>();

        int cellsPerSide = cells.length() / this.sides;
        for (int i = 0; i < this.sides; i++) {
            fours.putAll(this.sideExaminer.findCombinationOfFour(
                    cells.substring(i * cellsPerSide, (i + 1) * cellsPerSide)));
        }

        return fours;
    }
}
