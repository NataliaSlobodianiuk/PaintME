package com.paintme.infrastucture.algorithms;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.BoardExaminerFactory;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Field;
import com.paintme.infrastucture.board_examiners.BoardExaminer;

public abstract class FindMoveAlgorithm {
    protected int winAmount = 3;

    protected BoardExaminer examiner = null;
    protected void setExaminer(BoardType boardType, Field field) throws PaintMEException {
        BoardExaminerFactory examinerFactory = new BoardExaminerFactory();
        try {
            this.examiner = examinerFactory.getBoardExaminer(boardType, field);
        } catch (IllegalArgumentException exception) {
            throw new PaintMEException(
                    "BoardExaminerFactory couldn't create a board examiner." +
                            "Following exception occurred:" +
                            exception.getMessage(),
                    exception);
        }
    }

    public abstract int findAMove(char color, BoardType boardType, Field field, String cells) throws PaintMEException;
}
