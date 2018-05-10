package com.paintme.infrastucture;

import com.paintme.infrastucture.board_examiners.BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square5BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square9BoardExaminer;

public class BoardExaminerFactory{
    public BoardExaminer getBoardExaminer (String boardType, int cellsLength) {
        BoardExaminer BoardExaminer = null;
        
        switch (boardType.toUpperCase()) {
            case "SQUARE":
                switch (cellsLength) {
                    case 9:
                        BoardExaminer = new Square3BoardExaminer();
                        break;
                    case 25:
                        BoardExaminer = new Square5BoardExaminer();
                        break;
                    case 81:
                        BoardExaminer = new Square9BoardExaminer();
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

        return BoardExaminer;
    }
}
