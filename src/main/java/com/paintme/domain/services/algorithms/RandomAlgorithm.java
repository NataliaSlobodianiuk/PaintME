package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.BoardExaminerFactory;
import com.paintme.domain.services.PaintMEException;
import com.paintme.domain.services.board_examiners.BoardExaminer;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAlgorithm implements FindAMoveAlgorithm{
    @Override
    public int findAMove(char color, String boardType, String cells) throws PaintMEException {
        BoardExaminerFactory examinerFactory = new BoardExaminerFactory();
        BoardExaminer examiner = null;
        try {
            examiner = examinerFactory.getBoardExaminer(boardType, cells.length());
        } catch (IllegalArgumentException exception) {
            throw new PaintMEException(
                    "BoardExaminerFactory couldn't create a board examiner." +
                            "Following exception occurred:" +
                            exception.getMessage(),
                    exception);
        }

        Integer[] freeCells = examiner.findFreeCells(cells);

        int cellToMarkNum = ThreadLocalRandom.current().nextInt(0, freeCells.length - 1);

        return cellToMarkNum;
    }
}
