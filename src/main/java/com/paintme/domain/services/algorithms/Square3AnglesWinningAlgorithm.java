package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.AlgorithmsDictionaries;
import com.paintme.domain.services.PaintMEException;

public class Square3AnglesWinningAlgorithm extends Square3WinningAlgorithm {
    @Override
    public int findAMove(char color, String boardType, String cells) throws PaintMEException {
        return this.findAMove(
                color, boardType, cells, AlgorithmsDictionaries.Square3BAnglesWinningPositions);
    }
}
