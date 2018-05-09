package com.paintme.infrastucture;

public enum Field {
    THREE_BY_THREE("3x3"),
    FIVE_BY_FIVE("5x5"),
    NINE_BY_NINE("9x9");

    private String name;

    Field(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Field fromString(String name) {
        for (Field field : Field.values()) {
            if (field.name.equalsIgnoreCase(name)) {
                return field;
            }
        }
        return null;
    }
}
