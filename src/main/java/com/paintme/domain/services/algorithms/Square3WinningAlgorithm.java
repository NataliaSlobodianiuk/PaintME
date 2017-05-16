package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.AlgorithmsDictionaries;
import com.paintme.domain.services.PaintMEException;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Square3WinningAlgorithm extends Square3FindAMoveAlgorithm {
    public Square3WinningAlgorithm(){
        this.setRandomFrequencyNum(ThreadLocalRandom.current().nextInt(10, 100));
    }

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

        if (ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE) %
                this.getRandomFrequencyNum() == 0){
            cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(
                    0, freeCells.length - 1)];
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
                cellToMarkNum = this.findWinningMove(cells);
                if (cellToMarkNum == -1) {
                    cellToMarkNum = this.findAStrategicMove(
                            color, cells, AlgorithmsDictionaries.Square3BAnglesWinningPositions);
                }
                if (cellToMarkNum == -1) {
                    cellToMarkNum = freeCells[ThreadLocalRandom.current().nextInt(0, freeCells.length - 1)];
                }
            }
        }

        return cellToMarkNum;
    }
}
