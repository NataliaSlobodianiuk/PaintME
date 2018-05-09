package com.paintme.infrastucture;

import com.paintme.infrastucture.board_examiners.IBoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square5BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square9BoardExaminer;

public class BoardExaminerFactory{
    public IBoardExaminer getBoardExaminer (String boardType, int cellsLength) {
        IBoardExaminer IBoardExaminer = null;
        
        switch (boardType.toUpperCase()) {
            case "SQUARE":
                switch (cellsLength) {
                    case 9:
                        IBoardExaminer = new Square3BoardExaminer();
                        break;
                    case 25:
                        IBoardExaminer = new Square5BoardExaminer();
                        break;
                    case 81:
                        IBoardExaminer = new Square9BoardExaminer();
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
