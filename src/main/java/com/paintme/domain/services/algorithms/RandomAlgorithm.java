package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.PaintMEException;
import com.paintme.domain.services.board_examiners.BoardExaminer;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAlgorithm extends FindAMoveAlgorithm{
    public RandomAlgorithm() {
        this.setRandomFrequencyNum(1);
    }

    @Override
    public int findAMove(char color, String boardType, String cells) throws PaintMEException {
        BoardExaminer examiner;
        try {
            examiner = this.getBoardExaminer(boardType, cells.length());
        } catch (IllegalArgumentException exception) {
            throw new PaintMEException(
                    "Couldn't create a board examiner." +
                            "Following exception occurred:" +
                            exception.getMessage(),
                    exception);
        }

        Integer[] freeCells = examiner.findFreeCells(cells);

        int cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(0, freeCells.length - 1)];

        return cellToMarkNum;
    }
}
