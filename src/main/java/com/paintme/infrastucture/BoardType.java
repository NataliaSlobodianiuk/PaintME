package com.paintme.infrastucture;

public enum BoardType {
    _2D ("2D"),
    CUBE ("Cube");

    private String name;

    BoardType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static BoardType fromString(String name) {
        for (BoardType boardType : BoardType.values()) {
            if (boardType.name.equalsIgnoreCase(name)) {
                return boardType;
            }
        }
        return null;
    }
}
