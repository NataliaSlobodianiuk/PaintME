package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.AlgorithmsDictionaries;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Square3FindAMoveAlgorithm extends FindAMoveAlgorithm{
    protected int findWinningMove(String cells){
        int cellToMarkNum = -1;

        Map<Integer[], Character> twos = this.examiner.findCombinationOfTwo(cells);
        for (Integer[] two : twos.keySet()) {
            for (Integer[] pair : AlgorithmsDictionaries.Square3ThirdInARow.keySet()) {
                if (Arrays.equals(two, pair)) {
                    cellToMarkNum = AlgorithmsDictionaries.Square3ThirdInARow.get(pair);
                    break;
                }
            }
            if (cellToMarkNum != -1) {
                break;
            }
        }
        return cellToMarkNum;
    }

    protected int findAStrategicMove(char color, String cells, List<Integer[]> winningPositions){
        int cellToMarkNum = -1;

        Integer[] freeCells = this.examiner.findFreeCells(cells);
        Integer[] myCells = this.examiner.findCellsByColor(cells, color);

        boolean isAvailable = true;
        for (Integer[] winningPositionList: winningPositions) {
            for (Integer winningPosition : winningPositionList) {
                if (!Arrays.asList(freeCells).contains(winningPosition) &&
                        !Arrays.asList(myCells).contains(winningPosition)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                for (Integer winningPos : winningPositionList) {
                    if (!Arrays.asList(myCells).contains(winningPos)) {
                        cellToMarkNum = winningPos;
                        break;
                    }
                }
            }
            isAvailable = true;
        }
        return cellToMarkNum;
    }
}
