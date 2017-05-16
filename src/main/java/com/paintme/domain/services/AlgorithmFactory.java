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
                                case 2:
                                    algorithm = new Square3BlockingAlgorithm();
                                    break;
                                case 3:
                                    algorithm = new Square3TriangleWinningAlgorithm();
                                    break;
                                case 4:
                                    algorithm = new Square3AnglesWinningAlgorithm();
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
