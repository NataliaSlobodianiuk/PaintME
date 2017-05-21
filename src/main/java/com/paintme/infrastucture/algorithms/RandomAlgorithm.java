package com.paintme.infrastucture.algorithms;

import com.paintme.PaintMEException;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAlgorithm extends FindAMoveAlgorithm{
    public RandomAlgorithm() {
        this.setRandomFrequencyNum(1);
    }

    @Override
    public int findAMove(char color, String boardType, String cells)
            throws PaintMEException {

        if (this.examiner == null) {
            try {
                this.setExaminer(boardType, cells.length());
            } catch (IllegalArgumentException exception) {
                throw new PaintMEException(
                        "Couldn't create a board examiner." +
                                "Following exception occurred:" +
                                exception.getMessage(),
                        exception);
            }
        }

        Integer[] freeCells = examiner.findFreeCells(cells);

        int cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(0, freeCells.length - 1)];

        return cellToMarkNum;
    }
}
