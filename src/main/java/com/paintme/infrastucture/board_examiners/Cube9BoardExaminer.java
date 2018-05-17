package com.paintme.infrastucture.board_examiners;

public class Cube9BoardExaminer extends CubeBoardExaminer {
    public Cube9BoardExaminer() {
        this.sideExaminer = new Square9BoardExaminer();
    }
}
