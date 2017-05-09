package com.paintme.domain.services.algorithms;

import com.paintme.domain.services.PaintMEException;

public interface FindAMoveAlgorithm {
    int findAMove(char color, String boardType, String cells) throws PaintMEException;
}
