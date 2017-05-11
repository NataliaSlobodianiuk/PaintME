package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.PaintMEException;
import com.paintme.domain.services.StrategyDictionaries;
import com.paintme.domain.services.board_examiners.BoardExaminer;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Square3BlockingAlgorithm extends FindAMoveAlgorithm{
    public Square3BlockingAlgorithm(){
        this.setRandomFrequencyNum(ThreadLocalRandom.current().nextInt(1, 4));
    }
    @Override
    public int findAMove(char color, String boardType, String cells) throws PaintMEException {
        int cellToMarkNum = -1;

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
        if (Arrays.asList(freeCells).contains(4)) {
            cellToMarkNum = 4;
        }
        else if (freeCells.length > 6){
            cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(0, freeCells.length - 1)];
        }
        else {
            if (ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE) % this.getRandomFrequencyNum() == 0){
                cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(0, freeCells.length - 1)];
            }
            else {
                Map<Integer[], Character> twos = examiner.findCombinationOfTwo(cells);
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
            }
        }

        return cellToMarkNum;
    }
}
