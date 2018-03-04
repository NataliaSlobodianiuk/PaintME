package com.paintme.infrastucture.algorithms;

import com.paintme.PaintMEException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Square3WinningAlgorithm extends Square3FindAMoveAlgorithm {
    public Square3WinningAlgorithm(){
        this.setRandomFrequencyNum(ThreadLocalRandom.current().nextInt(10, 100));
    }

    protected int findAMove(char color, String boardType, String cells, List<Integer[]> winningPositions)
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

        List<Integer> freeCells = this.examiner.findFreeCells(cells);

        if (ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE) % this.getRandomFrequencyNum() == 0){
            cellToMarkNum = freeCells.get(ThreadLocalRandom.current().nextInt(0, freeCells.size() - 1));
        }
        else {
            if (freeCells.contains(4)) {
                cellToMarkNum = 4;
            } else if (freeCells.size() > 6) {
                for (int i = 0; i < 9; i = i + 2) {
                    if (!freeCells.contains(i)) {
                        cellToMarkNum = i;
                        break;
                    }
                }
            } else {
                cellToMarkNum = this.findAWinningMove(cells);
                if (cellToMarkNum == -1) {
                    cellToMarkNum = this.findAStrategicMove(color, cells, winningPositions);
                }
                if (cellToMarkNum == -1) {
                    cellToMarkNum = freeCells.get(ThreadLocalRandom.current().nextInt(0, freeCells.size() - 1));
                }
            }
        }

        return cellToMarkNum;
    }
}
