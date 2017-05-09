package com.paintme.domain.services.strategies;

public class EasyMode extends GameDifficultyStrategy {
    @Override
    protected int getDifficultyLevel() {
        return 0;
    }
}