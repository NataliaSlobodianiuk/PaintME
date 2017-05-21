package com.paintme.infrastucture.strategies;

public class EasyMode extends GameDifficultyStrategy {
    @Override
    protected int getDifficultyLevel() {
        return 0;
    }
}