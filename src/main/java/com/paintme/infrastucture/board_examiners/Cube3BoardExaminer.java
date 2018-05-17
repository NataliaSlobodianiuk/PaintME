package com.paintme.infrastucture.board_examiners;

public class Cube3BoardExaminer extends CubeBoardExaminer {
    public Cube3BoardExaminer() {
        this.sideExaminer = new Square3BoardExaminer();
    }
}
