package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.PaintMEException;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Square3BlockingAlgorithm extends Square3FindAMoveAlgorithm{
    public Square3BlockingAlgorithm(){
        this.setRandomFrequencyNum(ThreadLocalRandom.current().nextInt(1, 4));
    }

    @Override
    public int findAMove(char color, String boardType, String cells)
            throws PaintMEException {
        int cellToMarkNum = -1;

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
                cellToMarkNum = this.findAWinningMove(cells);

                if (cellToMarkNum == -1) {
                    cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(0, freeCells.length - 1)];
                }
            }
        }

        return cellToMarkNum;
    }
}
