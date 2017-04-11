package com.paintme.strategies;

public class EasyMode implements GameDifficultyStrategy {

    @Override
    public int findAMove(String cells) {
        return 0;
    }
}