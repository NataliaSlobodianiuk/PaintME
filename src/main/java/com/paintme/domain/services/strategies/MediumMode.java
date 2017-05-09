package com.paintme.domain.services.strategies;

import java.util.concurrent.ThreadLocalRandom;

public class MediumMode extends GameDifficultyStrategy {
    @Override
    protected int getDifficultyLevel() {
        int difficultyLevel = ThreadLocalRandom.current().nextInt(1, 3);
        return difficultyLevel;
    }
}
