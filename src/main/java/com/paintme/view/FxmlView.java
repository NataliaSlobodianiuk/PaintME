package com.paintme.view;

import java.util.Map;
import java.util.ResourceBundle;

public enum FxmlView {
    MAIN{
        @Override
        String setTitle(){
            return "Paint Me";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/mainMenu.fxml");
        }

        @Override
        int setWidth(){
            return  240;
        }

        @Override
        int setHeight(){
            return  290;
        }
    },
    GAME{
        @Override
        String setTitle(){
            return "Game";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/game.fxml");
        }

        @Override
        int setWidth(){
            return  600;
        }

        @Override
        int setHeight(){
            return  400;
        }
    },
    GAMEDETAILS{
        @Override
        String setTitle(){
            return "Game Details";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/gameDetails.fxml");
        }

        @Override
        int setWidth(){
            return  250;
        }

        @Override
        int setHeight(){
            return  350;
        }
    },
    HOMEPAGE{
        @Override
        String setTitle(){
            return "Home Page";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/homePage.fxml");
        }

        @Override
        int setWidth(){
            return  285;
        }

        @Override
        int setHeight(){
            return  500;
        }
    },
    LEADERBOARD{
        @Override
        String setTitle(){
            return "Leaderboard";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/leaderBoard.fxml");
        }

        @Override
        int setWidth(){
            return  550;
        }

        @Override
        int setHeight(){
            return  350;
        }
    },
    SIGNIN{
        @Override
        String setTitle(){
            return "Sign In";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/signIn.fxml");
        }

        @Override
        int setWidth(){
            return  193;
        }

        @Override
        int setHeight(){
            return  212;
        }
    },
    SIGNUP{
        @Override
        String setTitle(){
            return "Sign Up";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/signUp.fxml");
        }

        @Override
        int setWidth(){
            return  201;
        }

        @Override
        int setHeight(){
            return  298;
        }
    },
    TABLE{
        @Override
        String setTitle(){
            return "Table";
        }

        @Override
        String getFxmlFile(){
            return ("/fxml/table.fxml");
        }

        @Override
        int setWidth(){
            return  240;
        }

        @Override
        int setHeight(){
            return  260;
        }
    };

    abstract String setTitle();
    abstract String getFxmlFile();
    abstract int setWidth();
    abstract int setHeight();
}
