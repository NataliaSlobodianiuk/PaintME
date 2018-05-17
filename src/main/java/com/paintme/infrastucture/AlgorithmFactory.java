package com.paintme.infrastucture;

import com.paintme.infrastucture.algorithms.*;

public class AlgorithmFactory{
    public FindMoveAlgorithm getAlgorithm (BoardType boardType, Field field, int difficultyLevel) {
        FindMoveAlgorithm algorithm;

        if (difficultyLevel == 0) {
            algorithm = new RandomAlgorithm();
        } else {
            switch (boardType) {
                case _2D:
                case CUBE:
                    switch (field) {
                        case THREE_BY_THREE:
                            switch (difficultyLevel) {
                                case 1:
                                case 2:
                                    algorithm = new SquareBlockingAlgorithm(3);
                                    break;
                                case 3:
                                case 4:
                                    algorithm = new SquareWinningAlgorithm(3);
                                    break;
                                default:
                                    throw new IllegalArgumentException(
                                            "Unknown difficulty level.");
                            }
                            break;
                        case FIVE_BY_FIVE:
                        case NINE_BY_NINE:
                            switch (difficultyLevel) {
                                case 1:
                                case 2:
                                    algorithm = new SquareBlockingAlgorithm(4);
                                    break;
                                case 3:
                                case 4:
                                    algorithm = new SquareWinningAlgorithm(4);
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
                default:
                    throw new IllegalArgumentException(
                            "Unknown type of board.");
            }
        }

        return algorithm;
    }
}
