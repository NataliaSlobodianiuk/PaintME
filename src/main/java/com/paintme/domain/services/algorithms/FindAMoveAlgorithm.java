package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.BoardExaminerFactory;
import com.paintme.domain.services.PaintMEException;
import com.paintme.domain.services.StrategyDictionaries;
import com.paintme.domain.services.board_examiners.BoardExaminer;

import java.util.Arrays;
import java.util.Map;

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

    protected int blockWinningMoves(String cells){
        int cellToMarkNum = -1;

        Map<Integer[], Character> twos = this.examiner.findCombinationOfTwo(cells);
        for (Integer[] two : twos.keySet()) {
            for (Integer[] pair : StrategyDictionaries.Square3BlockingTwos.keySet()) {
                if (Arrays.equals(two, pair)) {
                    cellToMarkNum = StrategyDictionaries.Square3BlockingTwos.get(pair);
                    break;
                }
            }
            if (cellToMarkNum != -1) {
                break;
            }
        }
        return cellToMarkNum;
    }
}
