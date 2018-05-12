package com.paintme.infrastucture.algorithms;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Field;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Square3BlockingAlgorithm extends Square3FindAMoveAlgorithm{
    public Square3BlockingAlgorithm(){
        this.setRandomFrequencyNum(ThreadLocalRandom.current().nextInt(1, 4));
    }

    @Override
    public int findAMove(char color, BoardType boardType, Field field, String cells)
            throws PaintMEException {
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

        List<Integer> freeCells = examiner.findFreeCells(cells);
        if (freeCells.contains(4)) {
            cellToMarkNum = 4;
        } else if (freeCells.size() > 6){
            cellToMarkNum = freeCells.get(ThreadLocalRandom.current().nextInt(0, freeCells.size() - 1));
        } else {
            if (ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE) % this.getRandomFrequencyNum() == 0) {
                cellToMarkNum = freeCells.get(ThreadLocalRandom.current().nextInt(0, freeCells.size() - 1));
            } else {
                cellToMarkNum = this.findAWinningMove(cells);

                if (cellToMarkNum == -1) {
                    cellToMarkNum = freeCells.get(ThreadLocalRandom.current().nextInt(0, freeCells.size() - 1));
                }
            }
        }

        return cellToMarkNum;
    }
}
