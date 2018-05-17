package com.paintme.infrastucture.board_examiners;

public class Cube5BoardExaminer extends CubeBoardExaminer {
    public Cube5BoardExaminer() {
        this.sideExaminer = new Square5BoardExaminer();
    }
}
