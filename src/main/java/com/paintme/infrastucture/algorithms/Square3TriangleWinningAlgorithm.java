package com.paintme.infrastucture.algorithms;

import com.paintme.infrastucture.AlgorithmsDictionaries;
import com.paintme.PaintMEException;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Field;

public class Square3TriangleWinningAlgorithm extends Square3WinningAlgorithm {
    @Override
    public int findAMove(char color, BoardType boardType, Field field, String cells) throws PaintMEException {
        return this.findAMove(color, boardType, field, cells, AlgorithmsDictionaries.Square3BTriangleWinningPositions);
    }
}
