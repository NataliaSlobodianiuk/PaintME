package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.PaintMEException;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Square3TriangleWinningAlgorithm extends FindAMoveAlgorithm{
    @Override
    public int findAMove(char color, String boardType, String cells) throws PaintMEException {
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

        Integer[] freeCells = this.examiner.findFreeCells(cells);

        if (ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE) % this.getRandomFrequencyNum() == 0){
            cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(0, freeCells.length - 1)];
        }
        else {
            if (Arrays.asList(freeCells).contains(4)) {
                cellToMarkNum = 4;
            } else if (freeCells.length > 6) {
                for (int i = 0; i < 9; i = i + 2) {
                    if (!Arrays.asList(freeCells).contains(i)) {
                        cellToMarkNum = i;
                        break;
                    }
                }
            } else {
                cellToMarkNum = this.blockWinningMoves(cells);

                if (cellToMarkNum == -1) {
                    /*ToDo*/
                }
            }
        }

        return cellToMarkNum;
    }
}
