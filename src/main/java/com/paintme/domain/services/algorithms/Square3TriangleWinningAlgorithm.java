package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.PaintMEException;
import com.paintme.domain.services.StrategyDictionaries;

import java.util.Arrays;
import java.util.Map;
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

                if (cellToMarkNum == -1) {
                    /*ToDo*/
                }
            }
        }

        return cellToMarkNum;
    }
}
