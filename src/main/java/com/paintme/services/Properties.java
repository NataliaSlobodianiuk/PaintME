package com.paintme.services;

public class Properties {
    public enum EnumValues {
        APPLICATION("application.properties"),
        SESSION("session.properties"),
        LOGIN("user.name"),
        PASSWORD("user.password"),
        GAMEMODE("gamemode"),
        DIFFICULTY("computer.difficulty"),
        SIDE1LOGIN("side1.login"),
        SIDE2LOGIN("side2.login"),
        SIDE1COLOR("side1.color"),
        SIDE2COLOR("side2.color"),
        BOARDTYPE("board.type"),
        BOARDSIZE("board.size");

        private String name;

        EnumValues(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Files {
        APPLICATION,
        SESSION;

        @Override
        public String toString()
        {
            if (this == Files.APPLICATION) {
                return EnumValues.APPLICATION.toString();
            } else {
                return EnumValues.SESSION.toString();
            }
        }
    }

    public enum UserProperties {
        LOGIN,
        PASSWORD;

        @Override
        public String toString()
        {
            if (this == UserProperties.LOGIN) {
                return EnumValues.LOGIN.toString();
            } else {
                return EnumValues.PASSWORD.toString();
            }
        }
    }

    public enum GameProperties {
        GAMEMODE,
        DIFFICULTY,
        SIDE1LOGIN,
        SIDE2LOGIN,
        SIDE1COLOR,
        SIDE2COLOR,
        BOARDTYPE,
        BOARDSIZE;

        @Override
        public String toString()
        {
            if (this == GameProperties.GAMEMODE) {
                return EnumValues.GAMEMODE.toString();
            } else if (this == GameProperties.DIFFICULTY) {
                return EnumValues.DIFFICULTY.toString();
            } else if (this == GameProperties.SIDE1LOGIN) {
                return EnumValues.SIDE1LOGIN.toString();
            } else if (this == GameProperties.SIDE2LOGIN) {
                return EnumValues.SIDE2LOGIN.toString();
            } else if (this == GameProperties.SIDE1COLOR) {
                return EnumValues.SIDE1COLOR.toString();
            } else if (this == GameProperties.SIDE2COLOR) {
                return EnumValues.SIDE2COLOR.toString();
            } else if (this == GameProperties.BOARDTYPE) {
                return EnumValues.BOARDTYPE.toString();
            } else {
                return EnumValues.BOARDSIZE.toString();
            }
        }
    }
}
