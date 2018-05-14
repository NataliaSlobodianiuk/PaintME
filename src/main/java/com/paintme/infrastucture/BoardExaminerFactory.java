package com.paintme.infrastucture;

import com.paintme.infrastucture.board_examiners.BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square5BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square9BoardExaminer;

public class BoardExaminerFactory{
    public BoardExaminer getBoardExaminer (BoardType boardType, Field field) {
        BoardExaminer BoardExaminer = null;
        
        switch (boardType) {
            case _2D:
                switch (field) {
                    case THREE_BY_THREE:
                        BoardExaminer = new Square3BoardExaminer();
                        break;
                    case FIVE_BY_FIVE:
                        BoardExaminer = new Square5BoardExaminer();
                        break;
                    case NINE_BY_NINE:
                        BoardExaminer = new Square9BoardExaminer();
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Unknown field type.");
                }
                break;
            case CUBE:
                //ToDo
            default:
                throw new IllegalArgumentException(
                        "Unknown type of board.");
        }

        return BoardExaminer;
    }
}
