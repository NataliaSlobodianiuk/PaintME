package com.paintme.infrastucture.board_examiners;

import java.util.HashMap;
import java.util.Map;

public class Square5BoardExaminer extends SquareBoardExaminer {
    public Square5BoardExaminer(){
        this.size = 5;
    }

    @Override
    public char findWinningSymbol(String cells) {
        char winningSymbol = '-';

        Map<Integer[], Character> combinationsOfFourDictionary =
                this.findCombinationOfFour(cells);
        if (!combinationsOfFourDictionary.isEmpty()) {
            winningSymbol = (char) combinationsOfFourDictionary
                    .values().toArray()[0];
        }

        return winningSymbol;
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoColumns(String cells) {
        return new HashMap<>();
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoDiagonals(String cells) {
        return new HashMap<>();
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfTwoRows(String cells) {
        return new HashMap<>();
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeColumns(String cells) {
        return new HashMap<>();
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeDiagonals(String cells) {
        return new HashMap<>();
    }

    @Override
    protected Map<Integer[], Character> findCombinationOfThreeRows(String cells) {
        return new HashMap<>();
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