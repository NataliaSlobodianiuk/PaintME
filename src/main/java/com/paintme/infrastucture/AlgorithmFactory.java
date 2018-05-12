package com.paintme.infrastucture;

import com.paintme.infrastucture.algorithms.*;

public class AlgorithmFactory{
    public FindAMoveAlgorithm getAlgorithm (BoardType boardType, Field field, int difficultyLevel) {
        FindAMoveAlgorithm algorithm = null;

        if (difficultyLevel == 0) {
            algorithm = new RandomAlgorithm();
        } else {
            switch (boardType) {
                case _2D:
                    switch (field) {
                        case THREE_BY_THREE:
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
                                    "Unknown field type.");
                    }
                    break;
                case CUBE:
                    //ToDo
                default:
                    throw new IllegalArgumentException(
                            "Unknown type of board.");
            }
        }

        return algorithm;
    }
}
