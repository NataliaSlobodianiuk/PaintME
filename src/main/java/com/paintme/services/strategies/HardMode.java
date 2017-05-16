package com.paintme.services.strategies;

import java.util.concurrent.ThreadLocalRandom;

public class HardMode extends GameDifficultyStrategy {
    @Override
    protected int getDifficultyLevel() {
        int difficultyLevel = ThreadLocalRandom.current().nextInt(3, 5);
        return difficultyLevel;
    }
}
