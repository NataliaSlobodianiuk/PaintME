package com.paintme.infrastucture.board_examiners;

import java.util.HashMap;
import java.util.Map;

public abstract class SquareBoardExaminer extends BoardExaminerBase {
    protected int size;
    protected int winAmount;

    @Override
    public Map<Integer[], Character> findCombinationOfTwo(String cells) {
        Map<Integer[], Character> twos = new HashMap<>();

        twos.putAll(this.findCombinationOfTwoRows(cells));
        twos.putAll(this.findCombinationOfTwoColumns(cells));
        twos.putAll(this.findCombinationOfTwoDiagonals(cells));

        return twos;
    }

    @Override
    public Map<Integer[], Character> findCombinationOfThree(String cells) {
        Map<Integer[], Character> threes = new HashMap<>();

        threes.putAll(this.findCombinationOfThreeRows(cells));
        threes.putAll(this.findCombinationOfThreeColumns(cells));
        threes.putAll(this.findCombinationOfThreeDiagonals(cells));

        return threes;
    }

    @Override
    public Map<Integer[], Character> findCombinationOfFour(String cells) {
        Map<Integer[], Character> fours = new HashMap<>();

        fours.putAll(this.findCombinationOfFourRows(cells));
        fours.putAll(this.findCombinationOfFourColumns(cells));
        fours.putAll(this.findCombinationOfFourDiagonals(cells));

        return fours;
    }

    protected abstract Map<Integer[], Character> findCombinationOfTwoColumns(String cells);
    protected abstract Map<Integer[], Character> findCombinationOfTwoDiagonals(String cells);
    protected abstract Map<Integer[], Character> findCombinationOfTwoRows(String cells);

    protected abstract Map<Integer[], Character> findCombinationOfThreeColumns(String cells);
    protected abstract Map<Integer[], Character> findCombinationOfThreeDiagonals(String cells);
    protected abstract Map<Integer[], Character> findCombinationOfThreeRows(String cells);

    protected abstract Map<Integer[], Character> findCombinationOfFourColumns(String cells);
    protected abstract Map<Integer[], Character> findCombinationOfFourDiagonals(String cells);
    protected abstract Map<Integer[], Character> findCombinationOfFourRows(String cells);
}
