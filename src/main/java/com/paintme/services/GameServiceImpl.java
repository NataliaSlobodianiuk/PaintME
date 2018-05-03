package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.infrastucture.Difficulty;
import com.paintme.infrastucture.GameMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private String gameProperties = Properties.Files.SESSION.toString();

    private String gameModePropertyName = Properties.GameProperties.GAMEMODE.toString();
    private String difficultyPropertyName = Properties.GameProperties.DIFFICULTY.toString();
    private String side1LoginPropertyName = Properties.GameProperties.SIDE1LOGIN.toString();
    private String side2LoginPropertyName = Properties.GameProperties.SIDE2LOGIN.toString();
    private String side1ColorPropertyName = Properties.GameProperties.SIDE1COLOR.toString();
    private String side2ColorPropertyName = Properties.GameProperties.SIDE2COLOR.toString();

    private PropertiesService propertiesService;

    @Autowired
    public GameServiceImpl(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
        this.propertiesService.setPropertiesFileName(this.gameProperties);
    }

    @Override
    public GameMode getGameMode() throws PaintMEException{
        return GameMode.valueOf(this.getGameModeProperty());
    }

    @Override
    public void setGameMode(GameMode mode) throws PaintMEException{
        this.setProperty(this.gameModePropertyName, this.getGameModeProperty(), mode.name());
    }

    @Override
    public Difficulty getDifficulty() throws PaintMEException {
        return Difficulty.valueOf(this.getDifficultyProperty());
    }

    @Override
    public void setDifficulty(String difficulty) throws PaintMEException {
        this.setProperty(this.difficultyPropertyName, this.getDifficultyProperty(), difficulty);
    }

    @Override
    public void setDifficulty(Difficulty difficulty) throws PaintMEException {
        this.setDifficulty(difficulty.name());
    }

    @Override
    public String getSide1Login() throws PaintMEException {
        return this.propertiesService.getProperty(this.side1LoginPropertyName);
    }

    @Override
    public void setSide1Login(String login) throws PaintMEException {
        this.setProperty(this.side1LoginPropertyName, this.getSide1Login(), login);
    }

    @Override
    public String getSide2Login() throws PaintMEException {
        return this.propertiesService.getProperty(this.side2LoginPropertyName);
    }

    @Override
    public void setSide2Login(String login) throws PaintMEException {
        this.setProperty(this.side2LoginPropertyName, this.getSide2Login(), login);
    }

    @Override
    public String getSide1Color() throws PaintMEException {
        return this.propertiesService.getProperty(this.side1ColorPropertyName);
    }

    @Override
    public void setSide1Color(String rgb) throws PaintMEException {
        this.setProperty(this.side1ColorPropertyName, this.getSide1Color(), rgb);
    }

    @Override
    public String getSide2Color() throws PaintMEException {
        return this.propertiesService.getProperty(this.side2ColorPropertyName);
    }

    @Override
    public void setSide2Color(String rgb) throws PaintMEException {
        this.setProperty(this.side2ColorPropertyName, this.getSide2Color(), rgb);
    }

    private String getGameModeProperty() throws PaintMEException {
        return this.propertiesService.getProperty(this.gameModePropertyName);
    }

    private String getDifficultyProperty() throws PaintMEException {
        return this.propertiesService.getProperty(this.difficultyPropertyName);
    }

    private void setProperty(String propertyName, String currValue, String newValue) throws PaintMEException{
        if (currValue != null) {
            this.propertiesService.updateProperty(propertyName, newValue);
        }
        else {
            this.propertiesService.addProperty(propertyName, newValue);
        }
    }
}
