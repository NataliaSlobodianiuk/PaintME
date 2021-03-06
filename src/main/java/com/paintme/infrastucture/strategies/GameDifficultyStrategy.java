package com.paintme.infrastucture.strategies;

import com.paintme.infrastucture.AlgorithmFactory;
import com.paintme.PaintMEException;
import com.paintme.infrastucture.algorithms.FindAMoveAlgorithm;

public abstract class GameDifficultyStrategy {
    private FindAMoveAlgorithm algorithm = null;

    public String makeAMove(char myColor, String boardType, String cells) throws PaintMEException {
        if (this.algorithm == null) {
            try {
                this.setAlgorithm(boardType, cells.length());
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
            cellToMarkNum = this.algorithm.findAMove(myColor, boardType, cells);
        } catch (PaintMEException exception) {
            throw new PaintMEException(
                    "A move based on " +
                            this.algorithm.getClass().getSimpleName() +
                            " couldn't be found." +
                            "Following exception occurred:" +
                            exception.getMessage(),
                    exception);
        }

        StringBuilder newCells = new StringBuilder(cells);
        newCells.setCharAt(cellToMarkNum, myColor);

        return newCells.toString();
    }

    private void setAlgorithm(String boardType, int cellsLength) throws PaintMEException {
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
