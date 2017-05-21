package com.paintme.infrastucture.algorithms;

import com.paintme.infrastucture.AlgorithmsDictionaries;

import java.util.Arrays;
import java.util.Map;

public abstract class Square3FindAMoveAlgorithm extends FindAMoveAlgorithm{
    protected int findAWinningMove(String cells){
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
}
