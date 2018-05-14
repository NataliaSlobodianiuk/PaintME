package com.paintme.infrastucture.strategies;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.AlgorithmFactory;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Field;
import com.paintme.infrastucture.algorithms.FindMoveAlgorithm;

public abstract class GameDifficultyStrategy {
    private FindMoveAlgorithm algorithm = null;

    public int getCellToMark(char myColor, BoardType boardType, Field field, String cells) throws PaintMEException {
        if (this.algorithm == null) {
            try {
                this.setAlgorithm(boardType, field);
            } catch (PaintMEException exception) {
                throw new PaintMEException(
                        "The algorithm couldn't be set." +
                                "Following exception occurred:" +
                                exception.getMessage(),
                        exception);
            }
        }

        int cellToMarkNum;
        try {
            cellToMarkNum = this.algorithm.findAMove(myColor, boardType, field, cells);
        } catch (PaintMEException exception) {
            throw new PaintMEException(
                    "A move based on " +
                            this.algorithm.getClass().getSimpleName() +
                            " couldn't be found." +
                            "Following exception occurred:" +
                            exception.getMessage(),
                    exception);
        }

        return cellToMarkNum;
    }

    private void setAlgorithm(BoardType boardType, Field cellsLength) throws PaintMEException {
        int difficultyLevel = this.getDifficultyLevel();

        AlgorithmFactory algorithmFactory = new AlgorithmFactory();
        try {
            this.algorithm = algorithmFactory.getAlgorithm(boardType, cellsLength, difficultyLevel);
        } catch(IllegalArgumentException exception) {
            throw new PaintMEException(
                    "AlgorithmFactory couldn't create an algorithm." +
                            "Following exception occurred:" +
                            exception.getMessage(),
                    exception);
        }
    }

    protected abstract int getDifficultyLevel();
}
