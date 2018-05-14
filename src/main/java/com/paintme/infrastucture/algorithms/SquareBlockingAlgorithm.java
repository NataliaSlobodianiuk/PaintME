package com.paintme.infrastucture.algorithms;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Field;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SquareBlockingAlgorithm extends SquareFindMoveAlgorithm {
    public SquareBlockingAlgorithm(int winAmount){
        this.winAmount = winAmount;
    }

    @Override
    public int findAMove(char color, BoardType boardType, Field field, String cells)
            throws PaintMEException {
        int cellToMarkNum = this.findBlockMove(color, boardType, field, cells);
        if (cellToMarkNum == -1) {
            List<Integer> freeCells = this.examiner.findFreeCells(cells);
            if (!freeCells.isEmpty()) {
                cellToMarkNum = freeCells.get(ThreadLocalRandom.current().nextInt(0, freeCells.size() - 1));
            }
        }

        return cellToMarkNum;
    }
}
