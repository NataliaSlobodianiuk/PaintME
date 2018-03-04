package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.Difficulty;
import com.paintme.infrastucture.GameMode;

public interface GameService {
    GameMode getGameMode() throws PaintMEException;
    void setGameMode(GameMode mode) throws PaintMEException;

    Difficulty getDifficulty() throws PaintMEException;
    void setDifficulty(String difficulty) throws PaintMEException;
    void setDifficulty(Difficulty difficulty) throws PaintMEException;

    String getSide1Login() throws PaintMEException;
    void setSide1Login(String login) throws PaintMEException;

    String getSide2Login() throws PaintMEException;
    void setSide2Login(String login) throws PaintMEException;

    String getSide1Color() throws PaintMEException;
    void setSide1Color(String rgb) throws PaintMEException;

    String getSide2Color() throws PaintMEException;
    void setSide2Color(String rgb) throws PaintMEException;
}
