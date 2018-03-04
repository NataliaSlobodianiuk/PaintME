package com.paintme.infrastucture.strategies;

import java.util.concurrent.ThreadLocalRandom;

public class NormalMode extends GameDifficultyStrategy {
    @Override
    protected int getDifficultyLevel() {
        int difficultyLevel = ThreadLocalRandom.current().nextInt(1, 3);
        return difficultyLevel;
    }
}
