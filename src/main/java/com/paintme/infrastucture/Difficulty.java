package com.paintme.infrastucture;

import com.paintme.infrastucture.strategies.*;

public enum Difficulty {
    EASY,
    NORMAL,
    HARD,
    IMMORTAL;

    public GameDifficultyStrategy toStrategy(){
        if (this == Difficulty.EASY) {
            return new EasyMode();
        }
        else if (this == Difficulty.NORMAL) {
            return new NormalMode();
        }
        else if (this == Difficulty.HARD) {
            return new HardMode();
        }
        else {
            return null;
        }
    }
}
