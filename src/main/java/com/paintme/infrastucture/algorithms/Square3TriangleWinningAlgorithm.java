package com.paintme.infrastucture.algorithms;

import com.paintme.infrastucture.AlgorithmsDictionaries;
import com.paintme.PaintMEException;

public class Square3TriangleWinningAlgorithm extends Square3WinningAlgorithm {
    @Override
    public int findAMove(char color, String boardType, String cells) throws PaintMEException {
        return this.findAMove(
                color, boardType, cells, AlgorithmsDictionaries.Square3BTriangleWinningPositions);
    }
}
