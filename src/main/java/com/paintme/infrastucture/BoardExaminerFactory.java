package com.paintme.infrastucture;

import com.paintme.infrastucture.board_examiners.*;

public class BoardExaminerFactory{
    public BoardExaminer getBoardExaminer (BoardType boardType, Field field) {
        BoardExaminer boardExaminer;
        
        switch (boardType) {
            case _2D:
                switch (field) {
                    case THREE_BY_THREE:
                        boardExaminer = new Square3BoardExaminer();
                        break;
                    case FIVE_BY_FIVE:
                        boardExaminer = new Square5BoardExaminer();
                        break;
                    case NINE_BY_NINE:
                        boardExaminer = new Square9BoardExaminer();
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Unknown field type.");
                }
                break;
            case CUBE:
                switch (field) {
                    case THREE_BY_THREE:
                        boardExaminer = new Cube3BoardExaminer();
                        break;
                    case FIVE_BY_FIVE:
                        boardExaminer = new Cube5BoardExaminer();
                        break;
                    case NINE_BY_NINE:
                        boardExaminer = new Cube9BoardExaminer();
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Unknown field type.");
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown type of board.");
        }

        return boardExaminer;
    }
}
