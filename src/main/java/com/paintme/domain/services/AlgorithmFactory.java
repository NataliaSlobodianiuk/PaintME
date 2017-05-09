package com.paintme.domain.services;

import com.paintme.domain.services.algorithms.*;

public class AlgorithmFactory{
    public FindAMoveAlgorithm getAlgorithm (String boardType, int cellsLength, int difficultyLevel) {
        FindAMoveAlgorithm algorithm = null;

        if (difficultyLevel == 0) {
            algorithm = new RandomAlgorithm();
        }
        else {
            switch (boardType.toUpperCase()) {
                case "SQUARE":
                    switch (cellsLength) {
                        case 9:
                            switch (difficultyLevel) {
                                case 1:
                                    //BlockingThreesInDiagonalsAlgorithm
                                    break;
                                case 2:
                                    //BlockingThreesAlgorithm
                                    break;
                                case 3:
                                    //HardModeAlgorithm1
                                    break;
                                case 4:
                                    //HardModeAlgorithm2
                                    break;
                                default:
                                    throw new IllegalArgumentException(
                                            "Unknown difficulty level.");
                            }
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Unknown size of board.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown type of board.");
            }
        }

        return algorithm;
    }
}
