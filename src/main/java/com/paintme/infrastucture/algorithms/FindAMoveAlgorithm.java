package com.paintme.infrastucture.algorithms;

import com.paintme.infrastucture.BoardExaminerFactory;
import com.paintme.PaintMEException;
import com.paintme.infrastucture.board_examiners.BoardExaminer;

import java.util.Arrays;
import java.util.List;

public abstract class FindAMoveAlgorithm {
    private int randomFrequencyNum = 0;
    protected BoardExaminer examiner = null;

    public int getRandomFrequencyNum() {
        return randomFrequencyNum;
    }

    protected void setRandomFrequencyNum(int randomFrequencyNum) {
        this.randomFrequencyNum = randomFrequencyNum;
    }

    public abstract int findAMove(char color, String boardType, String cells)
            throws PaintMEException;

    protected void setExaminer(String boardType, int cellsLength)
            throws PaintMEException {
        BoardExaminerFactory examinerFactory = new BoardExaminerFactory();
        try {
            this.examiner = examinerFactory.getBoardExaminer(boardType, cellsLength);
        } catch (IllegalArgumentException exception) {
            throw new PaintMEException(
                    "BoardExaminerFactory couldn't create a board examiner." +
                            "Following exception occurred:" +
                            exception.getMessage(),
                    exception);
        }
    }

    protected int findAStrategicMove(char color, String cells, List<Integer[]> winningPositions){
        int cellToMarkNum = -1;

        Integer[] freeCells = this.examiner.findFreeCells(cells);
        Integer[] myCells = this.examiner.findCellsByColor(cells, color);

        boolean isAvailable = true;
        for (Integer[] winningPositionList: winningPositions) {
            for (Integer winningPosition : winningPositionList) {
                if (!Arrays.asList(freeCells).contains(winningPosition) &&
                        !Arrays.asList(myCells).contains(winningPosition)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                for (Integer winningPos : winningPositionList) {
                    if (!Arrays.asList(myCells).contains(winningPos)) {
                        cellToMarkNum = winningPos;
                        break;
                    }
                }
            }
            isAvailable = true;
        }
        return cellToMarkNum;
    }
}
