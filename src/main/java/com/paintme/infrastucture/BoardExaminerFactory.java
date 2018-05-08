package com.paintme.infrastucture;

import com.paintme.infrastucture.board_examiners.IBoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;

public class BoardExaminerFactory{
    public IBoardExaminer getBoardExaminer (String boardType, int cellsLength) {
        IBoardExaminer IBoardExaminer = null;
        
        switch (boardType.toUpperCase()) {
            case "SQUARE":
                switch (cellsLength) {
                    case 9:
                        IBoardExaminer = new Square3BoardExaminer();
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Unknown size of board.");
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown type of board.");
        }

        return IBoardExaminer;
    }
}
