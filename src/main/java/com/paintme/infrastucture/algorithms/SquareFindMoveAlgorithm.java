package com.paintme.infrastucture.algorithms;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Field;

import java.util.Map;

public abstract class SquareFindMoveAlgorithm extends FindMoveAlgorithm {
    protected int winAmount;

    protected int findBlockMove(char color, BoardType boardType, Field field, String cells) throws PaintMEException {
        if (this.examiner == null) {
            try {
                this.setExaminer(boardType, field);
            } catch (IllegalArgumentException exception) {
                throw new PaintMEException(
                        "Couldn't create a board examiner." +
                                "Following exception occurred:" +
                                exception.getMessage(),
                        exception);
            }
        }

        switch (winAmount) {
            case 3:
                return this.findBlockMove3(color, cells);
            case 4:
                return this.findBlockMove4(color, cells);
        }

        return -1;
    }

    private int findBlockMove3(char color, String cells) throws PaintMEException {
        return this.findBlockMove(color, examiner.findCombinationOfTwo(cells));
    }

    private int findBlockMove4(char color, String cells) throws PaintMEException {
        return this.findBlockMove(color, examiner.findCombinationOfThree(cells));
    }

    private int findBlockMove(char color, Map<Integer[], Character> combinations) throws PaintMEException {
        if (!combinations.isEmpty()) {
            for (Map.Entry<Integer[], Character> combination : combinations.entrySet()) {
                if (combination.getValue() != color) {
                    return combination.getKey()[combination.getKey().length - 1];
                }
            }
        }
        return -1;
    }

    protected int findWinningMove(char color, BoardType boardType, Field field, String cells) throws PaintMEException {
        int cellToMarkNum = -1;

        if (this.examiner == null) {
            try {
                this.setExaminer(boardType, field);
            } catch (IllegalArgumentException exception) {
                throw new PaintMEException(
                        "Couldn't create a board examiner." +
                                "Following exception occurred:" +
                                exception.getMessage(),
                        exception);
            }
        }

        switch (winAmount) {
            case 3:
                return this.findWinningMove3(color, cells);
            case 4:
                return this.findWinningMove4(color, cells);
        }

        return -1;
    }

    private int findWinningMove3(char color, String cells) throws PaintMEException {
        return this.findWinningMove(color, examiner.findCombinationOfTwo(cells));
    }

    private int findWinningMove4(char color, String cells) throws PaintMEException {
        return this.findWinningMove(color, examiner.findCombinationOfThree(cells));
    }

    private int findWinningMove(char color, Map<Integer[], Character> combinations) throws PaintMEException {
        if (!combinations.isEmpty()) {
            for (Map.Entry<Integer[], Character> combination : combinations.entrySet()) {
                if (combination.getValue() == color) {
                    return combination.getKey()[combination.getKey().length - 1];
                }
            }
        }
        return -1;
    }
}
