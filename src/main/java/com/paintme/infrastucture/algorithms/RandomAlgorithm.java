package com.paintme.infrastucture.algorithms;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Field;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAlgorithm extends FindMoveAlgorithm {
    @Override
    public int findAMove(char color, BoardType boardType, Field field, String cells) throws PaintMEException {
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
        return freeCells.isEmpty() ? -1 : freeCells.get(ThreadLocalRandom.current().nextInt(0, freeCells.size() - 1));
    }
}
